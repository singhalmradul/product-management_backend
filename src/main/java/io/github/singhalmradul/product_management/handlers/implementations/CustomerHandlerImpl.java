package io.github.singhalmradul.product_management.handlers.implementations;

import static io.github.singhalmradul.product_management.constants.PathVariable.CUSTOMER_ID;
import static io.github.singhalmradul.product_management.constants.RequestVariable.QUERY;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.badRequest;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.CustomerHandler;
import io.github.singhalmradul.product_management.model.Customer;
import io.github.singhalmradul.product_management.services.CustomerService;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomerHandlerImpl implements CustomerHandler {

    private final CustomerService customerService;

    @Override
    public ServerResponse createCustomer(ServerRequest request) {
        try {
            var customer = request.body(Customer.class);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(customerService.saveCustomer(customer))
            ;
        } catch (ServletException | IOException e) {
            log.warn("Error while creating Customer", e);
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse getAllCustomers(ServerRequest request) {
        return ok()
            .contentType(APPLICATION_JSON)
            .body(customerService.getCustomers())
        ;
    }

    @Override
    public ServerResponse getCustomer(ServerRequest request) {
        var customerId = request.pathVariable(CUSTOMER_ID);
        return ok()
            .contentType(APPLICATION_JSON)
            .body(customerService.getCustomerById(customerId))
        ;
    }

    @Override
    public ServerResponse searchCustomersByName(ServerRequest request) {
        var query = request.param(QUERY).orElseThrow();
        return ok()
            .contentType(APPLICATION_JSON)
            .body(customerService.findByNameSimilar(query))
        ;
    }

    @Override
    public ServerResponse deleteCustomer(ServerRequest request) {
        var customerId = request.pathVariable(CUSTOMER_ID);
        customerService.deleteCustomerById(customerId);
        return ok().build();
    }

    @Override
    public ServerResponse updateCustomer(ServerRequest request) {
        try {
            var id = request.pathVariable(CUSTOMER_ID);
            var customer = request.body(Customer.class);
            customer.setId(id);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(customerService.saveCustomer(customer))
            ;
        } catch (ServletException | IOException e) {
            log.warn("Error while updating Customer", e);
            return badRequest().body(e.getMessage());
        }
    }

}
