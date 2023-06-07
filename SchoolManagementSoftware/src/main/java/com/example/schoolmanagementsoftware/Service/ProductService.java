package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }



    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }
public void getAllProduct(Integer id,Product product){

}
    public void addProduct(Product product) {
    }

    public void updateProduct(int id, Product product) {
    }

    public void deleteProduct(int id) {
    }

    public void getProduct(int id) {

    }
}