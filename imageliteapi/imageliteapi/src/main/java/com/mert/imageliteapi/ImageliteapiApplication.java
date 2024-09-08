package com.mert.imageliteapi;

import com.mert.imageliteapi.base.repository.ImageRepository;
import com.mert.imageliteapi.domain.entity.Image;
import com.mert.imageliteapi.domain.enums.ImageExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImageliteapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageliteapiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ImageRepository imageRepository) {
		return args -> {
			Image image = Image.builder()
					.name("image")
					.size(100L)
					.extension(ImageExtension.PNG)
					.tags("tag1, tag2")
					.build();
			imageRepository.save(image);
		};
	}
}
