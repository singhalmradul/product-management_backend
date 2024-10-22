package io.github.singhalmradul.product_management.services.implementations;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.OrderRequest;
import io.github.singhalmradul.product_management.repositories.OrderRepository;
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
    private final OrderRepository repository;
    private final MediaService mediaService;

    @Override
    public String getOrderPdf(String orderId) {

        final var orderRequest = repository.findById(orderId).orElse(null);

        if (orderRequest == null) {
            log.error("Order not found for id: {}", orderId);
            return null;
        }

        if (orderRequest.getId() != null) {
            return orderRequest.getPdf();
        }

        final var pdf = pdfService.generateOrderPdf(orderRequest);
        try (var inputStream = pdf.toURI().toURL().openStream()) {
            return mediaService.saveFile(inputStream);
        } catch (IOException e) {
            log.error("Failed to save pdf: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public OrderRequest saveOrder(OrderRequest orderRequest) {
        return repository.save(orderRequest);
    }

    @Override
    public List<OrderRequest> getAllOrders() {
        return repository.findAll();
    }
}
