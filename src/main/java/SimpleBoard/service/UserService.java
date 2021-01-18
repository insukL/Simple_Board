package SimpleBoard.service;

import SimpleBoard.domain.User;

import java.util.List;

public interface UserService {
    public boolean signUp(User user);
    public String login(User user);
    public boolean logout();
    public boolean deleteAccount(String token);
    public boolean updateAccount(String token, User user);
    public User getUser(String token);
}
