package com.mert.imageliteapi.application.images;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mert.imageliteapi.domain.enums.ImageExtension;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ImageDTO {
    private String id;
    private String url;
    private String name;
    private String extension;
    private Long size;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate uploadDate;

}
