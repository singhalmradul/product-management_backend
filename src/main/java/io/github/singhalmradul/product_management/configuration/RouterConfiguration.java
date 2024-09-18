package io.github.singhalmradul.product_management.configuration;

import static io.github.singhalmradul.product_management.constants.PathVariable.CATEGORY_ID;
import static io.github.singhalmradul.product_management.constants.PathVariable.PRODUCT_ID;
import static io.github.singhalmradul.product_management.constants.UriConstants.CATEGORIES;
import static io.github.singhalmradul.product_management.constants.UriConstants.IMAGES;
import static io.github.singhalmradul.product_management.constants.UriConstants.PRODUCTS;
import static io.github.singhalmradul.product_management.constants.UriConstants.VERSION_1;
import static io.github.singhalmradul.product_management.constants.UriConstants.pathVariable;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;
import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.CategoryHandler;
import io.github.singhalmradul.product_management.handlers.ProductHandler;

@Configuration
public class RouterConfiguration {

    @Bean
    RouterFunction<ServerResponse> productRouter(ProductHandler handler) {

        return route()
            .path(VERSION_1, builder -> builder
                .path(PRODUCTS, builder1 -> builder1
                    .path(pathVariable(PRODUCT_ID), builder2 -> builder2
                        .GET(handler::getProduct)
                        .path(IMAGES, builder3 -> builder3
                            .POST(contentType(MULTIPART_FORM_DATA), handler::addProductImages)
                        )
                    )
                    .GET(handler::getAllProducts)
                    .POST(handler::createProduct)
                    .PUT(handler::updateProduct)
                )
            )
            .build();
    }

    @Bean
    RouterFunction<ServerResponse> categoryRouter(CategoryHandler handler) {

        return route()
            .path(VERSION_1, builder -> builder
                .path(CATEGORIES, builder1 -> builder1
                    .path(pathVariable(CATEGORY_ID), builder2 -> builder2
                        .GET(handler::getCategory)
                        .path(IMAGES, builder3 -> builder3
                            .POST(contentType(MULTIPART_FORM_DATA), handler::addCategoryImages)
                        )
                    )
                    .GET(handler::getCategories)
                    .POST(handler::createCategory)
                )
            )
            .build()
        ;
    }

}
