package com.t0khyo.clothing_store.service;

import com.t0khyo.clothing_store.model.dto.CategoricalSliderResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoricalSliderService extends ImageService<CategoricalSliderResponse, Long> {
    CategoricalSliderResponse save(MultipartFile image, String title, String category) throws IOException;

    List<CategoricalSliderResponse> getAllByCategory(String category);
}
