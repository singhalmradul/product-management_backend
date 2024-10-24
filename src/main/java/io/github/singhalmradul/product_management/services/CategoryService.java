package io.github.singhalmradul.product_management.services;

import java.util.List;

import io.github.singhalmradul.product_management.model.entities.Category;
import jakarta.servlet.http.Part;

public interface CategoryService {

    Category saveCategory(Category category);

    void deleteCategoryById(String id);

    List<Category> getCategories();

    Category getCategoryById(String id);

    List<String> addCategoryImages(String categoryId, List<Part> images);

    Category getReferenceById(String id);

    List<Category> findByNameSimilar(String name);
}