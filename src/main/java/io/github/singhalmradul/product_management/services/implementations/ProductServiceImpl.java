package io.github.singhalmradul.product_management.services.implementations;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.singhalmradul.product_management.model.Product;
import io.github.singhalmradul.product_management.repositories.ProductRepository;
import io.github.singhalmradul.product_management.services.CategoryService;
import io.github.singhalmradul.product_management.services.MediaService;
import io.github.singhalmradul.product_management.services.ProductService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_CODE_NOT_FOUND_TEMPLATE = "Product with code %s not found";
    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final MediaService mediaService;

    @Override
    @Transactional
    public Product saveProduct(final Product product) {
        final var categories = product
            .getCategories()
            .stream()
            .map(category ->
                categoryService
                    .getReferenceById(category.getId())
                    .addProduct(product)
            )
            .toList();
        product.setCategories(categories);
        return repository.save(product);
    }

    @Override
    public Product getProductByCode(final String code) {
        return repository
            .findByCode(code)
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                PRODUCT_CODE_NOT_FOUND_TEMPLATE,
                code
            )))
        ;
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
            )))
        ;
    }

    @Override
    @Transactional
    public List<String> addProductImages(final String productId, final List<Part> images) {
        final Product product = getProductById(productId);
        final List<String> imageUrls = mediaService.saveFiles(images.stream().map(part -> {
            try {
                return part.getInputStream();
            } catch (final IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }).toList()
        );
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
        final Product product = getProductById(id);
        mediaService.deleteFiles(product.getImages());
        repository.delete(product);
    }

}
