package com.example.schoolmanagementsoftware;

import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith( MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductRepository productService;
    @Mock
    ProductRepository productRepository;

    @Test
    public void createProductTest() {
        Product product = new Product(null, "product1", 10);
        when(productRepository.save(product)).thenReturn(product);
        Product createdProduct = productService.createProduct(product);
        Assertions.assertNotNull(createdProduct);
        Assertions.assertEquals(createdProduct.getId(), product.getId());
        Assertions.assertEquals(createdProduct.getName(), product.getName());
        Assertions.assertEquals(createdProduct.getPrice(), product.getPrice());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void getProductByNameTest() {
        Product product = new Product(null, "product1", 10);
        when(productRepository.findByName(product.getName())).thenReturn(product);
        Product foundProduct = productService.getProductByName(product.getName());
        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals(foundProduct.getId(), product.getId());
        Assertions.assertEquals(foundProduct.getName(), product.getName());
        Assertions.assertEquals(foundProduct.getPrice(), product.getPrice());
        verify(productRepository, times(1)).findByName(product.getName());
    }

}