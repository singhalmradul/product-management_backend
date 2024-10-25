package io.github.singhalmradul.product_management.services.implementations;

import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Files.readAllBytes;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.entities.OrderProduct;
import io.github.singhalmradul.product_management.model.entities.OrderRequest;
import io.github.singhalmradul.product_management.model.request.OrderRequestObject;
import io.github.singhalmradul.product_management.repositories.OrderRepository;
import io.github.singhalmradul.product_management.services.CustomerService;
import io.github.singhalmradul.product_management.services.OrderService;
import io.github.singhalmradul.product_management.services.PdfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private final OrderRepository repository;
    private final PdfService pdfService;
    private final CustomerService customerService;


    @Override
    public byte[] getOrderPdf(final UUID orderId) {

        final var orderRequest = repository.findById(orderId).orElseThrow();

        final var products = orderRequest.getProducts().stream().filter(OrderProduct::isPending).toList();
        // TODO: remove order_product from order_request where completed=true?
        orderRequest.setProducts(products);

        final var pdf = pdfService.generateOrderPdf(orderRequest);
        try {
            return readAllBytes(pdf);
        } catch (final IOException e) {
            log.error("Failed to save pdf: {}", e.getMessage());
            return EMPTY_BYTE_ARRAY;
        } finally {
            try {
                deleteIfExists(pdf);
                log.info("Deleted temporary file: {}", pdf);
            } catch (final IOException e) {
                log.error("Failed to delete pdf: {}", e.getMessage());
            }
        }

    }

    @Override
    public OrderRequest saveOrder(final OrderRequestObject order) {
        final var customerId = order.getCustomer().getId();
        final var customer = customerService.getCustomerById(customerId);
        final var orderRequest = OrderRequest
            .builder()
            .customer(customer)
            .date(order.getDate())
            .build()
        ;
        final var savedOrder = repository.save(orderRequest);
        order.getProducts().forEach(savedOrder::addProduct);
        return repository.save(savedOrder);
    }

    @Override
    public List<OrderRequest> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public OrderRequest getOrderById(final UUID id) {
        return repository.findById(id).orElseThrow();
    }
}
