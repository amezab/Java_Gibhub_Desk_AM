package com.example.AppInventory.service;

import com.example.AppInventory.model.Product;


public interface ProductService {

    void addStock(Product product, int amount);
    void removeStock(Product product, int amount);
    void updatePrice(Product product, double newPrice);
    void displayProductInfo(Product product);
    boolean needsRestocking(Product product);
    double calculateTotalValue(Product product);
}