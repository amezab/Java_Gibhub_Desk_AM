public class Locker {
    private String lockerId;
    private boolean isOccupied;
    private String contents;

    public Locker(String lockerId) {
        this.lockerId = lockerId;
        this.isOccupied = false;// By default, a new locker is not occupied
        this.contents = ""; //By default, a new locker has no contents
    }

    public String getLockerId() {
        return lockerId;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void storeItem(String item) {
        if (this.isOccupied) {
            System.out.println("Locker " + this.lockerId + " is already occupied. Cannot store: " + item);
        } else {
            this.contents = item;      // Set the contents to the new item
            this.isOccupied = true;    // Mark the locker as occupied
            System.out.println("Locker " + this.lockerId + " now contains: " + this.contents);
        }
    }

    public void removeItem() {
        this.contents = "";      // Clear the contents by setting to an empty string
        this.isOccupied = false; // Mark the locker as unoccupied
        System.out.println("Locker " + this.lockerId + " has been emptied.");
    }
    //I could not figure out how to do this one
    @Override
    public String toString() {
        // Use a ternary operator to make the status more readable
        String status = this.isOccupied ? "Occupied" : "Empty";

        return "Locker ID: " + this.lockerId +
                ", Status: " + status +
                ", Contents: '" + this.contents + "'";
    }





}
