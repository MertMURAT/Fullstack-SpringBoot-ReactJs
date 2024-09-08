package com.mert.imageliteapi.application.images;

import com.mert.imageliteapi.base.repository.ImageRepository;
import com.mert.imageliteapi.domain.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public void saveImage(Image image) {
        imageRepository.save(image);
    }
}
