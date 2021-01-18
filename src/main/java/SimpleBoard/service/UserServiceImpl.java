package SimpleBoard.service;

import SimpleBoard.domain.User;
import SimpleBoard.exception.EmptyStringException;
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

    public boolean signUp(User user) throws EmptyStringException{
        if(user.getAccount().trim().length() <= 0 || user.getPassword().trim().length() <= 0 || user.getNickname().trim().length() <= 0)
            throw new EmptyStringException("입력되지 않은 항목이 있습니다.");
        if(userMapper.getUserByAccount(user.getAccount()) != null) return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.createUser(user);
    }

    public String login(User user){
        User userInfo = userMapper.getUserByAccount(user.getAccount());
        if(userInfo == null || !bCryptPasswordEncoder.matches(user.getPassword(), userInfo.getPassword())) {
            return "Login Fail";
        }
        return jwtUtil.createToken(userInfo.getId());
    }

    public boolean deleteAccount(String token){
        return userMapper.deletedUser(jwtUtil.getIdByToken(token));
    }

    public boolean updateAccount (String token, User user) throws EmptyStringException{
        if(user.getNickname().trim().length() <= 0) throw new EmptyStringException("닉네임이 입력되지 않았습니다");
        user.setId(jwtUtil.getIdByToken(token));
        return userMapper.updateUser(user);
    }

    public boolean logout(){ return false; }

    public User getUser(String token){
        User user = userMapper.getUserByID(jwtUtil.getIdByToken(token));
        user.setPassword("");
        return user;
    }

    public List<User> getUserList(){ return userMapper.getUserList(); }
}