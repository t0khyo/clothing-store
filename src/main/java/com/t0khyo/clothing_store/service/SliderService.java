package com.t0khyo.clothing_store.service;

import com.t0khyo.clothing_store.model.dto.SliderResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SliderService {
    SliderResponse save(MultipartFile file, String title) throws IOException;

    SliderResponse getById(Long id);

    List<SliderResponse> getAll();

    void deleteById(Long id) throws IOException;

    Resource getImageById(Long id) throws IOException;
}
