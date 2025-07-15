package com.example.AppInventory.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInput {

    private Scanner scanner = new Scanner(System.in);

    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✗ Please enter a valid whole number.");
            }
        }
    }

    public double getDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✗ Please enter a valid decimal number (e.g., 9.99).");
            }
        }
    }

    public int getIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = getInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("✗ Please enter a number between " + min + " and " + max + ".");
        }
    }

    public boolean getConfirmation(String prompt) {
        while (true) {
            String input = getString(prompt + " (y/n): ").toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("✗ Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    public String getNonEmptyString(String prompt) {
        while (true) {
            String input = getString(prompt);
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("✗ This field cannot be empty. Please try again.");
        }
    }

    public int getPositiveInt(String prompt) {
        while (true) {
            int value = getInt(prompt);
            if (value >= 0) {
                return value;
            }
            System.out.println("✗ Please enter a positive number.");
        }
    }

    public double getPositiveDouble(String prompt) {
        while (true) {
            double value = getDouble(prompt);
            if (value >= 0) {
                return value;
            }
            System.out.println("✗ Please enter a positive number.");
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}