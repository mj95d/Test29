package com.example.schoolmanagementsoftware.Repository;

import com.example.schoolmanagementsoftware.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomerId(Integer customerId);

    Order findOrderByIdAndCustomer_Id( Integer orderId, Integer userId);

    Order findOrderByIdAndCustomer_Id ( Integer userId);

}