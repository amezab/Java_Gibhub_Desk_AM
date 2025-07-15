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

        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        if (daysBeforeExpiration < 0) {
            throw new IllegalArgumentException("Days before expiration cannot be negative");
        }

        this.expirationDate = expirationDate;
        this.daysBeforeExpiration = daysBeforeExpiration;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        this.expirationDate = expirationDate;
    }

    public int getDaysBeforeExpiration() {
        return daysBeforeExpiration;
    }

    public void setDaysBeforeExpiration(int daysBeforeExpiration) {
        if (daysBeforeExpiration < 0) {
            throw new IllegalArgumentException("Days before expiration cannot be negative");
        }
        this.daysBeforeExpiration = daysBeforeExpiration;
    }

    public long getDaysUntilExpiration() {
        return ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    public boolean isNearExpiration() {
        long daysUntil = getDaysUntilExpiration();
        return daysUntil <= daysBeforeExpiration && daysUntil >= 0;
    }

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

    @Override
    public String toString() {
        return String.format("PerishableProduct{ID='%s', Name='%s', Quantity=%d, Price=$%.2f, " +
                        "MinStock=%d, ExpirationDate=%s, Status=%s}",
                getProductId(), getProductName(), getQuantity(), getPrice(),
                getMinimumStock(), expirationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                getExpirationStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerishableProduct that = (PerishableProduct) o;
        return daysBeforeExpiration == that.daysBeforeExpiration &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expirationDate, daysBeforeExpiration);
    }
}