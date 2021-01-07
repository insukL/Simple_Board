package SimpleBoard.service;

import SimpleBoard.domain.User;
import SimpleBoard.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public boolean signUp(User user){
        if(userMapper.getUser(user.getAccount()) != null) return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.createUser(user);
    }

    public boolean login(User user){
        if(!bCryptPasswordEncoder.matches(user.getPassword(), userMapper.getUser(user.getAccount()).getPassword())){
            return false;
        }
        return true;
    }

    public boolean logout(){ return false; }

    public User getUser(long id){ return userMapper.getUser(id); }

    public List<User> getUserList(){ return userMapper.getUserList(); }
}