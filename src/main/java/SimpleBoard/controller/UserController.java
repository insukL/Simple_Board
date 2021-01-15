package SimpleBoard.controller;

import SimpleBoard.annotation.NoLogin;
import SimpleBoard.domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import SimpleBoard.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @NoLogin
    @ApiOperation(value = "회원가입", notes = "회원 정보를 추가합니다.")
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestBody User user){
        return userService.signUp(user)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @NoLogin
    @ApiOperation(value = "로그인", notes = "회원 정보를 입력하고 토큰을 생성합니다.")
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@ApiParam(name="User", required=true, value="(required:User)") @RequestBody User user){
        return new ResponseEntity<String>(userService.login(user), HttpStatus.OK);
    }

    @ApiOperation(value = "회원정보 보기", notes = "회원 정보를 읽어옵니다.")
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@ApiIgnore @RequestHeader("Authorization") String token){
        return new ResponseEntity<User>(userService.getUser(token), HttpStatus.OK);
    }

    @ApiOperation(value = "회원탈퇴", notes = "회원 정보를 삭제합니다.")
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@ApiIgnore @RequestHeader("Authorization") String token){
        return userService.deleteAccount(token)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "닉네임 변경", notes = "닉네임을 변경합니다.")
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateAccount(@ApiIgnore @RequestHeader("Authorization") String token,
                                                @ApiParam(name="User", required=true, value="(required:User)") @RequestBody User user){
        return userService.updateAccount(token, user)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
