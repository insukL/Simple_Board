package SimpleBoard.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class ExceptionAspect {
    /*
    @Around("execution(* SimpleBoard.controller.BoardController.*(..))")
    public Object catchException(ProceedingJoinPoint pjp){
        Object result = null;
        try{
            result = pjp.proceed();
        } catch (Throwable throwable) {
            result = new ResponseEntity<Exception>(, HttpStatus.BAD_REQUEST);
        }
        return result;
    }
     */
}
