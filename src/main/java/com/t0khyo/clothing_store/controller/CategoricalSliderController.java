package com.t0khyo.clothing_store.controller;

import com.t0khyo.clothing_store.model.dto.CategoricalSliderResponse;
import com.t0khyo.clothing_store.model.enums.Category;
import com.t0khyo.clothing_store.service.CategoricalSliderService;
import com.t0khyo.clothing_store.service.ImageService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/sliders")
@RestController
public class CategoricalSliderController extends ImageController<CategoricalSliderResponse, Long> {
    private final CategoricalSliderService categoricalSliderService;

    public CategoricalSliderController(ImageService<CategoricalSliderResponse, Long> imageService, RepresentationModelAssembler<CategoricalSliderResponse, EntityModel<CategoricalSliderResponse>> assembler, CategoricalSliderService categoricalSliderService) {
        super(imageService, assembler);
        this.categoricalSliderService = categoricalSliderService;
    }

    @GetMapping("/{category}")
    public ResponseEntity<CollectionModel<EntityModel<CategoricalSliderResponse>>> getAll(@PathVariable Category category) {
        List<CategoricalSliderResponse> categoricalSliders = categoricalSliderService.getAllByCategory(category);
        return ResponseEntity.ok(assembler.toCollectionModel(categoricalSliders));
    }

    @PostMapping("/upload")
    public ResponseEntity<EntityModel<CategoricalSliderResponse>> upload(
            @RequestParam(value="file") MultipartFile file,
            @RequestParam(value="title") Category category,
            @RequestParam(value="title", required=false, defaultValue="NONE") String title

    ) throws IOException {

        CategoricalSliderResponse savedImage = categoricalSliderService.save(file, title, category);

        return ResponseEntity.ok(assembler.toModel(savedImage));
    }
}
