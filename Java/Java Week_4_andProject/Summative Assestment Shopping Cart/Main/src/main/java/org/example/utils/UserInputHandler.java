package org.example.utils;
import java.util.Scanner;

public class UserInputHandler {

    private final Scanner scanner;

    public UserInputHandler(Scanner scanner){

        this.scanner = scanner;
    }

    public int getIntegerInput(){
        while (true){
            // System.out.println("Enter something");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            }catch (Exception e){
                System.out.println("That's not a number. Please try again.");
                // to clear the bad input.
                scanner.nextLine();
            }
        }
    }

    public String getStringInput(){
        return scanner.nextLine();
    }

}
