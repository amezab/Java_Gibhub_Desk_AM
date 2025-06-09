import java.util.Objects;
//Blueprint
public class Locker {
    private final int lockerNumber;
    private String pinNumber;//The PIN should be a 'string' (not an 'int').
    private boolean isRented;


    //constructor for my Locker class.
    public Locker(int lockerNumber) {
        this.lockerNumber = lockerNumber;
        this.pinNumber = null;
        this.isRented = false;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    //Working around the info for the pin number
    public String getPinNumber(){
       return pinNumber;
    }
    public void setPinNumber(String pinNumber){
        this.pinNumber = pinNumber;
    }

    public boolean isRented(){
        return isRented;
    }

    public void setRented(boolean rented) {
       this.isRented = rented;
    }

    public void reset(){
        this.pinNumber = null;
        this.isRented = false;
    }
}














