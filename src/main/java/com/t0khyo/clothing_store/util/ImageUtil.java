package com.t0khyo.clothing_store.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageUtil {
    private final ResourceLoader resourceLoader;
    @Value("${sliders.dir}")
    private String sliderDir;
    @Value("${story.dir}")
    private String storyDir;

    private String store(MultipartFile image, String dir) throws IOException {
        try {
            Path path = Paths.get(dir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            String filename = image.getOriginalFilename();
            Path filePath = path.resolve(filename);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            log.error("Error storing file: ", e);
            throw new IOException("store(): Error while storing file.");
        }
    }

    public String saveSliderImage(MultipartFile image) throws IOException {
        return store(image, sliderDir);
    }

    public String saveStoryImage(MultipartFile image) throws IOException {
        return store(image, storyDir);
    }

    public Resource loadImage(String imagePath) throws IOException {
        try {
            Path path = Paths.get(imagePath);
            Resource resource = resourceLoader.getResource("file:" + path);
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + imagePath);
            }
        } catch (Exception e) {
            log.error("Error loading file: ", e);
            throw new IOException("loadImage(): Error while loading file.");
        }
    }

    public void deleteImage(String imagePath) throws IOException {
        try {
            Path path = Paths.get(imagePath);
            Files.deleteIfExists(path);
            log.info("deleteImage() Deleted image at path: {}", imagePath);
        } catch (IOException e) {
            log.error("deleteImage() Error deleting image at path: " + imagePath);
            throw new IOException("deleteImage() Error deleting image at path: " + imagePath);
        }
    }
}
