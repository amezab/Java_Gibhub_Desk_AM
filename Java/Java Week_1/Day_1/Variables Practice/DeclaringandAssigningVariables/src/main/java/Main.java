public class Main {
    public static void main(String[] args) {

        //Sports Statistics
        String playerName = "Lionel Messi";
        int jerseyNumber = 10;
        String position = "Forward";
        boolean isStarter = true;
        String teamName = "FC Barcelona";
        System.out.printf("Player: %s | Jersey #: %d | Position: %s | Team: %s | Starter: %b\n", playerName, jerseyNumber, position,teamName, isStarter);

        //Movie Information
        String movieTitle = "Inception";
        int releaseYear = 2012;
        double rating = 9.5;
        boolean isSequel = false;
        String leadActor = "Leonardo Di Caprio";
        System.out.printf("Movie: %s | Year %d | Rating %.1f/10 | Sequel %b | Actor %s\n",movieTitle,releaseYear,rating,isSequel,leadActor);

        //Weather Report
        String cityName = "Tegus";
        int temperatureCelcius = 31;
        boolean isRaining = false;
        String humidityPercentage = "93%";
        String weatherCondition = "Cloudy";
        System.out.printf("City: %s | ℃ %d | isRaining %b | Humidity %s | Weather %s ♀4\n", cityName, temperatureCelcius,  isRaining, humidityPercentage, weatherCondition );

        //Flight Information
        String flightNumber = "AA1245";
        String departureCity = "NY";
        String arrivalCity = "LAX";
        int gateNumber = 1234;
        int terminal = 1;
        boolean isDelayed = false;
        System.out.printf("Flight #: %s | Departure City: %s | Arrival City: %s | Gate #: %d | Terminal #: %d | Delay Status %b",flightNumber,departureCity,arrivalCity,gateNumber,terminal,isDelayed);





    }
}
