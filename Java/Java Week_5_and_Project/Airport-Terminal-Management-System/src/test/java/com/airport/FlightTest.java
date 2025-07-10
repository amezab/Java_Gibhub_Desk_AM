import com.airport.domain.model.Aircraft;
import com.airport.domain.model.CommercialAircraft;
import com.airport.domain.model.Flight;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    // Happy Path: Test successful creation of a Flight object
    @Test
    void testFlightCreation_HappyPath() {
        Aircraft aircraft = new CommercialAircraft("Boeing 747", 416, 624.0, "Dual Aisle");
        LocalDate departureDate = LocalDate.of(2024, 12, 25);
        BigDecimal ticketPrice = new BigDecimal("1250.75");

        Flight flight = new Flight("FL789", departureDate, ticketPrice, aircraft);

        assertNotNull(flight);
        assertEquals("FL789", flight.getFlightNumber());
        assertEquals(departureDate, flight.getDepartureDate());
        assertEquals(ticketPrice, flight.getTicketPrice());
        assertEquals(aircraft, flight.getAircraft());
    }

    // Sad Path: Test creating a flight with a null aircraft
    @Test
    void testFlightCreation_SadPath_NullAircraft() {
        LocalDate departureDate = LocalDate.now();
        BigDecimal ticketPrice = new BigDecimal("200.00");

        Flight flight = new Flight("FL101", departureDate, ticketPrice, null);

        assertNotNull(flight);
        assertEquals("FL101", flight.getFlightNumber());
        assertNull(flight.getAircraft());
    }
}