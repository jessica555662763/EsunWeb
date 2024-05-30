package com.example.esun.service;

import com.example.esun.model.Order;
import com.example.esun.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product addProduct(Product product);

}
