package SimpleBoard.service;

import SimpleBoard.domain.User;
import SimpleBoard.repository.UserMapper;
import SimpleBoard.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public boolean signUp(User user){
        if(userMapper.getUserByAccount(user.getAccount()) != null) return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.createUser(user);
    }

    public String login(User user){
        User userInfo = userMapper.getUserByAccount(user.getAccount());
        if(!bCryptPasswordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
            return "Login Fail";
        }
        return jwtUtil.createToken(userInfo.getId());
    }

    public boolean logout(){ return false; }

    public User getUser(long id){ return userMapper.getUserByID(id); }

    public List<User> getUserList(){ return userMapper.getUserList(); }
}