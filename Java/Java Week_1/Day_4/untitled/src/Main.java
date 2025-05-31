import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Welcome to the Decisions App!");
        //The line of Java code Scanner console = new Scanner(System.in); is fundamental for allowing your Java program
        // to read input from the user via the console
        Scanner console = new Scanner(System.in);

// Snooze the alarm when you are too tired (if-else)
        System.out.println("Wake up");
            boolean tooTired = true;
            if(tooTired){
            System.out.println("Too tired... to snooze");
         } else {
           System.out.println("Lets get up and get at it!");
         }




// Decide to stop at a stop light (else-if)
        System.out.println("Approaching stop light!");
        System.out.print("Stoplight color?? ");
        String color = console.nextLine();

        if ("red".equalsIgnoreCase(color)){
            System.out.println("Stop!!!");

        }else if("yellow".equalsIgnoreCase(color)){
            System.out.println("Approaching stop light changing color!");
            System.out.println("How far from the light? ");
            String input = console.nextLine();

            int distance = Integer.parseInt(input);
            if (distance >= 20){
                System.out.println("Slow down and stop");

            }else {
                System.out.println("Slow down then stop");
            }

        } else if ("green".equalsIgnoreCase(color)) {
            System.out.println("Go!!!");
        }else {
            System.out.println("invalid Color ");
        }

// Decide which direction your adventure will go in? (switch)
        System.out.println("Which way do you want to go?");
        System.out.println("N - North");
        System.out.println("S - South");
        System.out.println("E - East");
        System.out.println("W - West");
        System.out.println("=======");
        System.out.println("Which direction? ");
        String direction = console.nextLine();
        //This one comes from the imput
        switch (direction) {
            case "N": {
                System.out.println("Go North");
                break;
            }
            case "S": {
                System.out.println("Go South");
                break;
            }
            case "E": {
                System.out.println("So East");
                break;
            }
            case "W": {
                System.out.println("Go West");
                break;
            }
            default:
            {
                System.out.println("Invalid Input");
                break;
            }


        }
        }
    }
