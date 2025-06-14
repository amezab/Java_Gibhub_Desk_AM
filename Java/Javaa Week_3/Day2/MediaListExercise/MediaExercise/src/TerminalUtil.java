import java.util.Scanner;

public class TerminalUtil {

    private Scanner scanner = new Scanner(System.in);

    public TerminalUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public int displayMenu(){
        System.out.println("=== Media List Application ===");
        System.out.println("=== ====================== ===");
        System.out.println("1. Add Media");
        System.out.println("2. Remove Media");
        System.out.println("3. Play Media");
        System.out.println("4. List All Media");
        System.out.println("5. Quit");
        System.out.println("Chose an option: ");


        return 0;
    }
    //Job is to display the message to the user
    public void displayMessage(String message){
        System.out.println(message);
    }
    //Prompts the user with a given message and reads their String input from the console.
    public String getString(String prompt) {
        System.out.println("Placeholder " + prompt);//Displays the prompt passed into the method
        return this.scanner.nextLine();// Reads and returns the user's input

    }

    public int getInt(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                int value = this.scanner.nextInt();
                this.scanner.nextLine();
                return value;
            }catch (Exception ex){
                System.out.println("That's not a valid entry");
                System.out.println("Placeholder " + ex.getMessage());
                this.scanner.nextLine();
            }

        }

    }

    public int getMenuChoice(){
        int userChoice;

        do {
            userChoice = getInt("Choose an option between 1-5: ");//ASKS the user for input, gets a number (valid or invalid)
            if (userChoice > 5 || userChoice < 0 ){
                displayMessage("Invalid option. Please choose a number between 1 and 5.");
            }
        } while (userChoice > 5 || userChoice < 0 );
        return userChoice;
    }



}
