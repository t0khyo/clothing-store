package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.CategoricalSliderResponse;
import com.t0khyo.clothing_store.service.ImageService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/categorical-sliders")
@RestController
public class CategoricalSliderController extends ImageController<CategoricalSliderResponse, Long> {
    public CategoricalSliderController(ImageService<CategoricalSliderResponse, Long> imageService, RepresentationModelAssembler<CategoricalSliderResponse, EntityModel<CategoricalSliderResponse>> assembler) {
        super(imageService, assembler);
    }
}
