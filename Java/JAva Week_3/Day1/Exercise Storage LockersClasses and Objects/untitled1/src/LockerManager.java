import java.util.ArrayList;
import java.util.List;
import java.util.Objects; // For safe String comparison

class LockerManager {
    private List<Locker> lockers;

    public LockerManager() {
        this.lockers = new ArrayList<>();
    }

    public void addLocker(String lockerId) {
        // Optional: Check for duplicate lockerId before adding
        if (getLocker(lockerId) != null) {
            System.out.println("Error: Locker with ID '" + lockerId + "' already exists. Cannot add duplicate.");
            return;
        }
        Locker newLocker = new Locker(lockerId);
        this.lockers.add(newLocker);
        System.out.println("Success: Locker '" + lockerId + "' has been added.");
    }

    public Locker getLocker(String lockerId) { //
           for (Locker currentLocker : this.lockers) { // Using enhanced for-loop IJ recomended
            if (Objects.equals(lockerId, currentLocker.getLockerId())) {
                System.out.println("Locker with ID '" + lockerId + "' found!");
                return currentLocker; // 3. Return the Locker object directly
            }
        }
        return null; // Return null if not found
    }

    public void removeLocker(String lockerId) {
        for (int i = 0; i < this.lockers.size(); i++) {
            Locker currentLocker = this.lockers.get(i);
            if (Objects.equals(lockerId, currentLocker.getLockerId())) {
                this.lockers.remove(i);
                System.out.println("Success: Locker '" + lockerId + "' has been removed.");
                return;
            }
        }
        System.out.println("Error: Locker '" + lockerId + "' not found for removal.");
    }

    public void displayAllLockers() {
        if (this.lockers.isEmpty()) {
            System.out.println("There are no lockers to display.");
            return;
        }
        System.out.println("\n--- Current Locker List (" + this.lockers.size() + " lockers) ---");
        for (Locker locker : this.lockers) {
            System.out.println(locker);
        }
        System.out.println("-------------------------------------\n");
    }
}