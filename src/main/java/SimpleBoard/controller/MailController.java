package SimpleBoard.controller;

import SimpleBoard.util.MailSender.SesMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MailController {
    @Autowired
    SesMailSender mailSender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ResponseEntity<String> send(){
        mailSender.sendMail("test@jaejugom.com", "cloud_365@naver.com", "테스트입니다.",
                "<h1>안녕하세요<h1><p>테스트중입니다.");
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
