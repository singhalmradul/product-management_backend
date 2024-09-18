package io.github.singhalmradul.product_management.services.implementations;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.Product;
import io.github.singhalmradul.product_management.repositories.ProductRepository;
import io.github.singhalmradul.product_management.services.MediaService;
import io.github.singhalmradul.product_management.services.ProductService;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_CODE_NOT_FOUND_TEMPLATE = "Product with code %s not found";
    private final ProductRepository repository;
    private final MediaService mediaService;

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getProductByCode(String code) {
        return repository
            .findByCode(code)
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                PRODUCT_CODE_NOT_FOUND_TEMPLATE,
                code
            )))
        ;
    }

    @Override
    public void deleteProduct(String code) {
        repository.deleteByCode(code);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                "Product with id %s not found",
                id
            )))
        ;
    }

    @Override
    public List<String> addProductImages(String productId, List<Part> images) {
        Product product = getProductById(productId);
        List<String> imageUrls = mediaService.saveImageParts(images);
        product.getImages().addAll(imageUrls);
        repository.save(product);
        return imageUrls;
    }

}
