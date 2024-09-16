package io.github.singhalmradul.product_management.services;

import java.util.List;

import jakarta.servlet.http.Part;

public interface MediaService {

    String saveImagePart(Part imagePart);

    List<String> saveImageParts(List<Part> imageParts);
}
