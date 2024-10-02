package io.github.singhalmradul.product_management.services.implementations;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;

import io.github.singhalmradul.product_management.services.MediaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CloudinaryMediaService implements MediaService {

    private final Cloudinary cloudinary;

    @Override
    public List<String> saveFiles(final List<InputStream> inputStreams) {
        if (isEmpty(inputStreams)) {
            return emptyList();
        }
        return inputStreams.stream().map(this::saveFile).toList();
    }

    @Override
    public void deleteFile(final String imageName) {
        try {
            cloudinary.uploader().destroy(imageName, emptyMap());
        } catch (final IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteFiles(final List<String> imageNames) {
        if (isEmpty(imageNames)) {
            return;
        }
        imageNames.forEach(this::deleteFile);
    }

    @Override
    public String saveFile(InputStream inputStream) {
        final var cloudinaryConfig = Map.of(
            "folder", "product_management",
            "unique_filename", true,
            "overwrite", false,
            "resource_type", "auto",
            "public_id", RandomStringUtils.randomAlphanumeric(10)
        );

        try {

            return cloudinary
                .uploader()
                .upload(inputStream.readAllBytes(), cloudinaryConfig)
                .get("url")
                .toString();

        } catch (final IOException e) {

            throw new RuntimeException(e.getMessage());
        }
    }
}
