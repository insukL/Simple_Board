package SimpleBoard.aspect;

import SimpleBoard.domain.Board;
import SimpleBoard.domain.Comment;
import SimpleBoard.exception.EmptyStringException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 10)
@Aspect
public class ContentCheck {
    @Before("execution(* SimpleBoard.controller.BoardController.*(.., SimpleBoard.domain.Board))")
    public void checkBoardContent(JoinPoint joinPoint) throws EmptyStringException {
        Board board = null;
        for(Object obj : joinPoint.getArgs()){
            if(obj instanceof Board) board = (Board)obj;
        }

        if(board.getContent().trim().length() <= 0 || board.getTitle().trim().length() <= 0)
            throw new EmptyStringException("제목이나 내용이 없습니다.");
    }

    @Before("execution(* SimpleBoard.controller.CommentController.*(.., SimpleBoard.domain.Comment))")
    public void checkCommentContent(JoinPoint joinPoint) throws EmptyStringException {
        Comment comment = null;
        for(Object obj : joinPoint.getArgs()){
            if(obj instanceof Comment) comment = (Comment) obj;
        }

        if(comment.getContent().trim().length() <= 0)
            throw new EmptyStringException("내용이 없습니다.");
    }
}
