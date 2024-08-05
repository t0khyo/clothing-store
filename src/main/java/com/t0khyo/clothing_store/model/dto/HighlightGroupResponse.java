package com.t0khyo.clothing_store.model.dto;

import com.t0khyo.clothing_store.model.enums.Category;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record HighlightGroupResponse(
        Long id,
        String title,
        Category category,
        List<HighlightResponse> highlights,
        LocalDateTime creationDateTime
) {
}
