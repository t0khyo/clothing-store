package com.t0khyo.clothing_store.model.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record HighlightGroupResponse(
        Long id,
        String title,
        String category,
        List<HighlightResponse> highlights,
        LocalDateTime creationDateTime
) {
}
