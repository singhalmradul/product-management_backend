package io.github.singhalmradul.product_management.services.implementations;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.Product;
import io.github.singhalmradul.product_management.repositories.ProductRepository;
import io.github.singhalmradul.product_management.services.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_CODE_NOT_FOUND_TEMPLATE = "Product with code %s not found";
    private final ProductRepository repository;

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
    public Object getAllProducts() {
        return repository.findAll();
    }

}
