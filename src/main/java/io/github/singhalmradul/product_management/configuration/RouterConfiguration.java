package io.github.singhalmradul.product_management.configuration;

import static io.github.singhalmradul.product_management.constants.PathVariable.CATEGORY_ID;
import static io.github.singhalmradul.product_management.constants.PathVariable.CUSTOMER_ID;
import static io.github.singhalmradul.product_management.constants.PathVariable.ORDER_ID;
import static io.github.singhalmradul.product_management.constants.PathVariable.PRODUCT_CODE;
import static io.github.singhalmradul.product_management.constants.PathVariable.PRODUCT_ID;
import static io.github.singhalmradul.product_management.constants.UriConstants.CATEGORIES;
import static io.github.singhalmradul.product_management.constants.UriConstants.CODE;
import static io.github.singhalmradul.product_management.constants.UriConstants.CUSTOMERS;
import static io.github.singhalmradul.product_management.constants.UriConstants.IMAGES;
import static io.github.singhalmradul.product_management.constants.UriConstants.INDEX;
import static io.github.singhalmradul.product_management.constants.UriConstants.ORDERS;
import static io.github.singhalmradul.product_management.constants.UriConstants.PDF;
import static io.github.singhalmradul.product_management.constants.UriConstants.PRODUCTS;
import static io.github.singhalmradul.product_management.constants.UriConstants.SEARCH;
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
import io.github.singhalmradul.product_management.handlers.CustomerHandler;
import io.github.singhalmradul.product_management.handlers.OrderHandler;
import io.github.singhalmradul.product_management.handlers.ProductHandler;

@Configuration
public class RouterConfiguration {

    @Bean
    RouterFunction<ServerResponse> productRouter(ProductHandler handler) {

        return route()
            .path(VERSION_1, builder -> builder
                // /v1
                .path(PRODUCTS, builder1 -> builder1
                    // /v1/products
                    .GET(SEARCH, handler::searchProductsByName)
                    .path(CODE, builder2 -> builder2
                        // /v1/products/code
                        .GET(pathVariable(PRODUCT_CODE), handler::getProductByCode)
                    )
                    .path(pathVariable(PRODUCT_ID), builder2 -> builder2
                        // /v1/products/{productId}
                        .GET(PDF, handler::getProductPdf)
                        .POST(IMAGES, contentType(MULTIPART_FORM_DATA), handler::addProductImages)
                        .GET(INDEX, handler::getProduct)
                        .PUT(INDEX, handler::updateProduct)
                        .DELETE(INDEX, handler::deleteProduct)
                    )
                    .GET(INDEX, handler::getAllProducts)
                    .POST(INDEX, handler::createProduct)
                )
            )
            .build();
    }

    @Bean
    RouterFunction<ServerResponse> categoryRouter(CategoryHandler handler) {

        return route()
            .path(VERSION_1, builder -> builder
                // /v1
                .path(CATEGORIES, builder1 -> builder1
                    // /v1/categories
                    .GET(SEARCH, handler::searchCategoriesByName)
                    .path(pathVariable(CATEGORY_ID), builder2 -> builder2
                        // /v1/categories/{categoryId}
                        .POST(IMAGES, contentType(MULTIPART_FORM_DATA), handler::addCategoryImages)
                        .GET(INDEX, handler::getCategory)
                        .PUT(INDEX, handler::updateCategory)
                        .DELETE(INDEX, handler::deleteCategory)
                    )
                    .GET(INDEX, handler::getCategories)
                    .POST(INDEX, handler::createCategory)
                )
            )
            .build()
        ;
    }

    @Bean
    RouterFunction<ServerResponse> customerRouter(CustomerHandler handler) {

        return route()
            .path(VERSION_1, builder -> builder
                // /v1
                .path(CUSTOMERS, builder1 -> builder1
                    // /v1/customers
                    .GET(SEARCH, handler::searchCustomersByName)
                    .path(pathVariable(CUSTOMER_ID), builder2 -> builder2
                        // /v1/customers/{customerId}
                        .GET(INDEX, handler::getCustomer)
                        .PUT(INDEX, handler::updateCustomer)
                        .DELETE(INDEX, handler::deleteCustomer)
                    )
                    .GET(INDEX, handler::getAllCustomers)
                    .POST(INDEX, handler::createCustomer)
                )
            )
            .build()
        ;
    }

    @Bean
    RouterFunction<ServerResponse> orderRouter(OrderHandler handler) {

        return route()
            .path(VERSION_1, builder -> builder
                // /v1
                .path(ORDERS, builder1 -> builder1
                    .POST(INDEX, handler::createOrder)
                    .GET(INDEX, handler::getAllOrders)
                    // /v1/orders
                    .path(pathVariable(ORDER_ID), builder2 -> builder2
                        // /v1/orders/{orderId}
                        .GET(PDF, handler::getOrderPdf)
                        .GET(INDEX, handler::getOrderById)
                    )
                )
            )
            .build()
        ;
    }

}
