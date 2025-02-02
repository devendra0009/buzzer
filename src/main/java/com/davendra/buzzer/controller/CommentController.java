package com.davendra.buzzer.controller;


import com.davendra.buzzer.dto.request.CommentRequest;
import com.davendra.buzzer.dto.response.CommentResponse;
import com.davendra.buzzer.models.CommentModel;
import com.davendra.buzzer.services.CommentService;
import com.davendra.buzzer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    // Get all comments by User ID
    @GetMapping("/user")
    public ResponseEntity<List<CommentModel>> getCommentsByUserId(@RequestHeader("Authorization") String token) {
        Long userId = userService.getUserFromToken(token).getId();
        List<CommentModel> comments = commentService.getCommentsByUserId(userId);
        return ResponseEntity.ok(comments);
    }

    // Get all comments by post ID
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPostId( @PathVariable("postId") Long postId) {
//        Long userId = userService.getUserFromToken(token).getId();
        List<CommentResponse> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    // Get comment by Comment ID
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentModel> getCommentById(@PathVariable Long commentId) {
        CommentModel comment = commentService.getCommentsById(commentId);
        return ResponseEntity.ok(comment);
    }

    // Create a new comment
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestHeader("Authorization") String token,
            @RequestBody CommentRequest commentReq) {
        Long userId = userService.getUserFromToken(token).getId();
        CommentResponse createdComment = commentService.createComment(commentReq, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    // Like a comment
    @PostMapping("/like/{commentId}")
    public ResponseEntity<CommentResponse> likeComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String token) {
        Long userId = userService.getUserFromToken(token).getId();
        CommentResponse response = commentService.likeComment(commentId, userId);
        return ResponseEntity.ok(response);
    }

    // Delete a comment
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String token) throws Exception {
        Long userId = userService.getUserFromToken(token).getId();
        String response = commentService.deleteComment(commentId, userId);
        return ResponseEntity.ok(response);
    }
}
