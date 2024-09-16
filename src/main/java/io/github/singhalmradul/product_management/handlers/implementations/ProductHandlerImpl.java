package io.github.singhalmradul.product_management.handlers.implementations;

import static io.github.singhalmradul.product_management.constants.UriConstants.PRODUCTS;
import static io.github.singhalmradul.product_management.constants.UriConstants.SLASH;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.badRequest;
import static org.springframework.web.servlet.function.ServerResponse.created;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.ProductHandler;
import io.github.singhalmradul.product_management.model.Product;
import io.github.singhalmradul.product_management.services.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductHandlerImpl implements ProductHandler{

    private final ProductService productService;


    @Override
    public ServerResponse createProduct(ServerRequest request) {
        try {
            var product = request.body(Product.class);
            return created(URI.create(PRODUCTS + SLASH + product.getCode()))
                .contentType(APPLICATION_JSON)
                .body(productService.saveProduct(product))
            ;
        } catch (Exception e) {
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse updateProduct(ServerRequest request) {
        try {
            var product = request.body(Product.class);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(productService.saveProduct(product))
            ;
        } catch (Exception e) {
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse getAllProducts(ServerRequest request) {
        return ok()
            .contentType(APPLICATION_JSON)
            .body(productService.getAllProducts())
        ;
    }
}
