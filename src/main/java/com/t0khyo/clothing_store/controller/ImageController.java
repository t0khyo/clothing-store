package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@RestController
public abstract class ImageController<T, ID> {
    protected final ImageService<T, ID> imageService;
    protected final RepresentationModelAssembler<T, EntityModel<T>> assembler;

//    @PostMapping("/upload")
//    public ResponseEntity<EntityModel<T>> upload(
//            @RequestParam(value="file") MultipartFile file,
//            @RequestParam(value="title", required=false, defaultValue="NONE") String title
//    ) throws IOException {
//
//        T savedImage = imageService.save(file, title);
//
//        return ResponseEntity.ok(assembler.toModel(savedImage));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<T>> getById(@PathVariable ID id) {
        return ResponseEntity.ok(assembler.toModel(imageService.getById(id)));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getImageById(@PathVariable ID id) throws IOException {
        Resource image = imageService.getImageById(id);

        if (image == null || !image.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(Paths.get(image.getFile().getAbsolutePath()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<T>>> getAll() {
        List<T> image = imageService.getAll();
        return ResponseEntity.ok(assembler.toCollectionModel(image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable ID id) throws IOException {
        imageService.deleteById(id);
        return ResponseEntity.ok("image deleted successfully!");
    }
}
