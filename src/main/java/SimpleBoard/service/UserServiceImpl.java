package SimpleBoard.service;

import SimpleBoard.domain.User;
import SimpleBoard.exception.EmptyStringException;
import SimpleBoard.repository.UserMapper;
import SimpleBoard.util.JwtUtil;
import SimpleBoard.util.SlackSender;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SlackSender slackSender;

    public boolean signUp(User user) throws EmptyStringException{
        if(user.getAccount().trim().length() <= 0 || user.getPassword().trim().length() <= 0 || user.getNickname().trim().length() <= 0)
            throw new EmptyStringException("입력되지 않은 항목이 있습니다.");
        if(userMapper.getUserByAccount(user.getAccount()) != null) return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        slackSender.noticeSignUp(user.getAccount());

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
        slackSender.noticeWithdrawal(userMapper.getUserByID(jwtUtil.getIdByToken(token)).getAccount());
        return userMapper.deletedUser(jwtUtil.getIdByToken(token));
    }

    public boolean updateAccount (String token, User user) throws EmptyStringException{
        if(user.getNickname().trim().length() <= 0) throw new EmptyStringException("닉네임이 입력되지 않았습니다");
        user.setId(jwtUtil.getIdByToken(token));
        return userMapper.updateUser(user);
    }

    public User getUser(String token){
        return userMapper.getUserByID(jwtUtil.getIdByToken(token));
    }
}