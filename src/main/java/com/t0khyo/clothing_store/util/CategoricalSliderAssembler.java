package com.t0khyo.clothing_store.util;

import com.t0khyo.clothing_store.controller.CategoricalSliderController;
import com.t0khyo.clothing_store.model.dto.CategoricalSliderResponse;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class CategoricalSliderAssembler implements RepresentationModelAssembler<CategoricalSliderResponse, EntityModel<CategoricalSliderResponse>> {
    @SneakyThrows
    @Override
    public EntityModel<CategoricalSliderResponse> toModel(CategoricalSliderResponse categoricalSliderResponse) {
        return EntityModel.of(categoricalSliderResponse,
                linkTo(methodOn(CategoricalSliderController.class).getImageById(categoricalSliderResponse.id())).withSelfRel(),
                linkTo(methodOn(CategoricalSliderController.class).getAll()).withRel("categorical-sliders")
        );
    }
}
