 import java.util.Scanner;

 //Boss
public class Main {
    private IO io;//Initialization
    public static final int max_Lockers = 3;//Holds the total capacity of the locker system.
    private Locker[] lockerList;//Hold  an array of all the individual Lockers.
    private LockerService lockerService;//locker management logic
    private LockerAccessMG lockerAccessMG;

    //private String generatePinNumber;

    public Main() {
        this.io = new IO();
        this.lockerService = new LockerService(max_Lockers,  this.io);
        this.lockerList = this.lockerService.getLockers();
        this.lockerAccessMG = new LockerAccessMG(this.lockerList, this.io);



    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();

    }

    public void run() { //The user has three options: rent a locker, open a renter locker, or release (un-rent) a locker.
        while (true) {
            this.io.printMessage("What would you like to do next? ");
            this.io.printMessage("1. Rent a Locker");
            this.io.printMessage("2. Access a Locker");
            this.io.printMessage("3. Release a Locker");
            this.io.printMessage("=====================");
            this.io.printMessage("Any other key to exit");
            String choice = this.io.readLine();

            if (!choice.equals("1") && (!choice.equals("2")) && (!choice.equals("3"))) {
                this.io.printMessage("Exiting Locker System. Goodbye!");
                break;
            }


            switch (choice) {

                case "1":
                    lockerService.rentLocker();
                    break;
                case "2":
                    int[] credentials = this.io.promptForLockerandPin();
                    //Extract the locker number from the array
                    int lockerNumberToAccess = credentials[0];
                    //Extract the pin as int and convert to str
                    String pinToAccess = String.valueOf(credentials[1]);
                    //accessing  the locker to the lockeraccesMG
                    lockerAccessMG.accessLocker(lockerNumberToAccess, pinToAccess);
                    break;

                case "3":
                    int[] pinCredentials = this.io.promptForLockerandPin();//getting credentials
                    int lockerNumberToRelease = pinCredentials[0];//gettin locker number
                    String pinToRelease = String.valueOf(pinCredentials[1]);//gettin and converting PIN
                    lockerAccessMG.releaseLocker(lockerNumberToRelease, pinToRelease); //call the release method
                    //this.io.printMessage("Are you sure?\n" + "Enter Locker and PIN number to release the locker");
                    break;
                default:
                    this.io.printMessage("Invalid option. Please choose 1, 2, or 3.");
                    break;

            }
            this.io.printMessage("\n-------------------------------------\n");



        }io.closeScanner();

    }
}



