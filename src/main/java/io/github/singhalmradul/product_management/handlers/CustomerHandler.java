package io.github.singhalmradul.product_management.handlers;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public interface CustomerHandler {

    ServerResponse createCustomer(ServerRequest request);

    ServerResponse getAllCustomers(ServerRequest request);

    ServerResponse getCustomer(ServerRequest request);

    ServerResponse searchCustomersByName(ServerRequest request);

    ServerResponse updateCustomer(ServerRequest request);

    ServerResponse deleteCustomer(ServerRequest request);
}
