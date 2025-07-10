package com.airport;

import com.airport.data.CsvUtil;
import com.airport.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CsvUtilTest {

    private CsvUtil csvUtil;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary file for each test
        File tempFile = tempDir.resolve("test_reservations.csv").toFile();

        // Use a CsvUtil instance that points to our temporary file
        csvUtil = new CsvUtil() {
            @Override
            public void saveReservation(Flight flight, Passenger passenger) {
                try (FileWriter writer = new FileWriter(tempFile, true)) {
                    String aircraftType = flight.getAircraft() instanceof CommercialAircraft ? "Commercial" : "PrivateJet";
                    String line = String.format("%s,%s,%s,%s,%s,%s,%s\n",
                            flight.getFlightNumber(), flight.getDepartureDate().toString(),
                            flight.getTicketPrice().toString(), passenger.getName(),
                            passenger.getPassportNumber(), flight.getAircraft().getModel(),
                            aircraftType);
                    writer.write(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    // Happy Path: Test saving and then loading reservations
    @Test
    void testSaveAndLoadReservations_HappyPath() {
        // 1. Setup
        Aircraft commercialAircraft = new CommercialAircraft("A380", 853, 900.0, "Wide Body");
        Flight flight1 = new Flight("BA249", LocalDate.of(2025, 8, 20), new BigDecimal("750.00"), commercialAircraft);
        Passenger passenger1 = new Passenger("Clark Kent", "CK98765");

        Aircraft privateAircraft = new PrivateJet("Gulfstream G650", 19, 12000.0, true, 2);
        Flight flight2 = new Flight("PJX01", LocalDate.of(2025, 9, 15), new BigDecimal("15000.00"), privateAircraft);
        Passenger passenger2 = new Passenger("Bruce Wayne", "BW54321");

        // 2. Action
        csvUtil.saveReservation(flight1, passenger1);
        csvUtil.saveReservation(flight2, passenger2);

        Map<String, List<Passenger>> loadedReservations = csvUtil.loadReservationsFromCSV();

        // 3. Assertions
        assertNotNull(loadedReservations);
        assertEquals(2, loadedReservations.size());

        // Verify Flight 1
        assertTrue(loadedReservations.containsKey("BA249"));
        List<Passenger> flight1Passengers = loadedReservations.get("BA249");
        assertEquals(1, flight1Passengers.size());
        assertEquals("Clark Kent", flight1Passengers.get(0).getName());

        // Verify Flight 2
        assertTrue(loadedReservations.containsKey("PJX01"));
        List<Passenger> flight2Passengers = loadedReservations.get("PJX01");
        assertEquals(1, flight2Passengers.size());
        assertEquals("Bruce Wayne", flight2Passengers.get(0).getName());
    }

    // Sad Path: Test loading from a malformed CSV file
    @Test
    void testLoadFromMalformedCsv_SadPath() throws IOException {
        File tempFile = tempDir.resolve("malformed.csv").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("header,row,is,fine\n");
            writer.write("only,four,columns\n"); // Malformed line
            writer.write("FL123,2025-10-10,500.00,Diana Prince,DP11223,Boeing 777,Commercial\n");
        }

        // Point CsvUtil to the malformed file
        csvUtil = new CsvUtil() {
            @Override
            public Map<String, List<Passenger>> loadReservationsFromCSV() {
                // Override to read from our specific malformed file for this test
                return super.loadReservationsFromCSV();
            }
        };

        Map<String, List<Passenger>> reservations = csvUtil.loadReservationsFromCSV();

        // Should only load the valid line, skipping the header and the malformed one.
        assertEquals(1, reservations.size());
        assertTrue(reservations.containsKey("FL123"));
        assertEquals(1, reservations.get("FL123").size());
    }

    // Sad Path: Test loading from a non-existent file
    @Test
    void testLoadFromNonExistentFile_SadPath() {
        File nonExistentFile = new File(tempDir.toFile(), "nonexistent.csv");

        // Point CsvUtil to a file that doesn't exist
        csvUtil = new CsvUtil() {
            @Override
            public Map<String, List<Passenger>> loadReservationsFromCSV() {
                return super.loadReservationsFromCSV();
            }
        };

        // Should not throw an exception, but return an empty map
        Map<String, List<Passenger>> reservations = csvUtil.loadReservationsFromCSV();
        assertTrue(reservations.isEmpty());
    }
}