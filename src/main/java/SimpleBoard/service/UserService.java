package SimpleBoard.service;

import SimpleBoard.domain.User;

import java.util.List;

public interface UserService {
    public boolean signUp(User user);
    public String login(User user);
    public boolean logout();
    public User getUser(long id);
    public List<User> getUserList();
}
