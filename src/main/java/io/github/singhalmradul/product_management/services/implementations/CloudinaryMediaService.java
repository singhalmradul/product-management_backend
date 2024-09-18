package io.github.singhalmradul.product_management.services.implementations;

import static java.util.Collections.emptyList;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;

import io.github.singhalmradul.product_management.services.MediaService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class CloudinaryMediaService implements MediaService {

    private final Cloudinary cloudinary;

    @Override
    public String saveImagePart(Part imagePart) {

        var cloudinaryConfig = Map.of(
            "folder", "product_management",
            "unique_filename", true,
            "overwrite", false,
            "resource_type", "auto",
            "public_id", imagePart.getSubmittedFileName()
        );

        try {

            return cloudinary
                .uploader()
                .upload(imagePart.getInputStream().readAllBytes(), cloudinaryConfig)
                .get("url")
                .toString();

        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<String> saveImageParts(List<Part> imageParts) {
        if (isEmpty(imageParts)) {
            return emptyList();
        }
        return imageParts.stream().map(this::saveImagePart).toList();
    }
}
