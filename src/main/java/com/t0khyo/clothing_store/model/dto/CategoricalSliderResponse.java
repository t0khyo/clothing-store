package com.t0khyo.clothing_store.model.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoricalSliderResponse(
        Long id,
        String title,
        String category,
        LocalDateTime creationDateTime
) {
}
