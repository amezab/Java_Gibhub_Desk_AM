package com.library.ui;

import java.time.Year;
import java.util.Scanner;

public class UserIO {
    private final Scanner scanner = new Scanner(System.in);

    // Use this method when the input CANNOT be empty.
    public String readRequiredString(String prompt) {
        while (true) {
            System.out.println(prompt);
            String userInput = this.scanner.nextLine();
            if (!userInput.trim().isEmpty()) {
                return userInput;
            }
            System.out.println("[Err] Input cannot be blank.");
        }
    }

    // Use this method when the input CAN be empty (for the update feature).
       public String readString(String prompt) {
        System.out.print(prompt);
        return this.scanner.nextLine();
    }

    // Use this method to read any integer.
    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = this.scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("[Err] Please enter a valid number.");
            }
        }
    }

    // Use this method to read an integer within a specific range.
    public int readInt(String prompt, int min, int max) {
        while (true) {
            int result = readInt(prompt);
            if (result >= min && result <= max) {
                return result;
            }
            System.out.println("[Err] Please enter a number between " + min + " and " + max + ".");
        }
    }

    public Year readYear(String prompt) {
        while (true) {
            try {
                // We use readRequiredString to make sure the input isn't blank
                String input = this.readRequiredString(prompt);
                return Year.parse(input);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("[Err] Please enter a valid 4-digit year.");
            }
        }
    }
}