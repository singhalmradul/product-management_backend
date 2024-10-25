package io.github.singhalmradul.product_management.handlers.implementations;

import static io.github.singhalmradul.product_management.constants.PathVariable.CUSTOMER_ID;
import static io.github.singhalmradul.product_management.constants.RequestVariable.QUERY;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.badRequest;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.product_management.handlers.CustomerHandler;
import io.github.singhalmradul.product_management.model.entities.Customer;
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
        try {
            var customerId = request.pathVariable(CUSTOMER_ID);
            var id = UUID.fromString(customerId);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(customerService.getCustomerById(id))
            ;
        } catch (IllegalArgumentException e) {
            log.warn("Error while fetching Customer", e);
            return badRequest().body(e.getMessage());
        }
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
        try {
            var customerId = request.pathVariable(CUSTOMER_ID);
            var id = UUID.fromString(customerId);
            customerService.deleteCustomerById(id);
            return ok().build();
        } catch (IllegalArgumentException e) {
            log.warn("Error while deleting Customer", e);
            return badRequest().body(e.getMessage());
        }
    }

    @Override
    public ServerResponse updateCustomer(ServerRequest request) {
        try {
            var customerId = request.pathVariable(CUSTOMER_ID);
            var id = UUID.fromString(customerId);
            var customer = request.body(Customer.class);
            customer.setId(id);
            return ok()
                .contentType(APPLICATION_JSON)
                .body(customerService.saveCustomer(customer))
            ;
        } catch (ServletException | IOException | IllegalArgumentException e) {
            log.warn("Error while updating Customer", e);
            return badRequest().body(e.getMessage());
        }
    }

}
