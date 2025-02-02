package com.davendra.buzzer.services;

import com.davendra.buzzer.dto.request.CommentRequest;
import com.davendra.buzzer.dto.response.CommentResponse;
import com.davendra.buzzer.models.CommentModel;

import java.util.List;

public interface CommentService {
    public List<CommentModel> getCommentsByUserId(Long userId);
    public List<CommentResponse> getCommentsByPostId(Long postId);
    public CommentModel getCommentsById(Long commentId);

    public CommentResponse createComment(CommentRequest commentReq, Long commentedByUserId);
    public CommentResponse likeComment(Long commentId, Long likedByUserId);
    public String deleteComment(Long commentId, Long commentedByUserId) throws Exception; // only delete your own comments
}
