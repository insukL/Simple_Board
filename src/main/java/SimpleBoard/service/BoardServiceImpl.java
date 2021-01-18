package SimpleBoard.service;

import SimpleBoard.domain.Board;
import SimpleBoard.domain.Recommend;
import SimpleBoard.repository.BoardMapper;
import SimpleBoard.repository.RecommendMapper;
import SimpleBoard.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private JwtUtil jwtUtil;

    public boolean createBoard(String token, Board board){
        board.setAuthor_id(jwtUtil.getIdByToken(token));
        return boardMapper.createBoard(board);
    }

    @Transactional
    public Board getBoard(long id){
        boardMapper.countViews(id);
        return boardMapper.getBoard(id);
    }

    public boolean updateBoard(long id, Board board){
        board.setId(id);
        return boardMapper.updateBoard(board);
    }

    public boolean deleteBoard(long id){
        return boardMapper.deleteBoard(id);
    }

    public List<Board> getBoardList(long page){
        return boardMapper.getBoardList((page - 1) * 10);
    }

    @Transactional
    public boolean changeRecommendState(String token, long id){
        Recommend recommend = new Recommend();
        recommend.setArticle_id(id);
        recommend.setAuthor_id(jwtUtil.getIdByToken(token));
        if(recommendMapper.findRecommend(recommend) >= 1 ){
            recommendMapper.deleteRecommend(recommend);
            boardMapper.minusRecommendNum(id);
        } else {
            recommendMapper.createRecommend(recommend);
            boardMapper.plusRecommendNum(id);
        }
        return true;
    }
}
