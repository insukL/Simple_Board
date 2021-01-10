package SimpleBoard.aspect;

import SimpleBoard.util.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class AccountCheck {
    @Autowired
    JwtUtil jwtUtil;

    @Before("execution(* SimpleBoard.controller.BoardController.*(String, ..))")
    public void checkLogin(JoinPoint joinPoint) throws Exception{
        String token = null;
        for(Object obj : joinPoint.getArgs()){
            if(obj instanceof String) token = (String)obj;
        }

        if(token == null || token.isEmpty()) throw new Exception();
        if(!token.startsWith("Bearer ")) throw new Exception();
        if(token.length() < 8) throw new Exception();
        if(jwtUtil.getExpireByToken(token).before(new Date())) throw new Exception();
    }
}
