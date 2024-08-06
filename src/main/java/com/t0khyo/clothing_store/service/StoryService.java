package com.t0khyo.clothing_store.service;

import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.model.enums.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StoryService extends ImageService<StoryResponse, Long> {
    StoryResponse save(MultipartFile file, String title, ContentType contentType, String category) throws IOException;

    List<StoryResponse> getAllTodayStories();

    List<StoryResponse> getAllByCategory(String category);
}
