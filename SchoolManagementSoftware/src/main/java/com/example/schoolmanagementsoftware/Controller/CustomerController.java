package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.Model.Customer;
import com.example.schoolmanagementsoftware.Repository.CustomerRepository;
import com.example.schoolmanagementsoftware.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity getAllCustomer(@AuthenticationPrincipal Customer customerId){
        return ResponseEntity.status(200).body("customer get");
    }

    @PostMapping("/register")
    public ResponseEntity addCustomer(@Valid @RequestBody Customer customer){
        this.customerService.addCustomer(customer);
        return ResponseEntity.status(200).body("customer added");
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal Customer customerId, @Valid @RequestBody Customer customer){
        this.customerService.updateCustomer(customerId.getId(), customer);
        return ResponseEntity.status(200).body("customer updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal Customer customerId){
        this.customerService.deleteCustomer(customerId.getId());
        return ResponseEntity.status(200).body("customer deleted");
    }


}