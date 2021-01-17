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

    @Select(value = "select boards.*, users.nickname from boards join users on boards.author_id = users.id where boards.id = #{id} and boards.deleted = 0")
    public Board getBoard(Long id);

    @Select(value = "select boards.*, users.nickname from boards join users on boards.author_id = users.id " +
            "where boards.deleted = 0 order by boards.id desc limit #{offset}, 10")
    public List<Board> getBoardList(long offset);

    @Update(value = "update boards set views = views + 1 where id = #{id}")
    public boolean countViews(long id);

    @Update(value = "update boards set recommend_num = recommend_num + 1 where id = #{id}")
    public boolean plusRecommendNum(long id);

    @Update(value = "update boards set recommend_num = recommend_num - 1 where id = #{id}")
    public boolean minusRecommendNum(long id);

    @Update(value = "update boards set comment_num = comment_num + 1 where id = #{id}")
    public boolean plusCommentNum(long id);

    @Update(value = "update boards set comment_num = comment_num - 1 where id = #{id}")
    public boolean minusCommentNum(long id);
}

