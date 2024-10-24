package io.github.singhalmradul.product_management.services.implementations;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.singhalmradul.product_management.model.entities.Product;
import io.github.singhalmradul.product_management.repositories.ProductRepository;
import io.github.singhalmradul.product_management.services.CategoryService;
import io.github.singhalmradul.product_management.services.MediaService;
import io.github.singhalmradul.product_management.services.ProductService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final MediaService mediaService;

    @Override
    @Transactional
    public Product saveProduct(final Product product) {
        if (product.getId() != null) {
            final var existingProduct = getProductById(product.getId());

            for (final var image : existingProduct.getImages()) {
                if (!product.getImages().contains(image)) {
                    mediaService.deleteFile(image);
                }
            }
        }

        var categories = product
                .getCategories()
                .stream()
                .map(category -> categoryService
                .getReferenceById(category.getId())
                .addProduct(product)
                )
                .collect(toSet());

        product.setCategories(categories);

        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(final String id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                "Product with id %s not found",
                id
        )));
    }

    @Override
    @Transactional
    public List<String> addProductImages(final String productId, final List<Part> images) {
        if (isEmpty(images)) {
            return emptyList();
        }
        final var product = getProductById(productId);
        final var inputStreams = images
                .stream()
                .map(part -> {
                    try {
                        return part.getInputStream();
                    } catch (final IOException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }).toList();
        final var imageUrls = mediaService.saveFiles(inputStreams);
        product.getImages().addAll(imageUrls);
        repository.save(product);
        return imageUrls;
    }

    @Override
    public List<Product> searchProductsByName(final String query) {
        return repository.findByNameSimilar(query);
    }

    @Override
    @Transactional
    public void deleteProduct(final String id) {
        final var product = getProductById(id);
        mediaService.deleteFiles(product.getImages());
        repository.delete(product);
    }

    @Override
    public Product getProductByCode(String code) {
        return repository.findByCode(code).orElseThrow()    ;
    }

}
