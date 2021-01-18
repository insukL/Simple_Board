package SimpleBoard.controller;

import SimpleBoard.annotation.NoLogin;
import SimpleBoard.annotation.NoPermission;
import SimpleBoard.domain.Board;
import SimpleBoard.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    //게시글 읽기
    @NoLogin
    @NoPermission
    @ResponseBody
    @ApiOperation(value = "게시글 읽기", notes = "게시글을 읽어옵니다.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Board> read(@ApiParam(name="id", required=true, value="(required:id)") @PathVariable("id") long id){
        return new ResponseEntity<Board>(boardService.getBoard(id), HttpStatus.OK);
    }

    //게시글 작성 기능
    @ResponseBody
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성합니다.")
    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<String> write(@ApiIgnore @RequestHeader("Authorization") String token,
                                        @ApiParam(name="Board", required=true, value="(required:board)") @RequestBody Board board){
        return boardService.createBoard(token, board)
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //게시글 수정
    @ResponseBody
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @RequestMapping(value="/{id}", method=RequestMethod.PATCH)
    public ResponseEntity<String> update(@ApiParam(name="id", required=true, value="(required:id)") @PathVariable("id") long id,
                                         @ApiParam(name="Board", required=true, value="(required:Board)") @RequestBody Board board){
        return boardService.updateBoard(id, board)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //게시글 삭제
    @ResponseBody
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> delete(@ApiParam(name="id", required=true, value="(required:id)") @PathVariable("id") long id){
        return boardService.deleteBoard(id)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //게시글 목록 가져오기
    @NoLogin
    @NoPermission
    @ResponseBody
    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 읽어옵니다.")
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ResponseEntity<List<Board>> getList(@ApiParam(name="page", required=true, value="(required:page)")
                                                   @RequestParam(value = "page", required = false, defaultValue = "1") long page){
        return new ResponseEntity<List<Board>>(boardService.getBoardList(page), HttpStatus.OK);
    }

    //게시글 추천
    @NoPermission
    @ResponseBody
    @ApiOperation(value = "게시글 추천", notes = "게시글 추천 상태를 변경합니다.")
    @RequestMapping(value = "/{id}/recommend/", method = RequestMethod.POST)
    public ResponseEntity<String> recommend(@ApiIgnore @RequestHeader("Authorization") String token,
                                            @ApiParam(name="id", required=true, value="(required:id)") @PathVariable long id){
        return boardService.changeRecommendState(token, id)
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}