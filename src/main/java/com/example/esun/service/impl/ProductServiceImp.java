package com.example.esun.service.impl;

import com.example.esun.model.Product;
import com.example.esun.repository.ProductRepository;
import com.example.esun.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productDao.getProducts();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
