package com.t0khyo.clothing_store.mapper;

import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.model.entity.Slider;
import com.t0khyo.clothing_store.model.entity.Story;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    default SliderResponse toDto(Slider slider) {
        return SliderResponse.builder()
                .id(slider.getId())
                .title(slider.getTitle())
                .creationDateTime(slider.getCreationDateTime())
                .build();
    }


    default StoryResponse toDto(Story story) {
        return StoryResponse.builder()
                .id(story.getId())
                .title(story.getTitle())
                .creationDateTime(story.getCreationDateTime())
                .build();
    }
}
