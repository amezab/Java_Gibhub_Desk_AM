//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
     enum SeatSelection {
        GENERAL,
        PREMIUM,
        VIP;
    }

    enum CoffeeSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    enum  TrafficLight {
         RED,
        YELLOW,
        GREEN
    }

    enum Weekday {
         MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    enum AlertLevel {
         LOW,
        MODERATE,
        HIGH,
        SEVERE
    }
    public static void main(String[] args) {
        //Coffee Exercise

        CoffeeSize coffeeOrder = CoffeeSize.MEDIUM;
        System.out.println(coffeeOrder);//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        //Seat Reservations in a Stadium

        SeatSelection general = SeatSelection.GENERAL;
        SeatSelection premium = SeatSelection.PREMIUM;
        SeatSelection vip = SeatSelection.VIP;
        System.out.println(general + " is assigned value: " + general.ordinal());
        System.out.println(premium + " is assigned value: " + premium.ordinal());
        System.out.println(vip + " is assigned value: " + vip.ordinal());


        //Convert Integer to Enum
        TrafficLight[] signals = TrafficLight.values();
        System.out.println("Traffic light signal: " + signals[1]);


        //Assign Work Days
        Weekday workday = Weekday.WEDNESDAY;
        System.out.println("Workday selected: " + workday);
        boolean weekend = (workday == Weekday.SATURDAY || workday == Weekday.SUNDAY);
        System.out.println("Is it a weekend?: " + weekend);

        //Government Alert Levels
        AlertLevel currentAlert = AlertLevel.HIGH;
        switch (currentAlert) {
            case LOW:
                System.out.println("No immediate danger.");
                break;
            case MODERATE:
                System.out.println("Stay alert and aware.");
                break;
            case HIGH:
                System.out.println("Take precautions and stay informed.");
                break;
            case SEVERE:
                System.out.println("Immediate action required!");
                break;


        }
    }
}
