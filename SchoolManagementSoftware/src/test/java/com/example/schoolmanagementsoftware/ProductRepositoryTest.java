package com.example.schoolmanagementsoftware;

import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith( MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @Mock
    EntityManager entityManager;

    @Test
    public void findByNameTest(){
        Product product=new Product(null,"product1",10);
        when(entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :name",Product.class))
                .thenReturn(Mock( TypedQuery.class));
        when(productRepository.findByName(product.getName())).thenReturn(product);
        Product foundProduct=productRepository.findByName(product.getName());
        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals(foundProduct.getId(),product.getId());
        Assertions.assertEquals(foundProduct.getName(),product.getName());
        Assertions.assertEquals(foundProduct.getPrice(),product.getPrice());
        verify(entityManager,times(1)).createQuery("SELECT p FROM Product p WHERE p.name = :name",Product.class);
        verify(productRepository,times(1)).findByName(product.getName());
    }

}