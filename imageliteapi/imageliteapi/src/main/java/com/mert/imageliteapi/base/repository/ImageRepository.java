package com.mert.imageliteapi.base.repository;

import com.mert.imageliteapi.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}