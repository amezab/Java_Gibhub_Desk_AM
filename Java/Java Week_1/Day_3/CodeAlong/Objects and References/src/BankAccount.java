//1. Create a class BankAccount.java with:
public class BankAccount {
    String owner;
    double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;

    }
//A method deposit(double amount)
   public void deposit(double amount) {
          balance += amount;

    }

}

