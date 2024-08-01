package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import com.t0khyo.clothing_store.service.ImageService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/highlights")
@RestController
public class HighlightController extends ImageController<HighlightResponse, Long> {
    public HighlightController(ImageService<HighlightResponse, Long> imageService, RepresentationModelAssembler<HighlightResponse, EntityModel<HighlightResponse>> assembler) {
        super(imageService, assembler);
    }
}
