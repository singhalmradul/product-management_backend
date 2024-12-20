package io.github.singhalmradul.product_management.handlers;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public interface ProductHandler {

    ServerResponse getAllProducts(ServerRequest request);

    ServerResponse createProduct(ServerRequest request);

    ServerResponse updateProduct(ServerRequest request);

    ServerResponse getProduct(ServerRequest request);

    ServerResponse getProductByCode(ServerRequest request);

    ServerResponse addProductImages(ServerRequest request);

    ServerResponse searchProductsByName(ServerRequest request);

    ServerResponse deleteProduct(ServerRequest request);

    ServerResponse getProductPdf(ServerRequest request);
}
