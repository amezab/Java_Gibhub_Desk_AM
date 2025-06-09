import java.text.DecimalFormat; //formating numbers with leading 0s
import java.util.Random; //random number generator
//Managing the entire collection of Locker objects.
//Brain manager to create, finding lockers/generating pin numbers
public class LockerService {
    private Locker [] lockers;//array to hold locker list

    private static final Random RANDOM = new Random();//using this to create the random numbers
    private static final DecimalFormat PIN_FORMATTER = new DecimalFormat("0000");
//    public boolean hasRentedLockers() {//Wanted to handle case 3 when there is no locker rented
//        for (int i = 0; i < lockers.length; i++) {
//            Locker currentLocker = lockers[i];
//
//            if (currentLocker.isRented()) {
//
//                return true;
//            }
//
//        }return false;
//    }

    private IO io;


    private String generatePin(){
        int randomNumber = RANDOM.nextInt(10000);
        String pin = PIN_FORMATTER.format(randomNumber);
        return pin;
    }

    public boolean rentLocker(){
        for (int i = 0; i < lockers.length; i++){
            if( !lockers[i].isRented()){//Checks if locker # IS AVAILABLE
            //Calling generate pin once and store it
            String newPinNumber = generatePin();
            lockers[i].setPinNumber(newPinNumber);//creates the number for the current locker
            lockers[i].setRented(true); // marks locker as rented
                this.io.printMessage("The locker # " + lockers[i].getLockerNumber() + " has been rented successfully");
                this.io.printMessage("Pin number is: " + newPinNumber);

            return true;//confirming locker has been rented
        }

        }//no locker available
        this.io.printMessage("No Lockers Available right now");
        return false;
    }

    public LockerService(int numberOfLockers, IO io){
        this.io = io;
        lockers  = new Locker[numberOfLockers];
        int currentLockerNumber = 1;

        for (int i = 0; i < lockers.length; i++) {
             lockers[i] = new Locker(currentLockerNumber);
              currentLockerNumber += 1;
        }
    }

    public Locker[] getLockers(){
        return lockers;
    }


}
