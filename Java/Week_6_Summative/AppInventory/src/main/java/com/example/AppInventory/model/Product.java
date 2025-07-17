package com.example.AppInventory.model;

import java.util.Objects;
// Core data fields
public class Product {
    private final String productId;
    private final String productName;
    private int quantity;
    private double price;
    private int minimumStock;

    public Product(String productId, String productName, int quantity, double price, int minimumStock) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.minimumStock = minimumStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }

    // Business logic, determines if product needs reordering
    public boolean isLowStock() {
        return quantity <= minimumStock;
    }

    // for comparing based on data and no memory location
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity &&
                Double.compare(price, product.price) == 0 &&
                minimumStock == product.minimumStock &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(productName, product.productName);
    }
    //Generates hash code for efficient storage in Sets/Maps
    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, quantity, price, minimumStock);
    }

    //Allows us to see a summary w/o using each getter individually
    @Override
    public String toString() {
        return String.format("Product: %s (ID: %s)\n" +
                        "  Stock: %d units | Unit Price: $%.2f | Min Stock: %d",
                productName, productId, quantity, price, minimumStock);
    }
}