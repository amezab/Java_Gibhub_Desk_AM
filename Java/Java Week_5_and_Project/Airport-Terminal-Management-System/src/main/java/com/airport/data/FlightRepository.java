package com.airport.data;


import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.Flight;
import com.airport.domain.model.PrivateJet;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FlightRepository {
    //map to store sample flights using the flight number as the key
    private final Map<String, Flight> flights = new HashMap<>();

    public FlightRepository() {
        // Create a few sample aircraft
        Aircraft aircraft1 = new CommercialAircraft("Boeing 737", 180, 26000, "American Airlines");
        Aircraft aircraft2 = new PrivateJet("Gulfstream G650", 18, 45000, true, 956);

        Flight flight1 = new Flight("AA101", LocalDate.of(2024, 8, 15), new BigDecimal("350.00"), aircraft1);
        Flight flight2 = new Flight("PJ007", LocalDate.of(2024, 9, 5), new BigDecimal("9500.00"), aircraft2);

        flights.put(flight1.getFlightNumber(), flight1);
        flights.put(flight2.getFlightNumber(), flight2);

    }

    // A method to retrieve a flight by its number
    public Flight findFlightByNumber(String flightNumber){
        return flights.get(flightNumber);
    }

    //method to get all available Flights
    public Collection<Flight> findAllFlights(){
        return flights.values();
    }
}
