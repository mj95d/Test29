package com.example.schoolmanagementsoftware.Repository;

import com.example.schoolmanagementsoftware.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

}