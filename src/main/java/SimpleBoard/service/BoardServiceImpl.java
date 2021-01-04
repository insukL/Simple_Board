package SimpleBoard.service;

import SimpleBoard.domain.Board;
import SimpleBoard.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;

    public boolean createBoard(Board board){
        //jwt 관련 메소드 작업 이후 변경 예정
        //User 관련 시스템 만든 이후에 추가
        board.setAuthor_id("111");
        return boardMapper.createBoard(board);
    }

    public Board getBoard(Long id){
        //별명 관련 정보도 같이 보내야 함
        //User 관련 시스템 만든 이후에 추가
        return boardMapper.getBoard(id);
    }

    public boolean updateBoard(Board board){
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
