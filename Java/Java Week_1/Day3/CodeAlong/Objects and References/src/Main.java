//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //*In the main method**, create **two Car objects**

        Car Car1 = new Car(
                "Toyota ",
                "Corolla ",
                2020);
        Car Car2 = new Car(
                "Ford ",
                "Mustang ",
                2022);

        //Call displayInfo()` for each.
        System.out.println("---My Car----");
        Car1.displayInfo();
        System.out.println(("---Your Car---"));
        Car2.displayInfo();

        //Scenario: Book Library System
        // create **two book objects**
        Book Book1 = new Book(
                " The Hobbit ",
                "by J.R.R.",
                "Tolkien"
        );
        Book Book2 = new Book(
                " The Hobbit ",
                "by J.R.R.",
                "Tolkien"
        );

        //Call displayInfo()` for each
        System.out.println("Book: ");
        Book1.borrowBook();

        //Shared Account Reference

        BankAccount acc1 = new BankAccount("a", 1000.00);
        BankAccount acc2 = acc1; //

        System.out.println("Initial Balance (acc1): $" + String.format("%.1f", acc1.balance));

        System.out.println("Depositing $500 to acc2...");

        acc2.balance = 1500.00; //

        System.out.println("Updated Balance (acc1): $" + String.format("%.1f", acc1.balance)); // Using %.1f to match previous output

        Employee emp1 = new Employee("Alice", 101);
        Employee emp2 = new Employee("Bob", 102);
        Employee emp3 = new Employee("Charlie", 103);

        System.out.println("Final total employees: " + Employee.totalEmployees);
    }
}

