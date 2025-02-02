package com.davendra.buzzer.services;

import com.davendra.buzzer.dto.request.ReelRequest;
import com.davendra.buzzer.models.ReelModel;

import java.io.IOException;
import java.util.List;

public interface ReelsService {
    public ReelModel createReel(ReelRequest reelRequest, Long userId) throws IOException;

    public List<ReelModel> getAllReels();

    public List<ReelModel> getReelByUserId(Long userId);
}
