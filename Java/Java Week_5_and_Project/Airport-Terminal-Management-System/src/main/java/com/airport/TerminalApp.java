package com.airport;

import com.airport.data.CsvUtil;
import com.airport.data.FlightRepository;
import com.airport.domain.model.Flight;
import com.airport.domain.model.Passenger;
import com.airport.domain.reservation.ReservationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class TerminalApp implements CommandLineRunner {

	private final FlightRepository flightRepository;
	private final ReservationSystem reservationSystem;
	private final CsvUtil csvUtil;
	private Scanner scanner;

	public TerminalApp(FlightRepository flightRepository, ReservationSystem reservationSystem, CsvUtil csvUtil) {
        this.flightRepository = flightRepository;
        this.reservationSystem = reservationSystem;
        this.csvUtil = csvUtil;
    }

    public static void main(String[] args) {
		SpringApplication.run(TerminalApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		//Loads data from the start
		Map<String, List<Passenger>> loadedReservations = csvUtil.loadReservationsFromCSV();
		reservationSystem.setAllReservations(loadedReservations);

		System.out.println("\n--- Available Flights ---");
		for (Flight flight : flightRepository.findAllFlights()) {
			System.out.printf("Flight %s | Departs: %s | Price: $%.2f | Aircraft: %s (%s)%n",
					flight.getFlightNumber(),
					flight.getDepartureDate(),
					flight.getTicketPrice(),
					flight.getAircraft().getModel(),
					flight.getAircraft().getClass().getSimpleName());
		}


		System.out.println("\n--- Book a Flight ---");
		System.out.print("Enter Flight Number: ");
		String flightNumber = scanner.nextLine();
		System.out.print("Enter Passenger Name: ");
		String passengerName = scanner.nextLine();
		System.out.print("Enter Passport Number: ");
		String passportNumber = scanner.nextLine();

		Passenger passenger = new Passenger(passengerName, passportNumber);

		try {
			//Add the reservation to the live system
			reservationSystem.addReservation(flightNumber, passenger);
			//Get the full flight object to save it
			Flight flight = flightRepository.findFlightByNumber(flightNumber);
			//Save the new reservation to the CSV file
			csvUtil.saveReservation(flight, passenger);
			System.out.println("Reservation successful!");

		} catch (IllegalArgumentException e) {
			// Print the error message from the exception (e.g., e.getMessage())
			System.out.println("Error: " + e.getMessage());
		}

	}

}
