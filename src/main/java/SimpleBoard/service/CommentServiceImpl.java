package SimpleBoard.service;

import SimpleBoard.domain.Comment;
import SimpleBoard.repository.BoardMapper;
import SimpleBoard.repository.CommentMapper;
import SimpleBoard.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public boolean createComment(String token, long articleId, Comment comment){
        comment.setArticle_id(articleId);
        comment.setAuthor_id(jwtUtil.getIdByToken(token));
        boardMapper.plusCommentNum(articleId);
        return commentMapper.createComment(comment);
    }

    public List<Comment> getComment(long articleId){ return commentMapper.getComment(articleId); }

    public boolean updateComment(long id, Comment comment){
        comment.setId(id);
        return commentMapper.updateComment(comment);
    }

    @Transactional
    public boolean deleteComment(long id){
        boardMapper.minusCommentNum(id);
        return commentMapper.deleteComment(id);
    }
}
