package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.HighlightResponse;
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

@RequestMapping("/api/v1/highlights")
@RestController
public class HighlightController extends ImageController<HighlightResponse, Long> {
    public HighlightController(ImageService<HighlightResponse, Long> imageService, RepresentationModelAssembler<HighlightResponse, EntityModel<HighlightResponse>> assembler) {
        super(imageService, assembler);
    }

    @PostMapping("/upload")
    public ResponseEntity<EntityModel<HighlightResponse>> upload(
            @RequestParam(value="file") MultipartFile file,
            @RequestParam(value="title", required=false, defaultValue="NONE") String title
    ) throws IOException {

        HighlightResponse savedImage = imageService.save(file, title);

        return ResponseEntity.ok(assembler.toModel(savedImage));
    }
}
