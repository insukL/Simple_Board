package SimpleBoard.controller;

import SimpleBoard.annotation.NoLogin;
import SimpleBoard.annotation.NoPermission;
import SimpleBoard.domain.Comment;
import SimpleBoard.service.CommentService;
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
public class CommentController {
    @Autowired
    private CommentService commentService;

    @NoLogin
    @NoPermission
    @ApiOperation(value = "댓글 읽기", notes = "댓글을 읽어옵니다.")
    @ResponseBody
    @RequestMapping(value = "board/{id}/comment", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getComment(@ApiParam(name="id", required=true, value="(required:id)") @PathVariable long id){
        return new ResponseEntity<List<Comment>>(commentService.getComment(id), HttpStatus.OK);
    }
    
    @ResponseBody
    @ApiOperation(value = "댓글 작성", notes = "댓글을 읽어옵니다. parent를 담아서 보내면 대댓글, 아무것도 담겨있지 않다면 일반 댓글")
    @RequestMapping(value = "board/{id}/comment", method = RequestMethod.POST)
    public ResponseEntity<String> createComment(@ApiIgnore @RequestHeader("Authorization") String token,
                                                @ApiParam(name="id", required=true, value="(required:id)") @PathVariable long id,
                                                @ApiParam(name="comment", required=true, value="(required:comment)") @RequestBody Comment comment){
        return commentService.createComment(token, id, comment)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정합니다.")
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateComment(@ApiParam(name="id", required=true, value="(required:id)") @PathVariable long id,
                                                @ApiParam(name="comment", required=true, value="(required:comment)") @RequestBody Comment comment){
        return commentService.updateComment(id, comment)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제합니다.")
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteComment(@ApiParam(name="id", required=true, value="(required:id)") @PathVariable long id){
        return commentService.deleteComment(id)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
