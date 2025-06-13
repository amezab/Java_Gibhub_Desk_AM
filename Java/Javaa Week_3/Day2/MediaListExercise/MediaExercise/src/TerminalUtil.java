import java.util.Scanner;

public class TerminalUtil {

    private Scanner scanner = new Scanner(System.in);

    public TerminalUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu(){
        System.out.println("=== Media List Application ===");
        System.out.println("=== ====================== ===");
        System.out.println("1. Add Media");
        System.out.println("2. Remove Media");
        System.out.println("3. Play Media");
        System.out.println("4. List All Media");
        System.out.println("5. Quit");
        System.out.println("Chose an option: ");


    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public String getString(String prompt) {
        System.out.println(prompt);//Displays the prompt passed into the method
        return this.scanner.nextLine();// Reads and returns the user's input

    }

    public int getInt(String prompt) {
        return;
    }



}
