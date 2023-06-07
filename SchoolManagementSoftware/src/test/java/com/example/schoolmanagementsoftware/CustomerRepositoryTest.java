package com.example.schoolmanagementsoftware;

import com.example.schoolmanagementsoftware.Model.Customer;
import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.CustomerRepository;
import com.example.schoolmanagementsoftware.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith( SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

    public class CustomerRepositoryTest {

        @InjectMocks
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
        public void saveTest(){
            when(customerRepository.save(customer)).thenReturn(customer);
            Customer savedCustomer=customerRepository.save(customer);
            Assertions.assertNotNull(savedCustomer.getId());
            Assertions.assertEquals(savedCustomer.getPassword(),customer.getPassword());
            Assertions.assertEquals(savedCustomer.getRole(),customer.getRole());
            verify(customerRepository,times(1)).save(customer);
        }

        @Test
        public void findByIdTest(){
            when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
            Customer foundCustomer=customerRepository.findById(customer.getId()).orElse(null);
            Assertions.assertNotNull(foundCustomer);
            Assertions.assertEquals(foundCustomer.getId(),customer.getId());
            Assertions.assertEquals(foundCustomer.getPassword(),customer.getPassword());
            Assertions.assertEquals(foundCustomer.getRole(),customer.getRole());
            verify(customerRepository,times(1)).findById(customer.getId());
        }

        @Test
        public void deleteByIdTest(){
            customerRepository.deleteById(customer.getId());
            verify(customerRepository,times(1)).deleteById(customer.getId());
        }

        @Test
        public void findAllTest(){
            List<Customer> customers=new ArrayList<>();
            customers.add(new Customer(null,false,String.valueOf(product.getPrice()),null));
            customers.add(new Customer(null,true,String.valueOf(product.getPrice()+1),null));
            when(customerRepository.findAll()).thenReturn(customers);

            List<Customer> foundCustomers=customerRepository.findAll();
            Assertions.assertEquals(foundCustomers.size(),2);
            verify(customerRepository,times(1)).findAll();
        }

        @Test
        public void findByProductNameTest(){
            when(productService.getProductByName("product name")).thenReturn(product);
            when(customerRepository.findByProductName(product.getName())).thenReturn(List.of(customer));

            List<Customer> foundCustomers=customerRepository.findByProductName(product.getName());
            Assertions.assertEquals(foundCustomers.size(),1);
            Assertions.assertEquals(foundCustomers.get(0),customer);
            verify(productService,times(1)).getProductByName(product.getName());
            verify(customerRepository,times(1)).findByProductName(product.getName());
        }
    }