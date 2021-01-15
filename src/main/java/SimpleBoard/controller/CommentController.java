package SimpleBoard.controller;

import SimpleBoard.annotation.NoLogin;
import SimpleBoard.annotation.NoPermission;
import SimpleBoard.domain.Comment;
import SimpleBoard.service.CommentService;
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
    @ResponseBody
    @RequestMapping(value = "board/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getComment(@PathVariable long id){
        return new ResponseEntity<List<Comment>>(commentService.getComment(id), HttpStatus.OK);
    }

    //comment에 parent를 담아서 보내면 reply(대댓글), 아무것도 담겨있지 않다면 일반 댓글
    @ResponseBody
    @RequestMapping(value = "board/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<String> createComment(@ApiIgnore @RequestHeader("Authorization") String token,
                                                @PathVariable long id,
                                                @RequestBody Comment comment){
        return commentService.createComment(token, id, comment)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateComment(@PathVariable long id,
                                                @RequestBody Comment comment){
        return commentService.updateComment(id, comment)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        return commentService.deleteComment(id)
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
