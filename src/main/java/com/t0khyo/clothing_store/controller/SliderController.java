package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.assembler.SliderAssembler;
import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.service.SliderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/sliders")
@RestController
public class SliderController {
    private final SliderService sliderService;
    private final SliderAssembler sliderAssembler;


    @PostMapping("/upload")
    public ResponseEntity<EntityModel<SliderResponse>> upload(
            @RequestParam(value="file") MultipartFile file,
            @RequestParam(value="title", required=false) String title) throws IOException {

        SliderResponse savedSlider = sliderService.save(file, title);

        return ResponseEntity.ok(sliderAssembler.toModel(savedSlider));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SliderResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sliderAssembler.toModel(sliderService.getById(id)));
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getImageById(@PathVariable Long id) throws IOException {
        Resource image = sliderService.getImageById(id);

        if (image == null || !image.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(Paths.get(image.getFile().getAbsolutePath()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
    }


    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<SliderResponse>>> getAll() {
        List<SliderResponse> sliders = sliderService.getAll();
        return ResponseEntity.ok(sliderAssembler.toCollectionModel(sliders));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws IOException {
        sliderService.deleteById(id);
        return ResponseEntity.ok("Slider deleted successfully!");
    }
}
