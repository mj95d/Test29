package com.example.schoolmanagementsoftware;

import com.example.schoolmanagementsoftware.Model.Customer;
import com.example.schoolmanagementsoftware.Model.Order;
import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.OrderRepository;
import com.example.schoolmanagementsoftware.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith( MockitoExtension.class)
public class OrderTest {

    @InjectMocks
    OrderRepository orderRepository;
    @Mock
    CustomerRepositoryTest customerRepository;
    @Mock
    ProductService productService;

    Product product;
    Customer customer;
    Order order;

    @BeforeEach
    void setUp() {
        product=new Product(null,"product1",10);
        customer=new Customer(null,false,String.valueOf(product.getPrice()),null);
        order=new Order(1,product.getPrice(),LocalDate.now(),"new",customer,product);
    }

    @Test
    public void saveTest(){
        when(orderRepository.save(order)).thenReturn(order);
        Order savedOrder=orderRepository.save(order);
        Assertions.assertNotNull(savedOrder.getId());
        Assertions.assertEquals(savedOrder.getQuantity(),order.getQuantity());
        Assertions.assertEquals(savedOrder.getTotalPrice(),order.getTotalPrice());
        Assertions.assertEquals(savedOrder.getDateReceived(),order.getDateReceived());
        Assertions.assertEquals(savedOrder.getStatus(),order.getStatus());
        Assertions.assertEquals(savedOrder.getCustomer(),order.getCustomer());
        Assertions.assertEquals(savedOrder.getProduct(),order.getProduct());
        verify(orderRepository,times(1)).save(order);
    }

    @Test
    public void findByIdTest(){
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        Order foundOrder=orderRepository.findById(order.getId()).orElse(null);
        Assertions.assertNotNull(foundOrder);
        Assertions.assertEquals(foundOrder.getId(),order.getId());
        Assertions.assertEquals(foundOrder.getQuantity(),order.getQuantity());
        Assertions.assertEquals(foundOrder.getTotalPrice(),order.getTotalPrice());
        Assertions.assertEquals(foundOrder.getDateReceived(),order.getDateReceived());
        Assertions.assertEquals(foundOrder.getStatus(),order.getStatus());
        Assertions.assertEquals(foundOrder.getCustomer(),order.getCustomer());
        Assertions.assertEquals(foundOrder.getProduct(),order.getProduct());
        verify(orderRepository,times(1)).findById(order.getId());
    }

    @Test
    public void deleteByIdTest(){
        orderRepository.deleteById(order.getId());
        verify(orderRepository,times(1)).deleteById(order.getId());
    }

    @Test
    public void findAllTest(){
        List<Order> orders=new ArrayList<>();
        orders.add(new Order(1,product.getPrice(),LocalDate.now(),"new",customer,product));
        orders.add(new Order(2,product.getPrice()+1,LocalDate.now(),"inProgress",customer,product));
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> foundOrders=orderRepository.findAll();
        Assertions.assertEquals(foundOrders.size(),2);
        verify(orderRepository,times(1)).findAll();
    }

}