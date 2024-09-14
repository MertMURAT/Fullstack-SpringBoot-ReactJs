package com.mert.imageliteapi.application.images;

import com.mert.imageliteapi.base.repository.ImageRepository;
import com.mert.imageliteapi.domain.entity.Image;
import com.mert.imageliteapi.domain.enums.ImageExtension;
import com.mert.imageliteapi.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Optional<Image> getById(String id) {
        return imageRepository.findById(id);
    }

    @Override
    public List<Image> search(ImageExtension extension, String query) {
        return imageRepository.findByExtensionAndNameOrTagsLike(extension, query);
    }
}
