package io.github.singhalmradul.product_management.services;

import io.github.singhalmradul.product_management.model.OrderRequest;

public interface OrderService {

    String getOrderPdf(OrderRequest orderRequest);
}
