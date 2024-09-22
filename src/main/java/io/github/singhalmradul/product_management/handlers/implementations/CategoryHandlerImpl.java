package io.github.singhalmradul.product_management.handlers.implementations;

import static io.github.singhalmradul.product_management.constants.PathVariable.CATEGORY_ID;
import static io.github.singhalmradul.product_management.constants.RequestVariable.IMAGES;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.badRequest;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.CategoryHandler;
import io.github.singhalmradul.product_management.model.Category;
import io.github.singhalmradul.product_management.services.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CategoryHandlerImpl implements CategoryHandler {

    private final CategoryService categoryService;

    @Override
    public ServerResponse createCategory(ServerRequest request) {
        try {
            var category = request.body(Category.class);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(categoryService.saveCategory(category))
            ;
        } catch (Exception e) {
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse getCategories(ServerRequest request) {
        return ok()
            .contentType(APPLICATION_JSON)
            .body(categoryService.getCategories())
        ;
    }

    @Override
    public ServerResponse getCategory(ServerRequest request) {
        var categoryId = request.pathVariable(CATEGORY_ID);
        return ok()
            .contentType(APPLICATION_JSON)
            .body(categoryService.getCategoryById(categoryId))
        ;
    }

    @Override
    public ServerResponse addCategoryImages(ServerRequest request) {
        var categoryId = request.pathVariable(CATEGORY_ID);
        List<Part> images;
        try {
            images = request.multipartData().get(IMAGES);
            return ok()
            .contentType(APPLICATION_JSON)
            .body(categoryService.addCategoryImages(categoryId, images))
        ;
        } catch (IOException | ServletException e) {
            return badRequest().body(e.getMessage());
        }

    }

}
