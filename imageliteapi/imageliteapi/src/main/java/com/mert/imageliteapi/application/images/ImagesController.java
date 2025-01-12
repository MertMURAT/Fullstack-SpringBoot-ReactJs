package com.mert.imageliteapi.application.images;

import com.mert.imageliteapi.domain.entity.Image;
import com.mert.imageliteapi.domain.enums.ImageExtension;
import com.mert.imageliteapi.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImagesController {

    private final ImageMapper imageMapper;
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) throws IOException {
        log.info("Image received: name : {}, size : {}", file.getOriginalFilename(), file.getSize());
        log.info("File content type : {}", file.getContentType());
        log.info("Media Type : {}", MediaType.valueOf(Objects.requireNonNull(file.getContentType())));
        log.info("Name defined for the image : {}", name);
        log.info("Tags : {}", tags);

        Image image = imageMapper.mapToImage(file, name, tags);
        Image savedImage = imageService.save(image);
        URI imageUri = buildImageURL(savedImage);

        return ResponseEntity.created(imageUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        var possibleImage = imageService.getById(id);
        if (possibleImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Image image = possibleImage.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtension().getMediaType());
        headers.setContentLength(image.getSize());
        // inline; filename="image.PNG"
        headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() + "\"", image.getFileName());

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(
            @RequestParam(value = "extension", required = false, defaultValue = "") String extension,
            @RequestParam(value = "query", required = false) String query
    ) {
        var result = imageService.search(ImageExtension.ofName(extension), query);
        var images = result.stream()
                .map(image ->
                {
                    var url = buildImageURL(image);
                    return imageMapper.ImageToDTO(image, url.toString());
                }).toList();

        return ResponseEntity.ok(images);
    }

    // localhost:8080/v1/images/asfsagags
    private URI buildImageURL(Image image) {
        String imagePath = "/" + image.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri() //
//                .fromCurrentRequest() //
                .path(imagePath)
                .build().toUri();
    }
}
