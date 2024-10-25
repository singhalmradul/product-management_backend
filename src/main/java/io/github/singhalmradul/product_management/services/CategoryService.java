package io.github.singhalmradul.product_management.services;

import java.util.List;
import java.util.UUID;

import io.github.singhalmradul.product_management.model.entities.Category;
import jakarta.servlet.http.Part;

public interface CategoryService {

    Category saveCategory(Category category);

    void deleteCategoryById(UUID id);

    List<Category> getCategories();

    Category getCategoryById(UUID id);

    List<String> addCategoryImages(UUID categoryId, List<Part> images);

    Category getReferenceById(UUID id);

    List<Category> findByNameSimilar(String name);
}