package com.t0khyo.clothing_store.model.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record HighlightResponse(
        Long id,
        String title,
        LocalDateTime creationDateTime
) {
}
