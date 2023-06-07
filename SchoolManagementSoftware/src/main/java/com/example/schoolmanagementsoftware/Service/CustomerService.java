package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Model.Customer;
import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductService productService;

    public CustomerService(CustomerRepository customerRepository, ProductService productService) {
        this.customerRepository = customerRepository;
        this.productService = productService;
    }

    public Customer addCustomer(String productName) {
        Product product = productService.getProductByName(productName);
        Customer customer = new Customer();
        customer.setPassword(String.valueOf(false));
        customer.setRole(String.valueOf(product.getPrice()));
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void updateCustomer(Integer id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setPassword(customer.getPassword());
            existingCustomer.setRole(customer.getRole());
            customerRepository.save(existingCustomer);
        }
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}