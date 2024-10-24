package io.github.singhalmradul.product_management.services;

import java.util.List;

import io.github.singhalmradul.product_management.model.entities.OrderRequest;
import io.github.singhalmradul.product_management.model.response.UriResponse;

public interface OrderService {

    UriResponse getOrderPdf(String orderId);

    OrderRequest getOrderById(String id);

    OrderRequest saveOrder(OrderRequest orderRequest);

    List<OrderRequest> getAllOrders();
}
