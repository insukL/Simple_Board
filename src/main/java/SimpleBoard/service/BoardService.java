package SimpleBoard.service;

import SimpleBoard.domain.Board;

import java.util.List;

public interface BoardService {
    public boolean createBoard(String token, Board board);
    public Board getBoard(long id);
    public boolean updateBoard(long id, Board board);
    public boolean deleteBoard(long id);
    public List<Board> getBoardList();
}
