package SimpleBoard.aspect;

import SimpleBoard.domain.SlackNoti.SlackAttachment;
import SimpleBoard.exception.BaseException;
import SimpleBoard.util.SlackSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SlackErrorNoti {
    @Autowired
    SlackSender slackSender;

    @AfterThrowing(value = "execution(* SimpleBoard.controller.*Controller.*(..))", throwing = "exception")
    public void sendSlackError(JoinPoint joinPoint, Throwable exception){
        if(exception instanceof BaseException) return; //Custom Exception에 대해서는 실행 X

        String errorMethod = joinPoint.getSignature().getName();
        String errorName = exception.getClass().getSimpleName();
        String errorTrace = exception.getStackTrace()[0].getClassName();
        String errorFile = exception.getStackTrace()[0].getFileName();

        String message = String.format("Error : %s\nErrorMethod : %s\nErrorTrace : %s\nErrorFile : %s",errorName, errorMethod, errorTrace, errorFile);
        slackSender.noticeError(message);
    }
}
