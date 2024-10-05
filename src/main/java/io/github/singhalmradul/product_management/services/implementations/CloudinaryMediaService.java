package io.github.singhalmradul.product_management.services.implementations;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;

import io.github.singhalmradul.product_management.services.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CloudinaryMediaService implements MediaService {

    private final String DIRECTORY = "product_management";

    private final Cloudinary cloudinary;

    @Override
    public List<String> saveFiles(final List<InputStream> inputStreams) {
        if (isEmpty(inputStreams)) {
            return emptyList();
        }
        return inputStreams.stream().map(this::saveFile).toList();
    }

    @Override
    public boolean deleteFile(final String imageUrl) {
        var end = imageUrl.lastIndexOf('.');
        var start = imageUrl.lastIndexOf('/') + 1;
        var publicId = imageUrl.substring(start, end);
        var resource = String.format("%s/%s", DIRECTORY, publicId);
        try {
            var response = cloudinary.uploader().destroy(resource, emptyMap());
            return response.get("result").equals("ok");
        } catch (final IOException e) {
            log.error("Failed to delete file: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteFiles(final Collection<String> imageUrls) {

        if (isEmpty(imageUrls)) {
            return true;
        }
        return imageUrls.stream().allMatch(this::deleteFile);
    }

    @Override
    public String saveFile(InputStream inputStream) {
        final var cloudinaryConfig = Map.of(
            "folder", DIRECTORY,
            "unique_filename", true,
            "overwrite", false,
            "resource_type", "auto",
            "public_id", randomAlphanumeric(10)
        );

        try {

            return cloudinary
                .uploader()
                .upload(inputStream.readAllBytes(), cloudinaryConfig)
                .get("url")
                .toString()
            ;
        } catch (final IOException e) {
            log.error("Failed to save file: {}", e.getMessage());
            return null;
        }
    }
}
