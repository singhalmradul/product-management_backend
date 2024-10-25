package io.github.singhalmradul.product_management.services.implementations;

import static java.lang.String.format;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.entities.Category;
import io.github.singhalmradul.product_management.repositories.CategoryRepository;
import io.github.singhalmradul.product_management.services.CategoryService;
import io.github.singhalmradul.product_management.services.MediaService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final MediaService mediaService;

    @Override
    public Category saveCategory(Category category) {

        if (category.getId() != null) {
            final var existingCategory = getCategoryById(category.getId());
            for (final var image : existingCategory.getImages()) {
                if (!category.getImages().contains(image)) {
                    mediaService.deleteFile(image);
                }
            }
        }

        return repository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID id) {
        var category = getCategoryById(id);
        mediaService.deleteFiles(category.getImages());
        repository.delete(category);
    }

    @Override
    public List<Category> getCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException(format(
                "Category with id %s not found",
                id
            )))
        ;
    }

    @Override
    public List<String> addCategoryImages(UUID categoryId, List<Part> images) {
        var category = getCategoryById(categoryId);
        var imageUrls = mediaService.saveFiles(images.stream().map(part -> {
            try {
                return part.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }).toList());
        category.getImages().addAll(imageUrls);
        return repository.save(category).getImages();
    }

    @Override
    public Category getReferenceById(UUID id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<Category> findByNameSimilar(String name) {
        return repository.findByNameSimilar(name);
    }

}
