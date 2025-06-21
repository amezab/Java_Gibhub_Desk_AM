package org.example.utils;
import java.util.Scanner;

public class UserInputHandler {

    private final Scanner scanner;

    public UserInputHandler(){
        this.scanner = new Scanner(System.in);
    }

    public int getIntegerInput(){
        while (true){
            System.out.println("Enter something");
            try {
                int choice;
                choice = scanner.nextInt();
                return choice;
            }catch (Exception e){
                System.out.println("That's not a number. Please try again.");
                //to clear the bad input.
                scanner.nextLine();
            }
        }

    }


}
