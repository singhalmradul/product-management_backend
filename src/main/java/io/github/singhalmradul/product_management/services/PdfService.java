package io.github.singhalmradul.product_management.services;

import java.io.OutputStream;

import io.github.singhalmradul.product_management.model.entities.OrderRequest;
import io.github.singhalmradul.product_management.model.entities.Product;

public interface PdfService {

    boolean generateOrderPdf(OrderRequest order, OutputStream out);

    boolean generateProductPdf(Product product, OutputStream out);
}
