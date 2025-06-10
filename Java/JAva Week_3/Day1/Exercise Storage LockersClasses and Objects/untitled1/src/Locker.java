public class Locker {
    private String lockerId;
    private boolean isOccupied;
    public String contents;

    public Locker(String lockerId, boolean isOccupied, String contents) {
        this.lockerId = lockerId;
        this.isOccupied = false;
        this.contents = "";
    }


    public void storeItem(String item) {
        //Check if the locker is already occupied
        if(this.isOccupied){
            System.out.println("Locker " + this.lockerId + " is already occupied. Cannot store: " + item);
        }else {
            //If not occupied, update contents and status
            this.contents = item;
            this.isOccupied = true;
            System.out.println("Locker " + this.lockerId + " now contains: " + this.contents);
        }
    }

    public void removeItem (){
        this.contents = ""; //clears the content

    }


}
