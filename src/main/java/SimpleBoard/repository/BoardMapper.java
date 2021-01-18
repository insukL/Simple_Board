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

    @Select(value = "select boards.id, title, content, author_id, views, boards.created_at, boards.updated_at, users.nickname," +
            "(select count(*) from recommend where boards.id = recommend.article_id) as recommend_num," +
            "(select count(*) from comments where boards.id = comments.article_id and comments.deleted = 0) as comment_num " +
            "from boards join users on boards.author_id = users.id where boards.id = #{id} and boards.deleted = 0")
    public Board getBoard(Long id);

    @Select(value = "select boards.id, title, author_id, views, boards.created_at, boards.updated_at, users.nickname," +
            "(select count(*) from recommend where boards.id = recommend.article_id) as recommend_num," +
            "(select count(*) from comments where boards.id = comments.article_id and comments.deleted = 0) as comment_num " +
            "from boards join users on boards.author_id = users.id " +
            "where boards.deleted = 0 order by boards.id desc limit #{offset}, 10")
    public List<Board> getBoardList(long offset);

    @Update(value = "update boards set views = views + 1 where id = #{id}")
    public boolean countViews(long id);
}

