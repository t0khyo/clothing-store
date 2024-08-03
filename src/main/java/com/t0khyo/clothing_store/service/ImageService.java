package com.t0khyo.clothing_store.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService<T, ID> {
//    T save(MultipartFile file, String title) throws IOException;

    T getById(ID id);

    List<T> getAll();

    void deleteById(ID id) throws IOException;

    Resource getImageById(ID id) throws IOException;
}
