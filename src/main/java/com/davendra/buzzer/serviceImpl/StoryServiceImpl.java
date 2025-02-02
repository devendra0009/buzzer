package com.davendra.buzzer.serviceImpl;

import com.davendra.buzzer.dto.request.StoryRequest;
import com.davendra.buzzer.dto.response.StoryResponse;
import com.davendra.buzzer.models.StoryModel;
import com.davendra.buzzer.models.UserModel;
import com.davendra.buzzer.repositories.StoryRepo;
import com.davendra.buzzer.repositories.UserRepo;
import com.davendra.buzzer.services.StoryService;
import com.davendra.buzzer.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepo storyRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StoryResponse createStory(StoryRequest storyRequest) {
        UserModel user = userService.findUserById(storyRequest.getUserId());
        StoryModel story = modelMapper.map(storyRequest, StoryModel.class);
        story.setId(null);
        story.setUser(user);
        story.setActive(true);
        story.setDeactivatedAt(LocalDateTime.now().plusSeconds(storyRequest.getDeactivatedAt() == null ? 20 : storyRequest.getDeactivatedAt()));  // add the story deactivated time , default 20 swconds
        return modelMapper.map(storyRepo.save(story), StoryResponse.class);
    }

    @Override
    public List<StoryResponse> getAllStoryForUser(Long userId) {
        UserModel userModel = userService.findUserById(userId);
        List<Long> usersToSearchStoriesFor = userModel.getFollowings();
        usersToSearchStoriesFor.add(userModel.getId());
        List<StoryModel> storyModelList = storyRepo.findActiveByUserIdIn(usersToSearchStoriesFor);
        List<StoryResponse> storyResponseList = new ArrayList<>();
        storyModelList.forEach(story -> {
            storyResponseList.add(modelMapper.map(story, StoryResponse.class));
        });
        return storyResponseList;
    }

    @Override
    public List<StoryResponse> getAllStoryOfUser(Long userId) {
        List<StoryResponse> storyResponseList = new ArrayList<>();
        storyRepo.findActiveByUserId(userId).forEach(story -> {
            storyResponseList.add(modelMapper.map(story, StoryResponse.class));
        });
        return storyResponseList;
    }

    @Override
    public StoryResponse storySeenByUser(Long storyId, Long userId) {
        StoryModel storyModel = storyRepo.findById(storyId).orElseThrow(() -> new NoSuchElementException("No story found with id " + storyId));
        UserModel userModel = userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException("No user found with id " + userId));
        storyModel.getSeenBy().add(userModel);
        return modelMapper.map(storyRepo.save(storyModel), StoryResponse.class);
    }

    @Scheduled(fixedRate = 60000) // Runs every minute; adjust as needed
    public void deactivateExpiredStories() {
        System.out.println("expiring stories...");
        List<StoryModel> storiesToDeactivate = storyRepo.findByIsActiveTrueAndDeactivateAtBefore(LocalDateTime.now());
        if (storiesToDeactivate.isEmpty()) return;
        storiesToDeactivate.forEach(story -> {
            story.setActive(false);
        });
        storyRepo.saveAll(storiesToDeactivate);
    }
}
