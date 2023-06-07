package com.example.schoolmanagementsoftware;

import com.example.schoolmanagementsoftware.Model.Customer;
import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.CustomerRepository;
import com.example.schoolmanagementsoftware.Service.CustomerService;
import com.example.schoolmanagementsoftware.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    ProductService productService;

    Product product;
    Customer customer;

    @BeforeEach
    void setUp() {
        product=new Product(null,"product1",10);
        customer=new Customer(null,false,String.valueOf(product.getPrice()),null);
    }

    @Test
    public void addCustomerTest(){
        when(productService.getProductByName(product.getName())).thenReturn(product);
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer addedCustomer=customerService.addCustomer(product.getName());
        Assertions.assertEquals(addedCustomer.getRole(),String.valueOf(product.getPrice()));
        verify(productService,times(1)).getProductByName(product.getName());
        verify(customerRepository,times(1)).save(customer);
    }

    @Test
    public void getCustomerTest(){
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Customer foundCustomer=customerService.getCustomer(customer.getId());
        Assertions.assertEquals(foundCustomer,customer);
        verify(customerRepository,times(1)).findById(customer.getId());
    }

    @Test
    public void updateCustomerTest(){
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        customer.setPassword(String.valueOf(true));
        customer.setRole(String.valueOf(product.getPrice()+1));
        customerService.updateCustomer(customer.getId(),customer);
        verify(customerRepository,times(1)).findById(customer.getId());
        verify(customerRepository,times(1)).save(customer);
    }

    @Test
    public void deleteCustomerTest(){
        customerService.deleteCustomer(customer.getId());
        verify(customerRepository,times(1)).deleteById(customer.getId());
    }

    @Test
    public void getAllCustomersTest(){
        List<Customer> customers=new ArrayList<>();
        customers.add(new Customer(null,false,String.valueOf(product.getPrice()),null));
        customers.add(new Customer(null,true,String.valueOf(product.getPrice()+1),null));
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> foundCustomers=customerService.getAllCustomers();
        Assertions.assertEquals(foundCustomers.size(),2);
        verify(customerRepository,times(1)).findAll();
    }
}
