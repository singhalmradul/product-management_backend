package io.github.singhalmradul.product_management.handlers.implementations;

import static io.github.singhalmradul.product_management.constants.PathVariable.PRODUCT_CODE;
import static io.github.singhalmradul.product_management.constants.PathVariable.PRODUCT_ID;
import static io.github.singhalmradul.product_management.constants.RequestVariable.IMAGES;
import static io.github.singhalmradul.product_management.constants.RequestVariable.QUERY;
import static io.github.singhalmradul.product_management.constants.UriConstants.PRODUCTS;
import static io.github.singhalmradul.product_management.constants.UriConstants.SLASH;
import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.web.servlet.function.ServerResponse.badRequest;
import static org.springframework.web.servlet.function.ServerResponse.created;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.ProductHandler;
import io.github.singhalmradul.product_management.model.entities.Product;
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
            var productId = request.pathVariable(PRODUCT_ID);
            var id = UUID.fromString(productId);
            var product = request.body(Product.class);
            product.setId(id);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(productService.saveProduct(product))
            ;
        } catch (ServletException | IOException | IllegalArgumentException e) {
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
        try {
            var productId = request.pathVariable(PRODUCT_ID);
            var id = UUID.fromString(productId);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(productService.getProductById(id))
            ;
        } catch (IllegalArgumentException e) {
            log.warn("Error while fetching product", e);
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse addProductImages(ServerRequest request) {
        try {
            var productId = request.pathVariable(PRODUCT_ID);
            var id = UUID.fromString(productId);
            var images = request.multipartData().get(IMAGES);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(productService.addProductImages(id, images))
            ;
        } catch (ServletException | IOException | IllegalArgumentException e) {
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
        try {
            var productId = request.pathVariable(PRODUCT_ID);
            var id = UUID.fromString(productId);
            productService.deleteProduct(id);
            return ok().build();
        } catch (IllegalArgumentException e) {
            log.warn("Error while deleting product", e);
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse getProductByCode(ServerRequest request) {
        var productCode = request.pathVariable(PRODUCT_CODE);
        return ok()
            .contentType(APPLICATION_JSON)
            .body(productService.getProductByCode(productCode))
        ;
    }

    @Override
    public ServerResponse getProductPdf(ServerRequest request) {
        try {
            var productId = request.pathVariable(PRODUCT_ID);
            var id = UUID.fromString(productId);
            String filename = format("product_%s.pdf", productId);
            return ok()
                .contentType(APPLICATION_PDF)
                .header("Content-Disposition", "attachment; filename=" + filename)
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .body(productService.getProductPdf(id))
            ;
        } catch (IllegalArgumentException ex) {
            return badRequest().body(ex.getMessage());
        }
    }
}
