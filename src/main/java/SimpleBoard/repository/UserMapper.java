package SimpleBoard.repository;

import SimpleBoard.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Insert(value = "insert into users(account, password, nickname) values(#{account}, #{password}, #{nickname})")
    public boolean createUser(User user);

    @Select(value = "select * from users where id = #{id}")
    public User getUser(Long id);

    @Select(value = "select * from users where account = #{account}")
    public User getUser(String account);

    @Select(value = "select * from users")
    public List<User> getUserList();
}
