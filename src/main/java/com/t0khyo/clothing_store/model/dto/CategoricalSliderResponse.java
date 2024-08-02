package com.t0khyo.clothing_store.model.dto;

import com.t0khyo.clothing_store.model.enums.Category;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoricalSliderResponse(
        Long id,
        String title,
        Category category,
        LocalDateTime creationDateTime
) {
}
