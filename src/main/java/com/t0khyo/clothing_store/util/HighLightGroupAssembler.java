package com.t0khyo.clothing_store.util;

import com.t0khyo.clothing_store.controller.HighlightController;
import com.t0khyo.clothing_store.model.dto.HighlightGroupResponse;
import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HighLightGroupAssembler implements RepresentationModelAssembler<HighlightGroupResponse, EntityModel<HighlightGroupResponse>> {
    @SneakyThrows
    @Override
    public EntityModel<HighlightGroupResponse> toModel(HighlightGroupResponse highlightGroupResponse) {
        return EntityModel.of(highlightGroupResponse,
                linkTo(methodOn(HighlightController.class).getHighlightGroupById(highlightGroupResponse.id())).withSelfRel(),
                linkTo(methodOn(HighlightController.class).getAll()).withRel("highlights")
        );
    }
}