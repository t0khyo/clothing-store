package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.service.ImageService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SuperBuilder
@RequestMapping("/api/v1/sliders")
@RestController
public class SliderController extends ImageController<SliderResponse, Long> {
    public SliderController(ImageService<SliderResponse, Long> imageService, RepresentationModelAssembler<SliderResponse, EntityModel<SliderResponse>> assembler) {
        super(imageService, assembler);
    }
}
