package io.github.singhalmradul.product_management.handlers;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public interface OrderHandler {

    ServerResponse getOrderPdf(ServerRequest request);
}
