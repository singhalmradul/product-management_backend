package io.github.singhalmradul.product_management.services.implementations;

import java.io.IOException;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.OrderRequest;
import io.github.singhalmradul.product_management.services.MediaService;
import io.github.singhalmradul.product_management.services.OrderService;
import io.github.singhalmradul.product_management.services.PdfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    private final PdfService pdfService;
    private final MediaService mediaService;

    @Override
    public String getOrderPdf(OrderRequest orderRequest) {
        final var pdf = pdfService.generateOrderPdf(orderRequest);
        try (var inputStream = pdf.toURI().toURL().openStream()) {
            return mediaService.saveFile(inputStream);
        } catch (IOException e) {
            log.error("Failed to save pdf: {}", e.getMessage());
            return null;
        }
    }
}
