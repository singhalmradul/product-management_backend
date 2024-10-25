package io.github.singhalmradul.product_management.handlers.implementations;

import static io.github.singhalmradul.product_management.constants.PathVariable.ORDER_ID;
import static io.github.singhalmradul.product_management.constants.UriConstants.ORDERS;
import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.web.servlet.function.ServerResponse.badRequest;
import static org.springframework.web.servlet.function.ServerResponse.created;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.OrderHandler;
import io.github.singhalmradul.product_management.model.request.OrderRequestObject;
import io.github.singhalmradul.product_management.services.OrderService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderHandlerImpl implements OrderHandler {

    private final OrderService orderService;

    @Override
    public ServerResponse getOrderPdf(ServerRequest request) {

            try {
                var orderId = request.pathVariable(ORDER_ID);
                var id = UUID.fromString(orderId);
                String filename = format("order_%s.pdf", orderId);
                return ok()
                    .contentType(APPLICATION_PDF)
                    .header("Content-Disposition", "attachment; filename=" + filename)
                    .header("Cache-Control", "no-cache, no-store, must-revalidate")
                    .body(orderService.getOrderPdf(id))
                ;
            } catch (IllegalArgumentException ex) {
                return badRequest().body(ex.getMessage());
            }
    }

    @Override
    public ServerResponse createOrder(ServerRequest request) {

        try {
            var requestOrder = request.body(OrderRequestObject.class);
            var order = orderService.saveOrder(requestOrder);
            var uri = URI.create(format("%s/%s", ORDERS, order.getId()));
            return created(uri).body(order);
        } catch (ServletException | IOException ex) {
            return badRequest().body(ex.getMessage());
        }
    }

    @Override
    public ServerResponse getAllOrders(ServerRequest request) {
        return ok().body(orderService.getAllOrders());
    }
}
