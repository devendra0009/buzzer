package com.davendra.buzzer.services;

import com.davendra.buzzer.dto.request.PostRequest;
import com.davendra.buzzer.dto.response.PostResponse;
import com.davendra.buzzer.models.PostModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    public PostResponse createPost(PostRequest post, Long userId) throws IOException;

    public String deletePost(Long postId, Long userId) throws Exception;

    public List<PostModel> findPostsByUserId(Long userId);

    public PostModel findPostsById(Long postId);

    public List<PostModel> findAllPost();

    public PostResponse likePost(Long userId, Long postId);

    public PostResponse savePost(Long userId, Long postId);
    public List<PostResponse> getAllSavedPostByUserId(Long userId);

//    public PostModel updatePost(PostModel post, Long postId);
}
