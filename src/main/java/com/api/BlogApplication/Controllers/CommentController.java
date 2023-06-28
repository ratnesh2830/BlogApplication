package com.api.BlogApplication.Controllers;

import com.api.BlogApplication.Entities.Comment;
import com.api.BlogApplication.Payloads.CommentDto;
import com.api.BlogApplication.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody Comment comment, @PathVariable int postId)
    {
        CommentDto commentDto = commentService.createComment(comment,postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId)
    {
        commentService.delete(commentId);
        return ResponseEntity.ok().body("Comment successfully deleted");
    }
}
