package com.example.AppInventory.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class PerishableProduct extends Product {
    private LocalDate expirationDate;
    private int daysBeforeExpiration;

    public PerishableProduct(String productId, String productName, int quantity,
                             double price, int minimumStock, LocalDate expirationDate,
                             int daysBeforeExpiration) {
        super(productId, productName, quantity, price, minimumStock);

        this.expirationDate = expirationDate;
        this.daysBeforeExpiration = daysBeforeExpiration;
    }

    //gets expiration date
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    //updates the exp date for batch updated
    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        this.expirationDate = expirationDate;
    }

    //Warning
    public int getDaysBeforeExpiration() {
        return daysBeforeExpiration;
    }

    //updates the warning threshold
    public void setDaysBeforeExpiration(int daysBeforeExpiration) {
        if (daysBeforeExpiration < 0) {
            throw new IllegalArgumentException("Days before expiration cannot be negative");
        }
        this.daysBeforeExpiration = daysBeforeExpiration;
    }
    //calculates days till experitation
    public long getDaysUntilExpiration() {
        //it helps calculate the days between two dates, in this case till now
        return ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
    }

    //checks if product is expired
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    //Checks if product is near exp, i could put on sale
    public boolean isNearExpiration() {
        long daysUntil = getDaysUntilExpiration();
        return daysUntil <= daysBeforeExpiration && daysUntil >= 0;
    }

    //provides expiration status, readable
        public String getExpirationStatus() {
        if (isExpired()) {
            return "EXPIRED";
        } else if (isNearExpiration()) {
            return "EXPIRING SOON";
        } else {
            return "FRESH";
        }
    }

    // POLYMORPHISM: Override parent's isLowStock method
    @Override
    public boolean isLowStock() {
        return super.isLowStock() || isNearExpiration() || isExpired();
    }

    //string representation including expiration info
    @Override
    public String toString() {
        return String.format("PerishableProduct{ID='%s', Name='%s', Quantity=%d, Unit Price=$%.2f, " +
                        "MinStock=%d, ExpirationDate=%s, Status=%s}",
                getProductId(), getProductName(), getQuantity(), getPrice(),
                getMinimumStock(), expirationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                getExpirationStatus());
    }

}