package io.github.singhalmradul.product_management.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.Category;
import io.github.singhalmradul.product_management.repositories.CategoryRepository;
import io.github.singhalmradul.product_management.services.CategoryService;
import io.github.singhalmradul.product_management.services.MediaService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {


    private static final String CATEGORY_NAME_NOT_FOUND_TEMPLATE = "Category with name %s not found";
    private final CategoryRepository repository;
    private final MediaService mediaService;

    @Override
    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return repository
            .findByName(name)
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                CATEGORY_NAME_NOT_FOUND_TEMPLATE,
                name
            )))
        ;
    }

    @Override
    public void deleteCategory(String name) {
        repository.deleteByName(name);
    }

    @Override
    public List<Category> getCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(String id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                "Category with id %s not found",
                id
            )))
        ;
    }

    @Override
    public List<String> addCategoryImages(String categoryId, List<Part> images) {
        var category = getCategoryById(categoryId);
        var imageUrls = mediaService.saveImageParts(images);
        category.getImages().addAll(imageUrls);
        return repository.save(category).getImages();
    }

    @Override
    public Category getReferenceById(String id) {
        return repository.getReferenceById(id);
    }

}
