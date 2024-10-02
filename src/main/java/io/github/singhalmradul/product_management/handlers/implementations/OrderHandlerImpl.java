package io.github.singhalmradul.product_management.handlers.implementations;

import static org.springframework.web.servlet.function.ServerResponse.badRequest;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.OrderHandler;
import io.github.singhalmradul.product_management.model.OrderRequest;
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
            var requestOrder = request.body(OrderRequest.class);
            return ok().body(orderService.getOrderPdf(requestOrder));
        } catch (ServletException | IOException ex) {
            return badRequest().body(ex.getMessage());
        }
    }
}
