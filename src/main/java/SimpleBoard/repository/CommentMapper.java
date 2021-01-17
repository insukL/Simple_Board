package SimpleBoard.repository;

import SimpleBoard.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    @Insert(value = "insert into comments(article_id, author_id, content, parent) " +
            "values(#{article_id}, #{author_id}, #{content}, #{parent})")
    public boolean createComment(Comment comment);

    @Select(value = "select comments.*, users.nickname from comments " +
            "join users on comments.author_id = users.id " +
            "where article_id = #{article_id} and comments.deleted = 0 " +
            "order by if(parent = 0, comments.id, parent)")
    public List<Comment> getComment(long article_id);

    @Update(value = "update comments set deleted = 1, updated_at = now() where id = #{id}")
    public boolean deleteComment(long id);

    @Update(value = "update comments set content = #{content}, updated_at = now() where id = #{id}")
    public boolean updateComment(Comment comment);

    @Select(value = "select * from comments where deleted = 0 and id = #{id}")
    public Comment getCommentById(long id);
}
