package com.davendra.buzzer.serviceImpl;

import com.davendra.buzzer.dto.request.ReelRequest;
import com.davendra.buzzer.models.ReelModel;
import com.davendra.buzzer.repositories.ReelsRepo;
import com.davendra.buzzer.services.CloudinaryUploadService;
import com.davendra.buzzer.services.ReelsService;
import com.davendra.buzzer.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Autowired
    private ReelsRepo reelsRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryUploadService cloudinaryUploadService;

    @Override
    public ReelModel createReel(ReelRequest reelRequest, Long userId) throws IOException {
        ReelModel reel = new ReelModel();
        modelMapper.map(reelRequest,reel);
        // here error may occur
        reel.setUser(userService.findUserById(userId));
        reel.setVideo(cloudinaryUploadService.uploadFile(reelRequest.getVideo()));
        return reelsRepo.save(reel);
    }

    @Override
    public List<ReelModel> getAllReels() {
        return reelsRepo.findAll();
    }

    @Override
    public List<ReelModel> getReelByUserId(Long userId) {
        userService.findUserById(userId);
        return reelsRepo.findByUserId(userId);
    }
}
