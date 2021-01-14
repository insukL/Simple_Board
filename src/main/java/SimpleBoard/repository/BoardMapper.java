package SimpleBoard.repository;

import SimpleBoard.domain.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    @Insert(value = "insert into boards(title, content, author_id) values(#{title}, #{content}, #{author_id})")
    public boolean createBoard(Board board);

    @Update(value = "update boards set title = #{title}, content=#{content}, updated_at=now() where id = #{id}")
    public boolean updateBoard(Board board);

    @Update(value = "update boards set deleted = 1, updated_at=now() where id = #{id}")
    public boolean deleteBoard(Long id);

    @Select(value = "select * from boards join users on boards.author_id = users.id where boards.id = #{id} and boards.deleted = 0")
    public Board getBoard(Long id);

    @Select(value = "select * from boards join users on boards.author_id = users.id where boards.deleted = 0 order by boards.id desc")
    public List<Board> getBoardList();
}

