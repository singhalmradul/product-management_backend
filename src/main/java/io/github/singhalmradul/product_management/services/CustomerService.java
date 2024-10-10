package io.github.singhalmradul.product_management.services;

import java.util.List;

import io.github.singhalmradul.product_management.model.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    void deleteCustomerById(String id);

    List<Customer> getCustomers();

    Customer getCustomerById(String id);

    Customer getReferenceById(String id);

    List<Customer> findByNameSimilar(String name);
}