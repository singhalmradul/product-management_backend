package io.github.singhalmradul.product_management.services;

import java.util.List;

import io.github.singhalmradul.product_management.model.Product;
import jakarta.servlet.http.Part;

public interface ProductService {

    Product saveProduct(Product products);

    List<Product> getAllProducts();

    Product getProductById(String id);

    List<String> addProductImages(String productId, List<Part> images);

    List<Product> searchProductsByName(String query);

    void deleteProduct(String id);
}
