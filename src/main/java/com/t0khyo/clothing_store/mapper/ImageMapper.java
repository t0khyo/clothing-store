package com.t0khyo.clothing_store.mapper;

import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.model.entity.Slider;
import com.t0khyo.clothing_store.model.entity.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mapping(target = "id", source = "slider.id")
    @Mapping(target = "title", source = "slider.title")
    @Mapping(target = "creationDateTime", source = "slider.creationDateTime")
    SliderResponse toDto(Slider slider);

    @Mapping(target = "id", source = "story.id")
    @Mapping(target = "title", source = "story.title")
    @Mapping(target = "creationDateTime", source = "story.creationDateTime")
    StoryResponse toDto(Story story);
}
