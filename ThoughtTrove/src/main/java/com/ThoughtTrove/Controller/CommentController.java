package com.ThoughtTrove.Controller;

import com.ThoughtTrove.Dto.Request.CommentDto;
import com.ThoughtTrove.Dto.Response.ApiResponse;
import com.ThoughtTrove.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
   @PostMapping("/post/{postId}/comments")
    public ResponseEntity createComment(@RequestBody CommentDto commentDto ,@PathVariable("postId")  int postId){

        CommentDto dtoComment = commentService.createComment(commentDto , postId);
        return  new ResponseEntity<>(dtoComment , HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer commentId){
       commentService.deletePost(commentId);
        return  new ResponseEntity<>(new ApiResponse("Comment has been Deleted" , true), HttpStatus.OK);
    }
}
