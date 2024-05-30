package com.example.esun.controller;

import com.example.esun.model.Product;
import com.example.esun.service.impl.ProductServiceImp;
import com.example.esun.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductServiceImp productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {

        List<Product> productList = productService.getProducts();
        return ResponseEntity.ok(productService.getAllProducts());
        
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid CreateProductRequest productRequest) {

        productService.addProduct(productRequest);
        List<Product> productList = productService.getProducts();
        return ResponseEntity.ok(productService.addProduct(productList));

    }
}
