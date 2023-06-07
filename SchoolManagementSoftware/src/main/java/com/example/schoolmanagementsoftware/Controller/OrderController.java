package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.DTO.DtoIn;
import com.example.schoolmanagementsoftware.DTO.DtoUp;
import com.example.schoolmanagementsoftware.Model.Customer;
import com.example.schoolmanagementsoftware.Service.OrderService;
import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

        private final OrderService orderService;

        @GetMapping( "/getAll" )
        public ResponseEntity getAllOrder(@AuthenticationPrincipal Customer customerId) {
            return ResponseEntity.status(200).body(this.orderService.getOrderById(customerId.getId()));
        }

        @PostMapping( "/add" )
        public ResponseEntity addOrder(@AuthenticationPrincipal Customer customer, @Valid @RequestBody DtoIn order) {
            this.orderService.addOrder(customer, order);
            return ResponseEntity.status(200).body("order added");
        }

        @PutMapping( "/update/{id}" )
        public ResponseEntity updateOrder(@AuthenticationPrincipal Customer customer, @PathVariable int id, @Valid @RequestBody DtoUp order) {
            this.orderService.updateOrder(id, customer.getId(), order);
            return ResponseEntity.status(200).body("order updated");
        }

        @DeleteMapping( "/delete/{id}" )
        public ResponseEntity delete(@PathVariable int id, @AuthenticationPrincipal Customer customer) {
            this.orderService.deleteOrder(id, customer.getId());
            return ResponseEntity.status(200).body("order deleted");
        }

        @PutMapping( "/update/status/{id}" )
        public ResponseEntity update(@PathVariable int id, @RequestParam String status) {
            this.orderService.updateStatus(id, status);
            return ResponseEntity.status(200).body("status updated");
        }

        @GetMapping( "/get/order/{id}" )
        public ResponseEntity update(@AuthenticationPrincipal Customer customerId, @PathVariable int id) {
            Order order = this.orderService.getOrderById(customerId.getId());
            return ResponseEntity.status(200).body(order);
        }
    }