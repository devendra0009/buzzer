package com.davendra.buzzer.controller;

import com.davendra.buzzer.dto.request.PostRequest;
import com.davendra.buzzer.dto.response.PostResponse;
import com.davendra.buzzer.models.PostModel;
import com.davendra.buzzer.services.PostService;
import com.davendra.buzzer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestHeader("Authorization") String token,
                                                   @RequestParam(name = "caption", required = false) String caption,
                                                   @RequestParam(name = "tags", required = false) List<String> tags,
                                                   @RequestParam(name = "location", required = false) String location,
                                                   @RequestParam(name = "usersTagged", required = false) List<Long> usersTagged,
                                                   @RequestPart("mediaFiles") List<MultipartFile> mediaFiles) throws IOException {
        PostRequest postRequest = PostRequest.builder().caption(caption).tags(tags).location(location).usersTagged(usersTagged).mediaFiles(mediaFiles).build();
        PostResponse createdPost = postService.createPost(postRequest, userService.getUserFromToken(token).getId());
        return ResponseEntity.ok(createdPost);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@RequestHeader("Authorization") String token, @PathVariable Long postId) {
        try {
            String result = postService.deletePost(postId, userService.getUserFromToken(token).getId());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostModel>> findPostsByUserId(@PathVariable Long userId) {
        List<PostModel> userPosts = postService.findPostsByUserId(userId);
        return ResponseEntity.ok(userPosts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostModel> findPostById(@PathVariable Long postId) {
        PostModel post = postService.findPostsById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostModel>> findAllPosts() {
        List<PostModel> allPosts = postService.findAllPost();
        return ResponseEntity.ok(allPosts);
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<PostResponse> likePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(postService.likePost(userService.getUserFromToken(token).getId(), postId));
    }

    @PostMapping("/save/{postId}")
    public ResponseEntity<PostResponse> savePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) {
        PostResponse response = postService.savePost(userService.getUserFromToken(token).getId(), postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/saved/get/{userId}")
    public ResponseEntity<List<PostResponse>> getAllSavedPostByUserId(@PathVariable Long userId) {
        List<PostResponse> response = postService.getAllSavedPostByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
