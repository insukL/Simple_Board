package SimpleBoard.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

// @Component
// @Aspect
public class ExceptionAspect {
    /* 해당 Aspect가 필요한지 알아보고 필요하면 사용하기
    @Around("execution(* SimpleBoard.controller.BoardController.*(..))")
    public Object catchException(ProceedingJoinPoint pjp){
        Object result = null;
        try{
            result = pjp.proceed();
        } catch (Throwable throwable) {
            result = new ResponseEntity<String>(throwable.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return result;
    }
     */
}
