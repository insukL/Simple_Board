package SimpleBoard.aspect;

import SimpleBoard.domain.Board;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ContentCheck {
    @Before("execution(* SimpleBoard.controller.BoardController.*(.., SimpleBoard.domain.Board))")
    public void checkBoardContent(JoinPoint joinPoint) throws Exception{
        Board board = null;
        for(Object obj : joinPoint.getArgs()){
            if(obj instanceof Board) board = (Board)obj;
        }

        if(board.getContent().trim().length() <= 0 || board.getTitle().trim().length() <= 0)
            throw new IllegalArgumentException("Please enter title and content.");
    }
}
