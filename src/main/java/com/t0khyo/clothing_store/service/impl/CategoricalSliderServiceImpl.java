package com.t0khyo.clothing_store.service.impl;

import com.t0khyo.clothing_store.mapper.ImageMapper;
import com.t0khyo.clothing_store.model.dto.CategoricalSliderResponse;
import com.t0khyo.clothing_store.model.entity.CategoricalSlider;
import com.t0khyo.clothing_store.model.enums.Category;
import com.t0khyo.clothing_store.repository.CategoricalSliderRepository;
import com.t0khyo.clothing_store.service.CategoricalSliderService;
import com.t0khyo.clothing_store.util.ImageUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoricalSliderServiceImpl implements CategoricalSliderService {
    private final ImageUtil imageUtil;
    private final CategoricalSliderRepository categoricalSliderRepository;
    private final ImageMapper imageMapper;
    @Value("${sliders.general.dir}")
    private String generalSliderDir;
    @Value("${sliders.men.dir}")
    private String menSliderDir;
    @Value("${sliders.women.dir}")
    private String womenSliderDir;
    @Value("${sliders.kids.dir}")
    private String kidsSliderDir;

    @Override
    public CategoricalSliderResponse save(MultipartFile image, String title, Category category) throws IOException {
        String dir = switch (category) {
            case MEN -> menSliderDir;
            case WOMEN -> womenSliderDir;
            case KIDS -> kidsSliderDir;
            case GENERAL -> generalSliderDir;
        };

        String imagePath = imageUtil.saveImage(image, dir);

        CategoricalSlider categoricalSlider = CategoricalSlider.builder()
                .title(title)
                .imagePath(imagePath)
                .category(category)
                .build();

        CategoricalSlider savedCategoricalSlider = categoricalSliderRepository.save(categoricalSlider);

        return imageMapper.toDto(savedCategoricalSlider);
    }

    @Override
    public CategoricalSliderResponse save(MultipartFile file, String title) throws IOException {
        return null;
    }

    @Override
    public CategoricalSliderResponse getById(Long id) {
        CategoricalSlider categoricalSlider = categoricalSliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("CategoricalSlider With id: " + id + " not found."));
        return imageMapper.toDto(categoricalSlider);
    }

    @Override
    public List<CategoricalSliderResponse> getAll() {
        return categoricalSliderRepository.findAll().stream().map(imageMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) throws IOException {
        CategoricalSlider categoricalSlider = categoricalSliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("CategoricalSlider With id: " + id + " not found."));

        imageUtil.deleteImage(categoricalSlider.getImagePath());
        categoricalSliderRepository.delete(categoricalSlider);
    }

    @Override
    public Resource getImageById(Long id) throws IOException {
        CategoricalSlider categoricalSlider = categoricalSliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("CategoricalSlider With id: " + id + " not found."));

        log.info("getImageById(): loading image with path: {}", categoricalSlider.getImagePath());

        return imageUtil.loadImage(categoricalSlider.getImagePath());
    }

    @Override
    public List<CategoricalSliderResponse> getAllByCategory(Category category) {
        return categoricalSliderRepository.findAllByCategory(category).stream().map(imageMapper::toDto).toList();
    }
}
