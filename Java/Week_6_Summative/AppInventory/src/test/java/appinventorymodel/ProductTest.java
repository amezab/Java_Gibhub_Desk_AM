package com.example.AppInventory.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    @Test
    public void testSomething() {
        // Test 1: "Can I create a product and get its values back?"
        Product jersey = new Product("P001","Barcelona Jersey",10,130.25,5);

        assertEquals("P001",jersey.getProductId());
        assertEquals("Barcelona Jersey", jersey.getProductName());
        assertEquals(10,jersey.getQuantity());
        assertEquals(130.25, jersey.getPrice());
        assertEquals(5,jersey.getMinimumStock());


        Product lowStockJersey = new Product("P002", "Real Madrid Jersey", 3, 79.99, 5);
        assertTrue(lowStockJersey.isLowStock());


        Product goodStockJersey = new Product("P003", "Liverpool Jersey", 10, 69.99, 5);
        assertFalse(goodStockJersey.isLowStock());

    }
}