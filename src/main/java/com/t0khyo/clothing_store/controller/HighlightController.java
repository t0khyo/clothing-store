package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.mapper.ImageMapper;
import com.t0khyo.clothing_store.model.dto.HighlightGroupResponse;
import com.t0khyo.clothing_store.model.dto.HighlightResponse;
import com.t0khyo.clothing_store.model.enums.Category;
import com.t0khyo.clothing_store.model.enums.ContentType;
import com.t0khyo.clothing_store.service.HighlightService;
import com.t0khyo.clothing_store.service.ImageService;
import com.t0khyo.clothing_store.util.HighLightGroupAssembler;
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
    private final HighLightGroupAssembler groupAssembler;
    private final ImageMapper imageMapper;

    public HighlightController(ImageService<HighlightResponse, Long> imageService, RepresentationModelAssembler<HighlightResponse, EntityModel<HighlightResponse>> assembler, HighlightService highlightService, HighLightGroupAssembler groupAssembler,
                               ImageMapper imageMapper) {
        super(imageService, assembler);
        this.highlightService = highlightService;
        this.groupAssembler = groupAssembler;
        this.imageMapper = imageMapper;
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


    @PostMapping("/groups")
    public ResponseEntity<EntityModel<HighlightGroupResponse>> createHighlightGroup(
            @RequestParam("name") String name,
            @RequestParam(required=false) Category category
    ) {
        return ResponseEntity.ok(groupAssembler.toModel(highlightService.saveHighlightGroup(name, category)));
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<EntityModel<HighlightGroupResponse>> getHighlightGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupAssembler.toModel(highlightService.getHighlightGroupById(id)));
    }

    @GetMapping("/groups")
    public ResponseEntity<CollectionModel<EntityModel<HighlightGroupResponse>>> getAllHighlightGroups() {
        return ResponseEntity.ok(groupAssembler.toCollectionModel(highlightService.getAllHighlightGroups()));
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteHighlightGroupById(@PathVariable Long id) {
        highlightService.deleteHighlightGroupById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/groups/{id}")
    public ResponseEntity<Void> addHighlightToHighlightGroupById(
            @PathVariable Long id,
            @RequestParam Long highlightId
    ) {
        highlightService.addHighlightToHighlightGroupById(id, highlightId);
        return ResponseEntity.noContent().build();
    }

}
