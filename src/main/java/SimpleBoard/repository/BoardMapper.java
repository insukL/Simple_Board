package SimpleBoard.repository;

import SimpleBoard.domain.Board;
import org.apache.ibatis.annotations.Delete;
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
    public boolean softDeleteBoard(Long id);

    @Update(value = "update boards set deleted = 0, updated_at=now() where id = #{id}")
    public boolean restoreBoard(Long id);

    @Delete(value = "delete from boards where id = #{id}")
    public boolean hardDeleteBoard(Long id);

    @Select(value = "select * from boards join users on boards.author_id = users.id where boards.id = #{id}")
    public Board getBoard(Long id);

    @Select(value = "select * from boards join users on boards.author_id = users.id where deleted = 0 order by boards.id desc")
    public List<Board> getBoardList();
}

