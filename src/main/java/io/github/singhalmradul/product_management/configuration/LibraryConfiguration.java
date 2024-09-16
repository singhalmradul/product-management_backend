package io.github.singhalmradul.product_management.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class LibraryConfiguration {

    @Value("${cloudinary-url}")
    private String cloudinaryUrl;

    @Bean
    Cloudinary cloudinary() {
        var cloudinary = new Cloudinary(cloudinaryUrl);
        cloudinary.config.secure = true;
        return cloudinary;
    }

}
