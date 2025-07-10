package com.airport;

import com.airport.data.FlightRepository;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;
import com.airport.domain.reservation.ReservationSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationSystemTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private ReservationSystem reservationSystem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Happy Path: Test adding a reservation for an existing flight
    @Test
    void testAddReservation_HappyPath() {
        Flight flight = new Flight("FL456", LocalDate.now(), new BigDecimal("300.00"), null);
        Passenger passenger = new Passenger("Test Passenger", "TP12345");

        when(flightRepository.findFlightByNumber("FL456")).thenReturn(flight);

        reservationSystem.addReservation("FL456", passenger);
        List<Passenger> passengers = reservationSystem.getPassengersForFlight("FL456");

        assertEquals(1, passengers.size());
        assertEquals(passenger, passengers.get(0));
    }

    // Sad Path: Test adding a reservation for a non-existent flight
    @Test
    void testAddReservation_SadPath_FlightNotFound() {
        Passenger passenger = new Passenger("Test Passenger", "TP12345");
        when(flightRepository.findFlightByNumber("FL999")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationSystem.addReservation("FL999", passenger);
        });

        assertEquals("Flight FL999 not found", exception.getMessage());
    }

    // Happy Path: Test getting passengers for a flight with reservations
    @Test
    void testGetPassengersForFlight_HappyPath() {
        Flight flight = new Flight("FL789", LocalDate.now(), new BigDecimal("400.00"), null);
        Passenger passenger1 = new Passenger("Alice", "A123");
        Passenger passenger2 = new Passenger("Bob", "B456");

        when(flightRepository.findFlightByNumber("FL789")).thenReturn(flight);

        reservationSystem.addReservation("FL789", passenger1);
        reservationSystem.addReservation("FL789", passenger2);

        List<Passenger> passengers = reservationSystem.getPassengersForFlight("FL789");
        assertEquals(2, passengers.size());
        assertTrue(passengers.contains(passenger1));
        assertTrue(passengers.contains(passenger2));
    }

    // Happy Path: Test getting passengers for a flight with no reservations
    @Test
    void testGetPassengersForFlight_SadPath_NoReservations() {
        List<Passenger> passengers = reservationSystem.getPassengersForFlight("FL000");
        assertTrue(passengers.isEmpty());
    }
}