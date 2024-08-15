package com.t0khyo.clothing_store.model.dto;

import lombok.Builder;

@Builder
public record CategoryRequest(
        String title,
        String color
) {
}
