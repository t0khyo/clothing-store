package com.t0khyo.clothing_store.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveSliderImage(MultipartFile image) throws IOException;
    String saveStoryImage(MultipartFile image) throws IOException;
    Resource loadImage(String imagePath) throws IOException;

    void deleteImage(String imagePath) throws IOException;
}
