package io.github.singhalmradul.product_management.services.implementations;

import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Files.newInputStream;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.entities.OrderRequest;
import io.github.singhalmradul.product_management.model.response.UriResponse;
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
    public UriResponse getOrderPdf(String orderId) {

        final var orderRequest = repository.findById(orderId).orElse(null);

        if (orderRequest == null) {
            log.error("Order not found for id: {}", orderId);
            return null;
        }

        if (orderRequest.getPdf() != null) {
            return new UriResponse(orderRequest.getPdf());
        }

        final var pdf = pdfService.generateOrderPdf(orderRequest);
        try (var inputStream = newInputStream(pdf)) {
            var uri = mediaService.saveFile(inputStream);
            orderRequest.setPdf(uri);
            repository.save(orderRequest);
            return new UriResponse(uri);
        } catch (IOException e) {
            log.error("Failed to save pdf: {}", e.getMessage());
            return null;
        } finally {
            try {
                deleteIfExists(pdf);
                log.info("Deleted temporary file: {}", pdf);
            } catch (IOException e) {
                log.error("Failed to delete pdf: {}", e.getMessage());
            }
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

    @Override
    public OrderRequest getOrderById(String id) {
        return repository.findById(id).orElseThrow();
    }
}
