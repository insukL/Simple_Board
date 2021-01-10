package SimpleBoard.service;

import SimpleBoard.domain.Board;
import SimpleBoard.repository.BoardMapper;
import SimpleBoard.repository.UserMapper;
import SimpleBoard.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    public boolean createBoard(String token, Board board) throws IllegalArgumentException {
        if(board.getContent().trim().length() <= 0 || board.getTitle().trim().length() <= 0)
            throw new IllegalArgumentException("Please enter title and content.");
        board.setAuthor_id(jwtUtil.getIdByToken(token));
        return boardMapper.createBoard(board);
    }

    public Board getBoard(Long id){
        return boardMapper.getBoard(id);
    }

    public boolean updateBoard(String token, Board board) throws IllegalArgumentException {
        if(board.getContent().trim().length() <= 0 || board.getTitle().trim().length() <= 0)
            throw new IllegalArgumentException("Please enter title and content.");
        if(jwtUtil.getIdByToken(token) != board.getAuthor_id()) throw new IllegalArgumentException();
        return boardMapper.updateBoard(board);
    }

    public boolean deleteBoard(String token, Long id) throws Exception {
        if(jwtUtil.getIdByToken(token) != boardMapper.getBoard(id).getAuthor_id()) throw new Exception();
        return boardMapper.softDeleteBoard(id);
    }

    public boolean restoreBoard(String token, Long id) throws Exception{
        if(jwtUtil.getIdByToken(token) != boardMapper.getBoard(id).getAuthor_id()) throw new Exception();
        return boardMapper.restoreBoard(id);
    }

    public List<Board> getBoardList(){
        return boardMapper.getBoardList();
    }
}
