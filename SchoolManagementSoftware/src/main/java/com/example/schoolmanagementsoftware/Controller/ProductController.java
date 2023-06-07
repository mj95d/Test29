package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.Model.Order;
import com.example.schoolmanagementsoftware.Model.Product;
import com.example.schoolmanagementsoftware.Repository.ProductRepository;
import com.example.schoolmanagementsoftware.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

        private final ProductService productService;

        @GetMapping("/getAll")
        public ResponseEntity getAllProduct(){
            return ResponseEntity.status(200).body(this.productService.getAllProduct());
        }

        @PostMapping("/add")
        public ResponseEntity addProduct(@Valid @RequestBody Product product){
            this.productService.addProduct(product);
            return ResponseEntity.status(200).body("product added");
        }

        @PutMapping("/update/{id}")
        public ResponseEntity updateProduct(@PathVariable int id, @Valid @RequestBody Product product){
            this.productService.updateProduct(id, product);
            return ResponseEntity.status(200).body("product updated");
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteProduct(@PathVariable int id){
            this.productService.deleteProduct(id);
            return ResponseEntity.status(200).body("product deleted");
        }

        @GetMapping("/getProduct/{id}")
        public ResponseEntity getProductById(@PathVariable int id){
            return ResponseEntity.status(200).body("this.productService.getProduct(id)");
        }

    }