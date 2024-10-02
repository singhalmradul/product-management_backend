package io.github.singhalmradul.product_management.services;

import java.io.InputStream;
import java.util.List;

public interface MediaService {

    String saveFile(InputStream inputStream);

    List<String> saveFiles(List<InputStream> inputStreams);

    void deleteFile(String fileName);

    void deleteFiles(List<String> fileNames);
}
