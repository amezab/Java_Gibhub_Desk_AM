import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LockerManager manager = new LockerManager();


        System.out.println("Welcome to the Locker Management System!");

        while (true) { // The infinite loop for the menu
            System.out.println("\n--- Locker System Menu ---");
            System.out.println("1. Add a new locker");
            System.out.println("2. Remove a locker");
            System.out.println("3. Store an item in a locker");
            System.out.println("4. Retrieve an item from a locker");
            System.out.println("5. Display all lockers");
            System.out.println("6. Exit program");
            System.out.print("Enter your choice (1-6): ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter Locker ID: ");
                    String addId = scanner.nextLine();//get the choice from user
                    manager.addLocker(addId);//Using the add locker method
                    break;
                case "2":
                    System.out.println("Enter locker Id to remove: ");
                    String removeId = scanner.nextLine();
                    manager.removeLocker(removeId);
                    break;
                case "3":
                    System.out.println("Enter locker ID to store Item: ");
                    String storeItem = scanner.nextLine();
                    Locker lockertoStore = (Locker) manager.getLocker(storeItem);
                    if (lockertoStore != null) { //Check if the locker was actually found
                        System.out.println("Enter Item to store: ");
                        String itemStore = scanner.nextLine();
                        lockertoStore.storeItem(itemStore);//Call storeItem on the found locker.
                    } else {
                        System.out.println("Error: Locker " + storeItem + " not found.");
                    }
                    break;

                case "4": // Retrieve an item from a locker
                    System.out.print("Enter Locker ID to retrieve item from: ");
                    String retrieveLockerId = scanner.nextLine();
                    // First, try to find the locker.
                    Locker lockerToRetrieve = (Locker) manager.getLocker(retrieveLockerId);
                    if (lockerToRetrieve != null) { // Check if the locker was found.
                        lockerToRetrieve.removeItem(); // Call removeItem on the found locker.
                    } else {
                        System.out.println("Error: Locker '" + retrieveLockerId + "' not found.");
                    }
                    break;

                case "5":
                    manager.displayAllLockers();
                    break;
                case "6":
                    System.out.println("Exiting Locker Management System. Goodbye!");
                    scanner.close(); //  Dont forget to close the scanner
                    return;

                default: // Handle any invalid input.
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break; // Exit the switch, loop will repeat and show menu again.

            }
        }
    }
}
