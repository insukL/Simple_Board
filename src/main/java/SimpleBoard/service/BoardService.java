package SimpleBoard.service;

import SimpleBoard.domain.Board;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

public interface BoardService {
    public boolean createBoard(Board board) throws IllegalArgumentException;
    public Board getBoard(Long id);
    public boolean updateBoard(Board board) throws IllegalArgumentException;
    public boolean deleteBoard(Long id);
    public boolean restoreBoard(Long id);
    public List<Board> getBoardList();
}
