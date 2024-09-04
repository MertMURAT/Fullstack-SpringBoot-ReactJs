package com.mert.imageliteapi.application.images;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
public class ImagesController {

    @PostMapping
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) {
        log.info("Image received: name : {}, size : {}", file.getOriginalFilename(), file.getSize());
        log.info("Name defined for the image : {}", name);
        log.info("Tags : {}", tags);
        return ResponseEntity.ok().build();
    }
}
