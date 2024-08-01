package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.StoryResponse;
import com.t0khyo.clothing_store.service.StoryService;
import com.t0khyo.clothing_store.util.StoryAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
@RestController
public class StoryController {
    private final StoryService storyService;
    private final StoryAssembler storyAssembler;

    @PostMapping("/upload")
    public ResponseEntity<EntityModel<StoryResponse>> upload(
            @RequestParam(value="file") MultipartFile file,
            @RequestParam(value="title", required=false) String title) throws IOException {

        StoryResponse savedStory = storyService.save(file, title);

        return ResponseEntity.ok(storyAssembler.toModel(savedStory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StoryResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(storyAssembler.toModel(storyService.getById(id)));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getImageById(@PathVariable Long id) throws IOException {
        Resource image = storyService.getImageById(id);

        if (image == null || !image.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(Paths.get(image.getFile().getAbsolutePath()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<StoryResponse>>> getTodayStories() {
        List<StoryResponse> stories = storyService.getAllTodayStories();
        return ResponseEntity.ok(storyAssembler.toCollectionModel(stories));
    }

    @GetMapping("/archive")
    public ResponseEntity<CollectionModel<EntityModel<StoryResponse>>> getAll() {
        List<StoryResponse> stories = storyService.getAll();
        return ResponseEntity.ok(storyAssembler.toCollectionModel(stories));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws IOException {
        storyService.deleteById(id);
        return ResponseEntity.ok("Story deleted successfully!");
    }
}
