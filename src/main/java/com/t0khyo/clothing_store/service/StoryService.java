package com.t0khyo.clothing_store.service;

import com.t0khyo.clothing_store.model.dto.StoryResponse;

import java.util.List;

public interface StoryService extends ImageService<StoryResponse, Long> {
    List<StoryResponse> getAllTodayStories();
}
