package com.davendra.buzzer.services;

import com.davendra.buzzer.dto.request.StoryRequest;
import com.davendra.buzzer.dto.response.StoryResponse;
import com.davendra.buzzer.models.StoryModel;

import java.util.List;

public interface StoryService {
    public StoryResponse createStory(StoryRequest story);
    public List<StoryResponse> getAllStoryForUser(Long userId);
    public List<StoryResponse> getAllStoryOfUser(Long userId);
    public StoryResponse storySeenByUser(Long storyId, Long userId);
}
