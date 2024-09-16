package io.github.singhalmradul.product_management.services;

import java.util.List;

import io.github.singhalmradul.product_management.model.OrderProduct;
import io.github.singhalmradul.product_management.model.OrderRequest;

public interface PdfService {

    String generatePdf(OrderRequest order, List<OrderProduct> products);
}
