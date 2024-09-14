package com.mert.imageliteapi.application.images;

import com.mert.imageliteapi.domain.enums.ImageExtension;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ImageDTO {
    private String url;
    private String name;
    private String extension;
    private Long size;
    private LocalDate uploadDate;

}
