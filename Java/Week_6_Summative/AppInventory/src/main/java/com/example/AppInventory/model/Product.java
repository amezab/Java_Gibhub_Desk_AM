package com.example.AppInventory.model;

import java.util.Objects;

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

    public boolean isLowStock() {
        return quantity <= minimumStock;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, quantity, price, minimumStock);
    }

    @Override
    public String toString() {
        return String.format("Product{ID='%s', Name='%s', Quantity=%d, Price=$%.2f, MinStock=%d}",
                productId, productName, quantity, price, minimumStock);
    }
}