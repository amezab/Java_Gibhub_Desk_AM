package com.library.ui;

import java.util.Scanner;

public class UserIO {
    private final Scanner scanner = new Scanner(System.in);

    public UserIO() {
    }

    public String readString(String prompt) {
        System.out.println(prompt);
        String userInput = scanner.nextLine();
        return userInput;
    }

    // Base method to read any integer
    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("[Err] Please enter a valid number.");
            }
        }
    }

    // Overloaded method to read integer within a range
    public int readInt(String prompt, int min, int max) {
        while (true) {
            int result = readInt(prompt); // Call the base readInt() method (no infinite recursion)
            if (result >= min && result <= max) {
                return result;
            } else {
                readString(String.format("[Err] Please enter a number between %s and %s. Press [Enter] to continue.", min, max));
            }
        }
    }
}