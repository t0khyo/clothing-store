package com.t0khyo.clothing_store.model.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record StoryResponse(
        Long id,
        String title,
        LocalDateTime creationDateTime
) {

}
