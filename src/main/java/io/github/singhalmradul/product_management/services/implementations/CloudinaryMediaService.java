package io.github.singhalmradul.product_management.services.implementations;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;

import io.github.singhalmradul.product_management.services.MediaService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CloudinaryMediaService implements MediaService {

    private final Cloudinary cloudinary;

    @Override
    public String saveImagePart(final Part imagePart) {

        final var cloudinaryConfig = Map.of(
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

        } catch (final IOException e) {

            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<String> saveImageParts(final List<Part> imageParts) {
        if (isEmpty(imageParts)) {
            return emptyList();
        }
        return imageParts.stream().map(this::saveImagePart).toList();
    }

    @Override
    public void deleteImage(final String imageName) {
        try {
            cloudinary.uploader().destroy(imageName, emptyMap());
        } catch (final IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteImages(final List<String> imageNames) {
        if (isEmpty(imageNames)) {
            return;
        }
        imageNames.forEach(this::deleteImage);
    }
}
