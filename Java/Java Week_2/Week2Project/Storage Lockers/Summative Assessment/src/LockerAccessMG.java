//security guard
public class LockerAccessMG {
    private Locker [] lockers;
    private IO io;


    public LockerAccessMG(Locker[] incomingLockers, IO io){
        this.lockers = incomingLockers;
        this.io = io;
    }


    public boolean accessLocker(int lockerNum, String pin){
        // Validation for lockerNumber
        if((lockerNum < 1) || (lockerNum > lockers.length)) {
            this.io.printMessage("Invalid locker number. Please enter a number between 1 and " + lockers.length + ".");
            return false;
        }//retrieving the locker object
        int lockerIndex  = lockerNum - 1;
        Locker targetLocker = lockers[lockerIndex];
        if ((!targetLocker.isRented())){
            this.io.printMessage("Locker is not rented");
            return false;
        }
        if (!pin.equals(targetLocker.getPinNumber())){
            this.io.printMessage("Incorrect Pin Number");
            return false;
        }
        this.io.printMessage("Locker opened successfully");

        return true;

    }

    public boolean releaseLocker(int lockerNum, String pin){
        if((lockerNum < 1) || (lockerNum > lockers.length)) {
            this.io.printMessage("Invalid locker number. Please enter a number between 1 and " + lockers.length + ".");
            return false;

        }//retrieving the locker object
        int lockerIndex  = lockerNum - 1;
        Locker targetLocker = lockers[lockerIndex];
        if ((!targetLocker.isRented())){
            this.io.printMessage("Locker is not rented");
            return false;

        }
        if (!pin.equals(targetLocker.getPinNumber())){
           this.io.printMessage("Incorrect Pin Number");
            return false;
        }
        //runs only if everything else is false
        this.io.printMessage("Are you sure you want to release Locker " + lockerNum +"? (y/n) ");
        String confirmation = this.io.readLine(); {

            if (!confirmation.equalsIgnoreCase("y")) {//learned this in class today!!!
                this.io.printMessage("Locker Release cancelled");
                return false;
            }
        }
        targetLocker.reset();
        this.io.printMessage("Locker " + lockerNum + " has been successfully released.");
        //this.io.printMessage("Locker opened successfully");
        return true;
    }

}
