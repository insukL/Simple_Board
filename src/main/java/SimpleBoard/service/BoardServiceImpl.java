package SimpleBoard.service;

import SimpleBoard.domain.Board;
import SimpleBoard.repository.BoardMapper;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;

    public boolean createBoard(Board board) throws IllegalArgumentException {
        if(board.getContent().trim().length() <= 0 || board.getTitle().trim().length() <= 0)
            throw new IllegalArgumentException("Please enter title and content.");
        //jwt 관련 메소드 작업 이후 변경 예정
        //User 관련 시스템 만든 이후에 추가
        board.setAuthor_id(1);
        return boardMapper.createBoard(board);
    }

    public Board getBoard(Long id){
        return boardMapper.getBoard(id);
    }

    public boolean updateBoard(Board board) throws IllegalArgumentException {
        if(board.getContent().trim().length() <= 0 || board.getTitle().trim().length() <= 0)
            throw new IllegalArgumentException("Please enter title and content.");
        return boardMapper.updateBoard(board);
    }

    public boolean deleteBoard(Long id){
        return boardMapper.softDeleteBoard(id);
    }

    public boolean restoreBoard(Long id) { return boardMapper.restoreBoard(id);}

    public List<Board> getBoardList(){
        return boardMapper.getBoardList();
    }
}
