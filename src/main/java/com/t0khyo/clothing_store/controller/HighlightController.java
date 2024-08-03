package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.model.enums.Category;
import com.t0khyo.clothing_store.model.enums.ContentType;
import com.t0khyo.clothing_store.service.HighlightService;
import com.t0khyo.clothing_store.service.ImageService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/highlights")
@RestController
public class HighlightController extends ImageController<HighlightResponse, Long> {
    private final HighlightService highlightService;

    public HighlightController(ImageService<HighlightResponse, Long> imageService, RepresentationModelAssembler<HighlightResponse, EntityModel<HighlightResponse>> assembler, HighlightService highlightService) {
        super(imageService, assembler);
        this.highlightService = highlightService;
    }

    @PostMapping("/upload")
    public ResponseEntity<EntityModel<HighlightResponse>> upload(
            @RequestParam(value="file") MultipartFile file,
            @RequestParam(value="title", required=false, defaultValue="NONE") String title,
            @RequestParam(value="contentType") ContentType contentType,
            @RequestParam Category category
    ) throws IOException {

        HighlightResponse savedImage = highlightService.save(file, title, contentType, category);

        return ResponseEntity.ok(assembler.toModel(savedImage));
    }

    @GetMapping("/{category}")
    public ResponseEntity<CollectionModel<EntityModel<HighlightResponse>>> getAllByCategory(
            @PathVariable Category category
    ) {
        List<HighlightResponse> highlights = highlightService.getAllByCategory(category);
        return ResponseEntity.ok(assembler.toCollectionModel(highlights));
    }
}
