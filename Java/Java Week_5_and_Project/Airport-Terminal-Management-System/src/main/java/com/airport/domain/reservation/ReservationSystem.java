package com.airport.domain.reservation;

import com.airport.data.FlightRepository;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationSystem {
    //A Map where the key is the flight number (String)
    //Value is the list of passengers on that flight (List<Passenger>)
    private final Map<String, List<Passenger>> reservations = new HashMap<>();
    private final FlightRepository flightRepository;


 //@Autowired annotation on the constructor tells Spring to find the managed FlightRepository bean and pass it in automatically.
@Autowired
    public ReservationSystem(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // This method should add a passenger to the list for a given flight number.
    public void addReservation(String flightNumber, Passenger passenger){

        Flight flight = flightRepository.findFlightByNumber(flightNumber);
        if (flight == null) {
            throw new IllegalArgumentException("Flight " + flightNumber + " not found");
        }

        // clean way to get the list for a flight, or create a new empty list if one doesn't exist
        List<Passenger>passengers = reservations.computeIfAbsent(flightNumber, k -> new ArrayList<>());
        passengers.add(passenger);
    }

    //Retrieve all passengers booked on a specific flight.
    //https://www.w3schools.com/java/ref_hashmap_getordefault.asp
    public List<Passenger> getPassengersForFlight(String flightNumber){
        return reservations.getOrDefault(flightNumber, new ArrayList<>());
    }

    public void setAllReservations(Map<String, List<Passenger>> loadedReservations) {
        // Clear any existing reservations and add all the loaded ones.
        this.reservations.clear();
        this.reservations.putAll(loadedReservations);
    }



}
