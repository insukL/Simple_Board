package SimpleBoard.controller;

import SimpleBoard.domain.Board;
import SimpleBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    //게시글 읽기 페이지
    @RequestMapping(value = "/board/read", method = RequestMethod.GET)
    public String read(){ return "board/read"; }
    //게시글 읽기
    @ResponseBody
    @RequestMapping(value = "/board/{id}", method = RequestMethod.GET)
    public ResponseEntity<Board> read(@PathVariable("id") long id){
        Board board = boardService.getBoard(id);
        board.setNickname("추가예정");
        return new ResponseEntity<Board>(board, HttpStatus.OK);
    }

    //게시글 작성 페이지
    @RequestMapping(value="/board/write", method = RequestMethod.GET)
    public String write(){ return "board/write";}
    //게시글 작성 기능
    @ResponseBody
    @RequestMapping(value="/board/write", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> write(@RequestBody Board board){
        return boardService.createBoard(board)
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //게시글 수정 페이지
    @RequestMapping(value="/board/update", method = RequestMethod.GET)
    public String update(){return "/board/update";}
    //게시글 수정
    @ResponseBody
    @RequestMapping(value="/board/{id}", method=RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody Board board){
        return boardService.updateBoard(board)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.OK);
    }

    //게시글 삭제
    @RequestMapping(value="/board/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") long id){
        return boardService.deleteBoard(id)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.OK);
    }

    //게시글 목록 가져오기
    @ResponseBody
    @RequestMapping(value="/board/list", method=RequestMethod.GET)
    public ResponseEntity<List<Board>> getList(){
        return new ResponseEntity<List<Board>>(boardService.getBoardList(), HttpStatus.OK);
    }
}