package com.t0khyo.clothing_store.mapper;

import com.t0khyo.clothing_store.model.dto.*;
import com.t0khyo.clothing_store.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

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
                .content(story.getContent())
                .category(story.getCategory())
                .creationDateTime(story.getCreationDateTime())
                .build();
    }

    default HighlightResponse toDto(Highlight highlight) {
        return HighlightResponse.builder()
                .id(highlight.getId())
                .title(highlight.getTitle())
                .content(highlight.getContent())
                .category(highlight.getCategory())
                .creationDateTime(highlight.getCreationDateTime())
                .build();
    }

    default CategoricalSliderResponse toDto(CategoricalSlider categoricalSlider) {
        return CategoricalSliderResponse.builder()
                .id(categoricalSlider.getId())
                .title(categoricalSlider.getTitle())
                .creationDateTime(categoricalSlider.getCreationDateTime())
                .category(categoricalSlider.getCategory())
                .build();
    }

    default HighlightGroupResponse toDto(HighlightGroup highlightGroup) {
        return HighlightGroupResponse.builder()
                .id(highlightGroup.getId())
                .title(highlightGroup.getTitle())
                .creationDateTime(highlightGroup.getCreationDateTime())
                .category(highlightGroup.getCategory())
                .highlights(
                        highlightGroup.getHighlights() == null
                                ? new ArrayList<>()
                                : highlightGroup.getHighlights().stream().map(this::toDto).toList()
                )
                .build();
    }

}
