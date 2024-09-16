package io.github.singhalmradul.product_management.handlers;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public interface CategoryHandler {

    ServerResponse createCategory(ServerRequest request);

    ServerResponse getCategories(ServerRequest request);

    ServerResponse getCategory(ServerRequest request);

    ServerResponse addCategoryImages(ServerRequest request);
}
