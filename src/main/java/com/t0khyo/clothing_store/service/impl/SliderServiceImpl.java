package com.t0khyo.clothing_store.service.impl;

import com.t0khyo.clothing_store.mapper.ImageMapper;
import com.t0khyo.clothing_store.model.dto.SliderResponse;
import com.t0khyo.clothing_store.model.entity.Slider;
import com.t0khyo.clothing_store.repository.SliderRepository;
import com.t0khyo.clothing_store.service.SliderService;
import com.t0khyo.clothing_store.util.ImageUtil;
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
//@Service
public class SliderServiceImpl implements SliderService {
    private final ImageUtil imageUtil;
    private final SliderRepository sliderRepository;
    private final ImageMapper imageMapper;

    public SliderResponse save(MultipartFile image, String title) throws IOException {
        String imagePath = imageUtil.saveSliderImage(image);

        Slider slider = Slider.builder()
                .title(title)
                .imagePath(imagePath)
                .build();

        Slider savedSlider = sliderRepository.save(slider);

        return imageMapper.toDto(savedSlider);
    }

    @Override
    public SliderResponse getById(Long id) {
        Slider slider = sliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Slider With id: " + id + " not found."));
        return imageMapper.toDto(slider);
    }

    @Override
    public List<SliderResponse> getAll() {
        return sliderRepository.findAll().stream().map(imageMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) throws IOException {
        Slider slider = sliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Slider With id: " + id + " not found."));

        imageUtil.deleteImage(slider.getImagePath());
        sliderRepository.delete(slider);
    }

    @Override
    public Resource getImageById(Long id) throws IOException {
        Slider slider = sliderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Slider With id: " + id + " not found."));

        log.info("getImageById(): loading image with path: {}", slider.getImagePath());

        return imageUtil.loadImage(slider.getImagePath());
    }
}
