package io.github.singhalmradul.product_management.services;

import java.util.List;
import java.util.UUID;

import io.github.singhalmradul.product_management.model.entities.OrderRequest;
import io.github.singhalmradul.product_management.model.request.OrderRequestObject;

public interface OrderService {

    byte[] getOrderPdf(UUID orderId);

    OrderRequest getOrderById(UUID id);

    OrderRequest saveOrder(OrderRequestObject order);

    List<OrderRequest> getAllOrders();
}
