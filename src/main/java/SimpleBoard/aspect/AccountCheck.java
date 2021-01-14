package SimpleBoard.aspect;

import SimpleBoard.exception.TokenExpireException;
import SimpleBoard.exception.TokenInvalidException;
import SimpleBoard.repository.BoardMapper;
import SimpleBoard.util.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
@Aspect
public class AccountCheck {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    BoardMapper boardMapper;

    //로그인 여부 확인
    @Before("(execution(* SimpleBoard.controller.BoardController.*(..))" +
            "|| execution(* SimpleBoard.controller.UserController.*(..)))" +
            "&& !@annotation(SimpleBoard.annotation.NoLogin)")
    public void checkLogin() throws RuntimeException{
        String token = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization");

        if(token == null || token.isEmpty()) throw new TokenInvalidException("Token이 존재하지 않습니다.");
        if(!token.startsWith("Bearer ")) throw new TokenInvalidException("Token이 유효하지 않습니다.");
        if(token.length() < 8) throw new TokenInvalidException("Token이 유효하지 않습니다.");
        if(jwtUtil.getExpireByToken(token).before(new Date())) throw new TokenExpireException("Token이 만료되었습니다");
    }

    //게시글 접근시 접근 권한 확인
    @Before("execution(* SimpleBoard.controller.BoardController.*(long, ..))" +
            "&& !@annotation(SimpleBoard.annotation.NoPermission)")
    public void checkBoardPermission(JoinPoint joinPoint) throws Exception{
        String token = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization");

        long id = 0;
        for(Object obj : joinPoint.getArgs()){
            if(obj instanceof Long) id = (long)obj;
        }

        if(boardMapper.getBoard(id).getAuthor_id() != jwtUtil.getIdByToken(token)) throw new Exception();
    }
}
