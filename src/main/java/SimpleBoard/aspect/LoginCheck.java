package SimpleBoard.aspect;

import SimpleBoard.exception.TokenExpireException;
import SimpleBoard.exception.TokenInvalidException;
import SimpleBoard.repository.BoardMapper;
import SimpleBoard.repository.CommentMapper;
import SimpleBoard.util.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
@Order(value = 1)
@Aspect
public class LoginCheck {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    BoardMapper boardMapper;

    //로그인 여부 확인
    @Before("(execution(* SimpleBoard.controller.BoardController.*(..))" +
            "|| execution(* SimpleBoard.controller.UserController.*(..))" +
            "|| execution(* SimpleBoard.controller.CommentController.*(..)))" +
            "&& !@annotation(SimpleBoard.annotation.NoLogin)")
    public void checkLogin() throws RuntimeException{
        String token = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization");

        if(token == null || token.isEmpty()) throw new TokenInvalidException("Token이 존재하지 않습니다.");
        if(!token.startsWith("Bearer ") || token.length() < 8) throw new TokenInvalidException("Token이 유효하지 않습니다.");
        if(jwtUtil.getExpireByToken(token).before(new Date())) throw new TokenExpireException("Token이 만료되었습니다");
    }
}
