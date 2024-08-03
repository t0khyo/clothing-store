package com.t0khyo.clothing_store.mapper;

import com.t0khyo.clothing_store.model.dto.CategoricalSliderResponse;
import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.model.entity.CategoricalSlider;
import com.t0khyo.clothing_store.model.entity.Highlight;
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
}
