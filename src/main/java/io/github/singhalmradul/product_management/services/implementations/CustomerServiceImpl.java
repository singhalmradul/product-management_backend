package io.github.singhalmradul.product_management.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.singhalmradul.product_management.model.entities.Customer;
import io.github.singhalmradul.product_management.repositories.CustomerRepository;
import io.github.singhalmradul.product_management.services.CustomerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void deleteCustomerById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format(
            "Customer with id %s not found",
            id
        )));
    }

    @Override
    public Customer getReferenceById(UUID id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<Customer> findByNameSimilar(String name) {
        return repository.findByNameSimilar(name);
    }
}
