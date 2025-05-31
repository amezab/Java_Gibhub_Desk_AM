//
public class BankAccount {
    // Declaring the attributes (variables) for the bank account
    String owner;   // To store the name or identifier of the account owner (e.g., "acc1")
    double balance; // To store the current monetary balance of the account

    // A constructor to initialize these variables when a new BankAccount object is created
    public BankAccount(String owner, double balance) {
        this.owner = owner;     // 'this.owner' refers to the 'owner' attribute of the current object
        this.balance = balance; // 'this.balance' refers to the 'balance' attribute of the current object
    }

    // A method deposit(double amount)
    public void deposit(double amount) {
        this.balance += amount; // Increase the balance by the deposited amount
        System.out.println("Updated Balance (" + this.owner + "): $" + this.balance);
    }

    // --- Essential Getter Methods ---
    // These methods are needed to read the 'owner' and 'balance' from outside the class,
    // which is necessary for the main method to print the initial state.
    // Getter for the owner
    public String getOwner() {
        return owner;
    }
    // Getter for the balance
    public double getBalance() {
        return balance;
    }

}