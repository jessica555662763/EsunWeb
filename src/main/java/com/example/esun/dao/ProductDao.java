package com.example.esun.dao;

import com.example.esun.dto.ProductQueryParams;
import com.example.esun.dto.ProductRequest;
import com.example.esun.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductById(Integer productId);

    List<Product> getProducts();

    void updateQuantity(Integer productId, Integer stock);
}