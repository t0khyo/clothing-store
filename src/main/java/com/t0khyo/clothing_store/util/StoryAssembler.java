package com.t0khyo.clothing_store.util;

import com.t0khyo.clothing_store.controller.SliderController;
import com.t0khyo.clothing_store.controller.StoryController;
import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.model.dto.StoryResponse;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StoryAssembler implements RepresentationModelAssembler<StoryResponse, EntityModel<StoryResponse>> {
    @SneakyThrows
    @Override
    public EntityModel<StoryResponse> toModel(StoryResponse storyResponse) {
        return EntityModel.of(storyResponse,
                linkTo(methodOn(StoryController.class).getImageById(storyResponse.id())).withSelfRel(),
                linkTo(methodOn(StoryController.class).getTodayStories()).withRel("stories")
        );
    }
}
