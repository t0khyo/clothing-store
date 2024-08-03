package com.t0khyo.clothing_store.service;

import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.model.enums.Category;
import com.t0khyo.clothing_store.model.enums.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HighlightService extends ImageService<HighlightResponse, Long> {
    HighlightResponse save(MultipartFile file, String title, ContentType contentType, Category category) throws IOException;

    List<HighlightResponse> getAllByCategory(Category category);
}
