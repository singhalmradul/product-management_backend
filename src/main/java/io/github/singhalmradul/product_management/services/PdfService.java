package io.github.singhalmradul.product_management.services;

import java.io.File;

import io.github.singhalmradul.product_management.model.OrderRequest;

public interface PdfService {

    File generateOrderPdf(OrderRequest order);
}
