package com.example.schoolmanagementsoftware.DTO;

import com.example.schoolmanagementsoftware.Model.Customer;
import com.example.schoolmanagementsoftware.Model.Order;
import com.example.schoolmanagementsoftware.Model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class DtoUp {

    private String username;
    private Set<Customer> customer;
    @NotNull
    private Integer quantity;
    @NotNull
    private Integer productId;
    @NotNull
    private Set<Product> products;
    private Set<Order> order;
}