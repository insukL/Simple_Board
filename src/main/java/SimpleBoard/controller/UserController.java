package SimpleBoard.controller;

import SimpleBoard.domain.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import SimpleBoard.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestBody User user){
        return userService.signUp(user)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.OK);
    }
}
