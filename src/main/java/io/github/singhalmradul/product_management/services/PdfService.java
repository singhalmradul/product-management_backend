package io.github.singhalmradul.product_management.services;

import java.nio.file.Path;

import io.github.singhalmradul.product_management.model.entities.OrderRequest;

public interface PdfService {

    Path generateOrderPdf(OrderRequest order);
}
