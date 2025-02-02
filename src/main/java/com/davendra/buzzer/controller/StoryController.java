package com.davendra.buzzer.controller;


import com.davendra.buzzer.dto.request.StoryRequest;
import com.davendra.buzzer.dto.response.StoryResponse;
import com.davendra.buzzer.models.StoryModel;
import com.davendra.buzzer.services.StoryService;
import com.davendra.buzzer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/story")
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<StoryResponse> createStory(@RequestBody StoryRequest storyRequest, @RequestHeader("Authorization") String token) {
        storyRequest.setUserId(userService.getUserFromToken(token).getId());
        StoryResponse createdStory = storyService.createStory(storyRequest);
        return ResponseEntity.ok(createdStory);
    }


    @GetMapping("/for/user")
    public ResponseEntity<List<StoryResponse>> getAllStoryForUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.of(Optional.ofNullable(storyService.getAllStoryForUser(userService.getUserFromToken(token).getId())));
    }

    @GetMapping("/of/user/{userId}")
    public ResponseEntity<List<StoryResponse>> getAllStoryOfUser(@PathVariable Long userId) {
        return ResponseEntity.of(Optional.ofNullable(storyService.getAllStoryOfUser(userId)));
    }

    // will optimize it -> seen by story srchitecture
    @PutMapping("/user/seen/update/{storyId}")
    public ResponseEntity<StoryResponse> updateStorySeenByUser(@PathVariable Long storyId, @RequestHeader("Authorization") String token) {
        return ResponseEntity.of(Optional.ofNullable(storyService.storySeenByUser(storyId, userService.getUserFromToken(token).getId())));
    }
}
