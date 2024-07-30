package com.t0khyo.clothing_store.assembler;

import com.t0khyo.clothing_store.controller.SliderController;
import com.t0khyo.clothing_store.model.dto.SliderResponse;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SliderAssembler implements RepresentationModelAssembler<SliderResponse, EntityModel<SliderResponse>> {
    @SneakyThrows
    @Override
    public EntityModel<SliderResponse> toModel(SliderResponse sliderResponse) {
        return EntityModel.of(sliderResponse,
                linkTo(methodOn(SliderController.class).getImageById(sliderResponse.id())).withSelfRel(),
                linkTo(methodOn(SliderController.class).getAll()).withRel("sliders")
        );
    }
}
