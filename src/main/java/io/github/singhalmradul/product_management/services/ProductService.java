package io.github.singhalmradul.product_management.services;

import io.github.singhalmradul.product_management.model.Product;

public interface ProductService {

    Product saveProduct(Product products);
    Product getProductByCode(String code);
    void deleteProduct(String code);
    Object getAllProducts();
}
