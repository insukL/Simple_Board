package SimpleBoard.repository;

import SimpleBoard.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Insert(value = "insert into users(account, password, nickname) values(#{account}, #{password}, #{nickname})")
    public boolean createUser(User user);

    @Select(value = "select id, account, nickname, created_at, updated_at from users where deleted = 0 and id = #{id}")
    public User getUserByID(Long id);

    @Select(value = "select * from users where deleted = 0 and account = #{account}")
    public User getUserByAccount(String account);

    @Update(value = "update users set deleted = 1, updated_at=now() where id = #{id}")
    public boolean deletedUser(Long id);

    @Update(value = "update users set nickname = #{nickname}, updated_at=now() where id = #{id}")
    public boolean updateUser(User user);
}
