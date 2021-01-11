package SimpleBoard.service;

import SimpleBoard.domain.Board;
import SimpleBoard.repository.BoardMapper;
import SimpleBoard.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private JwtUtil jwtUtil;

    public boolean createBoard(String token, Board board){
        board.setAuthor_id(jwtUtil.getIdByToken(token));
        return boardMapper.createBoard(board);
    }

    public Board getBoard(long id){
        return boardMapper.getBoard(id);
    }

    public boolean updateBoard(Board board){
        return boardMapper.updateBoard(board);
    }

    public boolean deleteBoard(long id){
        return boardMapper.softDeleteBoard(id);
    }

    public boolean restoreBoard(long id){
        return boardMapper.restoreBoard(id);
    }

    public List<Board> getBoardList(){
        return boardMapper.getBoardList();
    }
}
