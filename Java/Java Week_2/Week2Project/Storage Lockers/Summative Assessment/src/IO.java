//Receptionist
import java.util.Scanner;

public class IO {

    private Scanner scanner;
    //to initialize the scanner field
    public IO() {
        this.scanner = new Scanner(System.in);
    }
    //Method to interact with the user for pin and locker
    public int [] promptForLockerandPin(){
        System.out.print("Enter Locker number");
        int lockerNum = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the 4-Digit Pin number");
        String pinNum = scanner.nextLine();
        int pinInt = Integer.parseInt(pinNum);
        //Returns both values for locker and pin number:
        return new int[]{lockerNum, pinInt};

    }

    public String readLine(){
        return this.scanner.nextLine();
        //taking choice info
    }
    //Closing the scanner
    public void closeScanner() {

    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
