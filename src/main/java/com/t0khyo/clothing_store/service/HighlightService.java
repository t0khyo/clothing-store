package com.t0khyo.clothing_store.service;

import com.t0khyo.clothing_store.model.dto.HighlightGroupResponse;
import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import com.t0khyo.clothing_store.model.enums.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HighlightService extends ImageService<HighlightResponse, Long> {
    HighlightResponse save(MultipartFile file, String title, ContentType contentType, String category) throws IOException;

    List<HighlightResponse> getAllByCategory(String category);

    HighlightGroupResponse saveHighlightGroup(String name, String category);

    HighlightGroupResponse getHighlightGroupById(Long id);

    List<HighlightGroupResponse> getAllHighlightGroups();

    String deleteHighlightGroupById(Long id);

    HighlightGroupResponse addHighlightToHighlightGroupById(Long id, Long highlightId);
}
