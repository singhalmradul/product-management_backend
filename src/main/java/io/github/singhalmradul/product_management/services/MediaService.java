package io.github.singhalmradul.product_management.services;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

public interface MediaService {

    String saveFile(InputStream inputStream);

    List<String> saveFiles(List<InputStream> inputStreams);

    boolean deleteFile(String imageUrl);

    boolean deleteFiles(Collection<String> imageUrls);
}
