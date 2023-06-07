package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.DTO.DtoIn;
import com.example.schoolmanagementsoftware.DTO.DtoUp;
import com.example.schoolmanagementsoftware.Model.Customer;
import com.example.schoolmanagementsoftware.Model.Order;
import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.OrderRepository;
import com.example.schoolmanagementsoftware.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderService {
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;

        this.productRepository = productRepository;
    }


    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    private Double calculateTotalPrice(List<Product> orderItems) {
        return orderItems.stream().mapToDouble(Product::getPrice).sum();
    }

    public void addOrder(Customer customer, DtoIn order) {
    }

    public void updateOrder(int id, Integer id1, DtoUp order) {
    }

    public void deleteOrder(int id, Integer id1) {
    }

    public void updateStatus(int id, String status) {
    }
}