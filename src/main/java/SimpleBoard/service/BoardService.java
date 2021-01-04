package SimpleBoard.service;

import SimpleBoard.domain.Board;

import java.util.List;

public interface BoardService {
    public boolean createBoard(Board board);
    public Board getBoard(Long id);
    public boolean updateBoard(Board board);
    public boolean deleteBoard(Long id);
    public boolean restoreBoard(Long id);
    public List<Board> getBoardList();
}
