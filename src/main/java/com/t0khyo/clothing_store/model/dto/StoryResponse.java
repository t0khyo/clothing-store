package com.t0khyo.clothing_store.model.dto;

import com.t0khyo.clothing_store.model.enums.ContentType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record StoryResponse(
        Long id,
        String title,
        ContentType content,

        String category,
        LocalDateTime creationDateTime
) {

}
