package SimpleBoard.repository;

import SimpleBoard.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select(value = "select * from users")
    public List<User> getUserList();
}
