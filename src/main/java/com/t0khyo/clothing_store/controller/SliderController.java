package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.service.ImageService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@SuperBuilder
//@RequestMapping("/api/v1/sliders")
//@RestController
public class SliderController extends ImageController<SliderResponse, Long> {
    public SliderController(ImageService<SliderResponse, Long> imageService, RepresentationModelAssembler<SliderResponse, EntityModel<SliderResponse>> assembler) {
        super(imageService, assembler);
    }

    @PostMapping("/upload")
    public ResponseEntity<EntityModel<SliderResponse>> upload(
            @RequestParam(value="file") MultipartFile file,
            @RequestParam(value="title", required=false, defaultValue="NONE") String title
    ) throws IOException {

        SliderResponse savedImage = imageService.save(file, title);

        return ResponseEntity.ok(assembler.toModel(savedImage));
    }
}
