import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        printWelcomeMessage();//a function was created as an aditional class in main
       // System.out.println("==== Welcome to the Theater ====");
        //System.out.println("Please enter the ticket info below\n");


        // Prompt user for movie title
        String movie = getString("What movie would you like to see?", console);
        //System.out.print("What movie would you like to see? ");
        //String movie = console.nextLine();

        // Prompt user for movie time (1pm, 2:30pm, etc.)
        // Notes: Keeping it simple to avoid time validation challenges
        // Current version does not validate for expected values
        //System.out.println("There are 3 matinees available: 1pm, 2:30pm, 4pm\\nWhat\n" +//      "time?");
        //String movieTime = console.nextLine();
        String movieTime = getString("There are 3 matinees available: 1pm, 2:30pm, 4pm\nWhat time?", console, "1pm", "2:30pm", "4pm");


        // Prompt user for # of adult tickets
        //System.out.println("# of Adult Tickets? ");
        //This is how we take the input and convert it into a int
        /*String adultTixString = console.nextLine();
        int adultTix = Integer.parseInt(adultTixString);

        // Prompt user for # of child tickets
        System.out.println("# of Child Tickets");
        String childTixString = console.nextLine();
        int childTix = Integer.parseInt(childTixString);*/
        int adultTix = getInt("# of Adult Tieckets?", console);
        int childTix =getInt("# of Child Tickets?", console);

// Calculate cost: Matinee Adult: $11.75, Child: $8.25
        //Calculate cost: Matinee Adult: $11.75, Child: $8.25
        double totalCost = adultTix * 11.75 + childTix * 8.25;
        printPurchaseSummary(movie, movieTime, adultTix, childTix, totalCost);

        System.out.println("\nThanks! Enjoy the show!");

        /*System.out.println("\nPurchase Complete! Summary: ");
        System.out.println("Movie:\t\t"+movie);
        System.out.println("Time:\t\t"+movieTime);
        System.out.println("Adult Tix:\t"+adultTix);
        System.out.println("Child Tix:\t"+childTix);
        System.out.println("Total Cost:\t$"+totalCost);
        System.out.println("\nThanks! Enjoy the show!");*/


        }

    public static void printWelcomeMessage(){
        System.out.println("==== Welcome to the Theater ====");
        System.out.println("Please enter the ticket info below.\n");

    }
    public static String getString(String prompt, Scanner console){
        System.out.println(prompt);
        return console.nextLine();
    }
    public static String getString(String prompt, Scanner console, String val1, String val2, String val3) {
        boolean isValid = false;
        String input = "";
        while (!isValid) {
            input = getString(prompt, console);
            if (input.equalsIgnoreCase(val1) ||
                    input.equalsIgnoreCase(val2) ||
                    input.equalsIgnoreCase(val3)) {
                isValid = true;
            }
            else {
                System.out.println("Invalid entry. Please try again.");
            }
        }
        return input;
    }
    public static int getInt(String prompt, Scanner console) {
        System.out.println(prompt);
        String input = console.nextLine();
        return Integer.parseInt(input);
    }

    public static void printPurchaseSummary(
            String movie, String movieTime, int adultTix, int childTix, double totalCost
    ) {
        System.out.println("\nPurchase Complete! Summary: ");
        System.out.println("Movie:\t\t"+ movie);
        System.out.println("Time:\t\t"+ movieTime);
        System.out.println("Adult Tix:\t"+ adultTix);
        System.out.println("Child Tix:\t"+ childTix);
        System.out.println("Total Cost:\t$"+ totalCost);
    }


}


