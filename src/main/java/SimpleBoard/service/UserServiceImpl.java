package SimpleBoard.service;

import SimpleBoard.domain.User;
import SimpleBoard.repository.UserMapper;
import SimpleBoard.util.DateTimeUtil;
import SimpleBoard.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public boolean signUp(User user){
        if(userMapper.getUser(user.getAccount()) != null) return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.createUser(user);
    }

    public String login(User user){
        User userInfo = userMapper.getUser(user.getAccount());
        if(!bCryptPasswordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
            return "Fail";
        }
        return "jwtUtil.createToken(user.getId())";
    }

    public boolean logout(){ return false; }

    public User getUser(long id){ return userMapper.getUser(id); }

    public List<User> getUserList(){ return userMapper.getUserList(); }
}