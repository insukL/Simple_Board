package SimpleBoard.service;

import SimpleBoard.domain.Board;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

public interface BoardService {
    public boolean createBoard(String token, Board board) throws IllegalArgumentException;
    public Board getBoard(Long id);
    public boolean updateBoard(String token, Board board) throws IllegalArgumentException;
    public boolean deleteBoard(String token, Long id) throws Exception;
    public boolean restoreBoard(String token, Long id) throws Exception;
    public List<Board> getBoardList();
}
