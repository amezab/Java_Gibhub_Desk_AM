package com.airport;

import com.airport.domain.model.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    // Happy Path: Test successful creation of a Passenger object
    @Test
    void testPassengerCreation_HappyPath() {
        Passenger passenger = new Passenger("John Doe", "AB123456");
        assertNotNull(passenger);
        assertEquals("John Doe", passenger.getName());
        assertEquals("AB123456", passenger.getPassportNumber());
    }

    // Happy Path: Test the equals and hashCode methods
    @Test
    void testEqualsAndHashCode_HappyPath() {
        // Create TWO passengers with IDENTICAL data
        Passenger passenger1 = new Passenger("Jane Doe", "CD654321");
        Passenger passenger2 = new Passenger("Jane Doe", "CD654321");  // Same person!

        // Create a DIFFERENT passenger
        Passenger passenger3 = new Passenger("John Smith", "EF987654");

        // Test equals() - same data should be equal
        assertEquals(passenger1, passenger2);
        assertNotEquals(passenger1, passenger3);


        assertEquals(passenger1.hashCode(), passenger2.hashCode());
        assertNotEquals(passenger1.hashCode(), passenger3.hashCode());
    }

    // Sad Path: Test creating a passenger with null values
    @Test
    void testPassengerCreation_SadPath_NullValues() {
        Passenger passenger = new Passenger(null, null);
        assertNull(passenger.getName());
        assertNull(passenger.getPassportNumber());
    }

    // Sad Path: Test creating a passenger with empty strings
    @Test
    void testPassengerCreation_SadPath_EmptyValues() {
        Passenger passenger = new Passenger("", "");
        assertEquals("", passenger.getName());
        assertEquals("", passenger.getPassportNumber());
    }
}