package org.project.imagesaveform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.project.imagesaveform")
public class ImageSaveFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageSaveFormApplication.class, args);
	}

}
