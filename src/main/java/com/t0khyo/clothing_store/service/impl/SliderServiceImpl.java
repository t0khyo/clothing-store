package com.t0khyo.clothing_store.service.impl;

import com.t0khyo.clothing_store.mapper.SliderMapper;
import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.model.entity.Slider;
import com.t0khyo.clothing_store.repository.SliderRepository;
import com.t0khyo.clothing_store.service.ImageService;
import com.t0khyo.clothing_store.service.SliderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SliderServiceImpl implements SliderService {
    private final ImageService imageService;
    private final SliderRepository sliderRepository;
    private final SliderMapper sliderMapper;

    @Override
    public SliderResponse save(MultipartFile image, String title) throws IOException {
        String imagePath = imageService.saveSliderImage(image);

        Slider slider = Slider.builder()
                .title(title)
                .imagePath(imagePath)
                .build();

        Slider savedSlider = sliderRepository.save(slider);

        return sliderMapper.toDto(savedSlider);
    }

    @Override
    public SliderResponse getById(Long id) {
        Slider slider = sliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Story With id: " + id + " not found."));
        return sliderMapper.toDto(slider);
    }

    @Override
    public List<SliderResponse> getAll() {
        return sliderRepository.findAll().stream().map(sliderMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) throws IOException {
        Slider slider = sliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Story With id: " + id + " not found."));

        imageService.deleteImage(slider.getImagePath());
        sliderRepository.delete(slider);
    }

    @Override
    public Resource getImageById(Long id) throws IOException {
        Slider slider = sliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Story With id: " + id + " not found."));

        log.info("getImageById(): loading image with path: {}", slider.getImagePath());

        return imageService.loadImage(slider.getImagePath());
    }
}
