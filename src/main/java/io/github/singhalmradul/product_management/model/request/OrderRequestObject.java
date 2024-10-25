package io.github.singhalmradul.product_management.model.request;

import java.time.LocalDate;
import java.util.List;

import io.github.singhalmradul.product_management.model.entities.Customer;
import io.github.singhalmradul.product_management.model.entities.OrderProduct;
import lombok.Data;

@Data
public class OrderRequestObject {

    private Customer customer;

    private LocalDate date;

    private String pdf;

    private List<OrderProduct> products;
}
