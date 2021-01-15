package SimpleBoard.service;

import SimpleBoard.domain.Comment;

import java.util.List;

public interface CommentService {
    public boolean createComment(String token, long articleId, Comment comment);
    public List<Comment> getComment(long articleId);
    public boolean updateComment(long id, Comment comment);
    public boolean deleteComment(long id);
}
