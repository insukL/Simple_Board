package SimpleBoard.aspect;

import SimpleBoard.exception.PermissionInvalidException;
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

@Component
@Order(value = 5)
@Aspect
public class PermissionCheck {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    CommentMapper commentMapper;

    //게시글 접근시 접근 권한 확인
    @Before("execution(* SimpleBoard.controller.BoardController.*(long, ..))" +
            "&& !@annotation(SimpleBoard.annotation.NoPermission)")
    public void checkBoardPermission(JoinPoint joinPoint) throws PermissionInvalidException{
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization");

        long id = 0;
        for(Object obj : joinPoint.getArgs()){
            if(obj instanceof Long){
                id = (long)obj;
                break;
            }
        }

        if(boardMapper.getBoard(id).getAuthor_id() != jwtUtil.getIdByToken(token)) throw new PermissionInvalidException("불가능한 접근입니다");
    }

    @Before("execution(* SimpleBoard.controller.CommentController.*(long, ..))" +
            "&& !@annotation(SimpleBoard.annotation.NoPermission)")
    public void checkCommentPermission(JoinPoint joinPoint) throws PermissionInvalidException{
        String token = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getHeader("Authorization");

        long id = 0;
        for(Object obj : joinPoint.getArgs()){
            if(obj instanceof Long){
                id = (long)obj;
                break;
            }
        }

        if(commentMapper.getCommentById(id).getAuthor_id() != jwtUtil.getIdByToken(token)) throw new PermissionInvalidException("불가능한 접근입니다");
    }
}
