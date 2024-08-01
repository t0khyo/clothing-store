package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.service.ImageService;
import com.t0khyo.clothing_store.service.StoryService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@SuperBuilder
@RequestMapping("/api/v1/story")
@RestController
public class StoryController extends ImageController<StoryResponse, Long> {
    private final StoryService storyService;

    public StoryController(ImageService<StoryResponse, Long> imageService, RepresentationModelAssembler<StoryResponse, EntityModel<StoryResponse>> assembler, StoryService storyService) {
        super(imageService, assembler);
        this.storyService = storyService;
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<StoryResponse>>> getTodayStories() {
        List<StoryResponse> stories = storyService.getAllTodayStories();
        return ResponseEntity.ok(assembler.toCollectionModel(stories));
    }

    @GetMapping("/archive")
    public ResponseEntity<CollectionModel<EntityModel<StoryResponse>>> getAll() {
        List<StoryResponse> stories = storyService.getAll();
        return ResponseEntity.ok(assembler.toCollectionModel(stories));
    }
}
