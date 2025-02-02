package com.davendra.buzzer.serviceImpl;

import com.davendra.buzzer.dto.request.PostRequest;
import com.davendra.buzzer.dto.response.AuthResponse;
import com.davendra.buzzer.dto.response.PostResponse;
import com.davendra.buzzer.models.PostModel;
import com.davendra.buzzer.models.UserModel;
import com.davendra.buzzer.repositories.PostRepo;
import com.davendra.buzzer.repositories.UserRepo;
import com.davendra.buzzer.services.CloudinaryUploadService;
import com.davendra.buzzer.services.PostService;
import com.davendra.buzzer.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CloudinaryUploadService cloudinaryUploadService;


    @Override
    public PostResponse createPost(PostRequest postReq, Long userId) throws IOException {
        UserModel user = userService.findUserById(userId);
        PostModel postModel = new PostModel();
        modelMapper.map(postReq, postModel);

        // here error may occur
        postModel.setUser(user);
        if (postReq.getUsersTagged() != null && !postReq.getUsersTagged().isEmpty()) {
            List<UserModel> taggedUesrs = userService.findAllUsersById(postReq.getUsersTagged()); // Assuming it fetches users based on the IDs
            postModel.setUsersTagged(taggedUesrs);
        }

        // get image and video link
        if (postReq.getMediaFiles() != null && !postReq.getMediaFiles().isEmpty()) {
            // get image and video link only if media files exist
            List<String> urls = cloudinaryUploadService.uploadMultipleFiles(postReq.getMediaFiles());
            postModel.setMediaFiles(urls);
        }

        PostModel savedPost = postRepository.save(postModel);
        PostResponse postResponse = new PostResponse();
        modelMapper.map(savedPost, postResponse);
        return postResponse;
    }

    @Override
    public String deletePost(Long postId, Long userId) throws Exception {
        PostModel post = findPostsById(postId);
        UserModel user = userService.findUserById(userId);
        if (!Objects.equals(post.getUser().getId(), user.getId())) {
            throw new Exception("You can only delete your posts!");
        }
        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<PostModel> findPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public PostModel findPostsById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    @Override
    public List<PostModel> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public PostResponse likePost(Long userId, Long postId) {
        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        UserModel user = userService.findUserById(userId);

        // just stored ids of user for now, whenever we want to see the users who had liked the post, we can call an api and pass this getLikedBy array
        if (post.getLikedBy().contains(user)) {
            post.getLikedBy().remove(user);
        } else {
            post.getLikedBy().add(user);
        }
        PostModel savedPost = postRepository.save(post);
        PostResponse postResponse = new PostResponse();
        modelMapper.map(savedPost, postResponse);
        return postResponse;
    }

    @Override
    public PostResponse savePost(Long userId, Long postId) {
        PostModel post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        UserModel user = userService.findUserById(userId);

        if (post.getSavedBy().contains(user)) {
            post.getSavedBy().remove(user);
        } else {
            post.getSavedBy().add(user);
        }
        return modelMapper.map(postRepository.save(post), PostResponse.class);
    }

    @Override
    public List<PostResponse> getAllSavedPostByUserId(Long userId) {
        List<PostModel> post = postRepository.findBySavedBy(userId);
        List<PostResponse> postResponses = new ArrayList<>();
        post.forEach(p -> {
            postResponses.add(modelMapper.map(p, PostResponse.class));
        });
        return postResponses;
    }
}
