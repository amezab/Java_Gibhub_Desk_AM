import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //this is where I can access the location class
    public static Location mountPaozu;
    public static Location lookout;
    public static Location cellGamesArena;

    //counter for the keys collected
    public static int keyPartsCollected = 0;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // Create Location Objects
        mountPaozu = new Location("Mount Paozu", "The familiar scent of mountain air and wildflowers fills your senses.",
                "You've returned to Mount Paozu."
        );

        lookout = new Location("The Lookout", "A serene and expansive platform stretches out, stark white against the endless blue sky.",
                "The serene expanse of The Lookout greets you once more.");

        cellGamesArena = new Location("The Cell Games Arena aka Ruins", "\"The cracked, desolate wasteland stretches out before you," +
                "dominated by the shattered remnants of what was once a grand, circular fighting stage.",
                "You stand once again amidst the scarred remains of the Cell Games Arena");


        //Start in a central hub with options for your three locations.
        //Main Game Loop
        System.out.println("==========Gohan's Legendary Locales: A DBZ Adventure=======");
        System.out.println("***********************************************************");
        System.out.println("You are Gohan, seeking to grow stronger by revisiting pivotal places from your past.");

        //Display a menu of the three locations.
        while (true) {
            System.out.println("\n--- Your Current Location: The Hub ---");
            System.out.println("Where do you wish to go? ");
            System.out.println("1. Mount Paozu (Goku's House & Training Grounds):");
            System.out.println("2. The Lookout (Kami's/Dende's Temple):");

            System.out.println("3. The Cell Games Arena (Ruins) - [LOCKED]");
            System.out.println("Type Q to exit the hub");

            System.out.print("Your Choice ");
            String choice = scanner.nextLine();

            if (choice.equals("Q")) {
                System.out.println("Until next time...");
                break;
            }
            //holding the location chosen by the user
            Location chosenLocation = null;

            switch (choice) {
                case "1":
                    chosenLocation = mountPaozu;
                    break;
                case "2":
                    chosenLocation = lookout;
                    break;
                case "3":
                    if (keyPartsCollected == 2) {
                        chosenLocation = cellGamesArena;
                        System.out.println("The entrance to The Cell Games Arena opens.");
                    } else {
                        System.out.println("The entrance to the Cell Games Arena are sealed");
                        System.out.println("You need the two keys to enter. You have " + keyPartsCollected + " perhaps the other" +
                                "location holds a clue");
                        continue;
                    }
                    break;
                default:
                    System.out.println("Invalid Choice, please enter 1, 2, 3 or Q ");

            }

            if (chosenLocation != null) {
                System.out.println("\n--- Entering " + chosenLocation.name + " ---");
                System.out.println(chosenLocation.getDescription());
            }
            if (!chosenLocation.hasBeenVisited) { // If it has NOT been visited before
                chosenLocation.setHasBeenVisited(true);

                if (chosenLocation == mountPaozu || chosenLocation == lookout) {
                    keyPartsCollected++; // Adds 1 to the counter
                    System.out.println("\nYou found a glowing fragment! You feel it's a part of an ancient key. Parts collected: " + keyPartsCollected + "/2");
                }
                if (mountPaozu.hasBeenVisited && lookout.hasBeenVisited && cellGamesArena.hasBeenVisited) {
                    System.out.println("\n---------------------------------------------------------");
                    System.out.println("Having revisited the pillars of his past, Gohan feels a new strength flow through him, not just in power, but in understanding." +
                            " The lessons of Paozu, the serenity of the Temple, and the raw truth of the Cell Arena merge into a silent wisdom." +
                            " He is ready. The future may bring any threat, but now, Gohan is more prepared than ever to face it, on his own terms.");
                    System.out.println("---------------------------------------------------------");
                    break; // Exit the 'while' loop to end the game.
                }
            } // <-- The 'while' loop ends here.





        }
    }
}