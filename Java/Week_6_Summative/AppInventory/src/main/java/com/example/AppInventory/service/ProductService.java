package com.example.AppInventory.service;

import com.example.AppInventory.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public void addStock(Product product, int amount) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        int newQuantity = product.getQuantity() + amount;
        product.setQuantity(newQuantity);
        System.out.println("Added " + amount + " units to " + product.getProductName() +
                ". New quantity: " + newQuantity);
    }

    public void removeStock(Product product, int amount) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (amount > product.getQuantity()) {
            throw new IllegalArgumentException("Cannot remove " + amount +
                    " units. Only " + product.getQuantity() + " units available");
        }

        int newQuantity = product.getQuantity() - amount;
        product.setQuantity(newQuantity);
        System.out.println("Removed " + amount + " units from " + product.getProductName() +
                ". New quantity: " + newQuantity);
    }

    public void updatePrice(Product product, double newPrice) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        double oldPrice = product.getPrice();
        product.setPrice(newPrice);
        System.out.println("Updated price for " + product.getProductName() +
                " from $" + String.format("%.2f", oldPrice) +
                " to $" + String.format("%.2f", newPrice));
    }

    public void displayProductInfo(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        System.out.println("=== Product Information ===");
        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Current Quantity: " + product.getQuantity());
        System.out.println("Price: $" + String.format("%.2f", product.getPrice()));
        System.out.println("Minimum Stock: " + product.getMinimumStock());
        System.out.println("Low Stock Alert: " + (product.isLowStock() ? "YES" : "NO"));
        System.out.println("==========================");
    }

    public boolean needsRestocking(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return product.isLowStock();
    }

    public double calculateTotalValue(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return product.getQuantity() * product.getPrice();
    }
}