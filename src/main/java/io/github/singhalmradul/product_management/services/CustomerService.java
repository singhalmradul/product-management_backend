package io.github.singhalmradul.product_management.services;

import java.util.List;
import java.util.UUID;

import io.github.singhalmradul.product_management.model.entities.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    void deleteCustomerById(UUID id);

    List<Customer> getCustomers();

    Customer getCustomerById(UUID id);

    Customer getReferenceById(UUID id);

    List<Customer> findByNameSimilar(String name);
}