package com.t0khyo.clothing_store.service;

import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.model.dto.StoryResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StoryService {
    StoryResponse save(MultipartFile file, String title) throws IOException;

    StoryResponse getById(Long id);

    List<StoryResponse> getAll();

    List<StoryResponse> getAllTodayStories();

    void deleteById(Long id) throws IOException;

    Resource getImageById(Long id) throws IOException;
}
