package com.t0khyo.clothing_store.util;

import com.t0khyo.clothing_store.controller.HighlightController;
import com.t0khyo.clothing_store.controller.SliderController;
import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HighlightAssembler implements RepresentationModelAssembler<HighlightResponse, EntityModel<HighlightResponse>> {
    @SneakyThrows
    @Override
    public EntityModel<HighlightResponse> toModel(HighlightResponse highlightResponse) {
        return EntityModel.of(highlightResponse,
                linkTo(methodOn(HighlightController.class).getImageById(highlightResponse.id())).withSelfRel(),
                linkTo(methodOn(HighlightController.class).getAll()).withRel("highlights")
        );
    }
}
