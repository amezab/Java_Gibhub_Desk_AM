package com.airport.data;

import com.airport.domain.model.*;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvUtil {

    private final java.io.File csvFile;

    public CsvUtil() {
        String path = "data/reservations.csv";
        this.csvFile = new java.io.File(path);
    }

    public void saveReservation(Flight flight, Passenger passenger) {
        // The try-with-resources block handles the FileWriter
        try (FileWriter writer = new FileWriter(csvFile, true)) {
            // --- Determine Aircraft Type ---
            String aircraftType;
            if (flight.getAircraft() instanceof CommercialAircraft) {
                aircraftType = "Commercial";
            } else if (flight.getAircraft() instanceof PrivateJet) {
                aircraftType = "PrivateJet";
            } else {
                aircraftType = "Unknown";
            }

            // --- Format the CSV Line ---
            String line = String.format("%s,%s,%s,%s,%s,%s,%s\n",
                    flight.getFlightNumber(),
                    flight.getDepartureDate().toString(),
                    flight.getTicketPrice().toString(),
                    passenger.getName(),
                    passenger.getPassportNumber(),
                    flight.getAircraft().getModel(),
                    aircraftType);

            writer.write(line);

        } catch (java.io.IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public Map<String, List<Passenger>> loadReservationsFromCSV() {
        Map<String, List<Passenger>> results = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                // Skip the header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(",");
                // Check if we have enough data elements (should be 7)
                if (data.length < 7) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                // 1. Create the Passenger object
                Passenger passenger = new Passenger(data[3], data[4]);

                // 2. Create the correct Aircraft subclass
                String aircraftType = data[6];
                String aircraftModel = data[5];
                Aircraft aircraft = null;

                if (aircraftType.equals("Commercial")) {
                    aircraft = new CommercialAircraft(data[5], 0, 0.0, "");

                } else if (aircraftType.equals("PrivateJet")) {
                    aircraft = new PrivateJet(data[5], 0, 0.0, false, 0);
                }

                // 3. Safety check and create the Flight object
                // This block only runs if the aircraft was successfully created
                if (aircraft != null) {
                    String flightNumber = data[0];
                    LocalDate departureDate = LocalDate.parse(data[1]);
                    BigDecimal ticketPrice = new BigDecimal(data[2]);

                    Flight flight = new Flight(flightNumber, departureDate, ticketPrice, aircraft);

                    // 4. Add the passenger to the results map for the correct flight
                    results.computeIfAbsent(flight.getFlightNumber(), k -> new ArrayList<>()).add(passenger);
                }
            }
        } catch (java.io.IOException e) {
            System.err.println("Error reading from CSV file: " + e.getMessage());
        }

        // 5. Return the fully populated map
        //reversing the save process turn text into Java objects.
        return results;
    }
}

