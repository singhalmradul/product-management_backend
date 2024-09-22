package io.github.singhalmradul.product_management.services;

import java.util.List;

import io.github.singhalmradul.product_management.model.Category;
import jakarta.servlet.http.Part;

public interface CategoryService {

    Category saveCategory(Category category);

    Category getCategoryByName(String name);

    void deleteCategory(String name);

    List<Category> getCategories();

    Category getCategoryById(String id);

    List<String> addCategoryImages(String categoryId, List<Part> images);

    Category getReferenceById(String id);
}