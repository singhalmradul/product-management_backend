package io.github.singhalmradul.product_management.services;

import java.util.List;

import io.github.singhalmradul.product_management.model.OrderRequest;

public interface OrderService {

    String getOrderPdf(OrderRequest orderRequest);

    OrderRequest saveOrder(OrderRequest orderRequest);

    List<OrderRequest> getAllOrders();
}
