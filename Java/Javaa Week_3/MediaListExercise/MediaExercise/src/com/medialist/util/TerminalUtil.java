package com.medialist.util;
import java.util.List;
import java.util.Scanner;
import com.medialist.model.Media;


public class TerminalUtil {
    private Scanner scanner; //

    public TerminalUtil() { //
        this.scanner = new Scanner(System.in); // Initializing the scanner inside the constructor
    }

    public void displayMenu(){
        System.out.println("=== Media List Application ===");
        System.out.println("=== ====================== ===");
        System.out.println("1. Add Media");
        System.out.println("2. Remove Media");
        System.out.println("3. Play Media");
        System.out.println("4. List All Media");
        System.out.println("5. Quit");
        System.out.print("Chose an option: ");

    }
    //Job is to display the message to the user
    public void displayMessage(String message){
        System.out.println(message);
    }
    //Prompts the user with a given message and reads their String input from the console.
    public String getString(String prompt) {
        System.out.println(prompt);//Displays the prompt passed into the method
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
                System.out.println(ex.getMessage());
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

    public void displayMediaList(List<Media> mediaList) {
        if (mediaList == null || mediaList.isEmpty()) {
            displayMessage("There is nothing to show.");
            return;
        }

        displayMessage("\n--- All media files in Library---");
        for (int i = 0; i < mediaList.size(); i++) {
            Media media = mediaList.get(i);
            displayMessage((i + 1) + ". " + media.getName()); // Shows number and name
            displayMessage("   Description " + media.getDescription()); // Shows full description
        }
        displayMessage("----------------------------------------");
    }




}
