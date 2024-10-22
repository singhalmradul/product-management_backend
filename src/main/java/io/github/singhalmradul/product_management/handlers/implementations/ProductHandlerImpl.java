package io.github.singhalmradul.product_management.handlers.implementations;

import static io.github.singhalmradul.product_management.constants.PathVariable.PRODUCT_CODE;
import static io.github.singhalmradul.product_management.constants.PathVariable.PRODUCT_ID;
import static io.github.singhalmradul.product_management.constants.RequestVariable.IMAGES;
import static io.github.singhalmradul.product_management.constants.RequestVariable.QUERY;
import static io.github.singhalmradul.product_management.constants.UriConstants.PRODUCTS;
import static io.github.singhalmradul.product_management.constants.UriConstants.SLASH;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.badRequest;
import static org.springframework.web.servlet.function.ServerResponse.created;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;
import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.ProductHandler;
import io.github.singhalmradul.product_management.model.Product;
import io.github.singhalmradul.product_management.services.ProductService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        } catch (ServletException | IOException e) {
            log.warn("Error while creating product", e);
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse updateProduct(ServerRequest request) {
        try {
            var id = request.pathVariable(PRODUCT_ID);
            var product = request.body(Product.class);
            product.setId(id);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(productService.saveProduct(product))
            ;
        } catch (ServletException | IOException e) {
            log.warn("Error while updating product", e);
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

    @Override
    public ServerResponse getProduct(ServerRequest request) {
        var productId = request.pathVariable(PRODUCT_ID);
        return ok()
            .contentType(APPLICATION_JSON)
            .body(productService.getProductById(productId))
        ;
    }

    @Override
    public ServerResponse addProductImages(ServerRequest request) {
        try {
            var productId = request.pathVariable(PRODUCT_ID);
            var images = request.multipartData().get(IMAGES);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(productService.addProductImages(productId, images))
            ;
        } catch (ServletException | IOException e) {
            log.warn("Error while adding product images", e);
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse searchProductsByName(ServerRequest request) {
        var query = request.param(QUERY).orElseThrow();
        return ok()
            .contentType(APPLICATION_JSON)
            .body(productService.searchProductsByName(query))
        ;
    }

    @Override
    public ServerResponse deleteProduct(ServerRequest request) {
        var productId = request.pathVariable(PRODUCT_ID);
        productService.deleteProduct(productId);
        return ok().build();
    }

    @Override
    public ServerResponse getProductByCode(ServerRequest request) {
        var productCode = request.pathVariable(PRODUCT_CODE);
        return ok()
            .contentType(APPLICATION_JSON)
            .body(productService.getProductByCode(productCode))
        ;
    }
}
