package com.example.schoolmanagementsoftware.Repository;

import com.example.schoolmanagementsoftware.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

    public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    Customer findCustomerByUsername(String username);

}
