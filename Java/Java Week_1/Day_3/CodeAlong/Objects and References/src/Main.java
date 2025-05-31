//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        //Creating a Class and Instantiating Objects
        // Part 1: Car Dealership System Objects created from class Car
    Car car1 = new Car ("Toyota", "Corolla", 2020);
    Car car2 = new Car ("Ford", "Mustang", 2022);

    // call displayInfo()` for each Car
    car1.displayInfo(1);
    car2.displayInfo(2);

    //Part 2: Reference Types and Object Behavior
        //Scenario: Book Library System


     Book book1 = new Book("The Hobbit ", "J.R.R. ", " ");
     Book book2 = new Book("The Hobbit ", "J.R.R. ", " ");
     book1.borrowBook();
     book2.borrowBook();

//Part 3: Reference Assignment and Behavior
// Shared Account Reference

     BankAccount acc1 = new BankAccount("acc1", 1000.0);
     System.out.println("Initial Balance (" + acc1.getOwner() + "): $" + acc1.getBalance());

     BankAccount acc2 = acc1;
     System.out.println("Depositing $500 to acc2...");

     acc1.deposit(500.0);

//Part 4: Using Static Members
        //Scenario: Tracking Total Employees in a Company
// create three employees and print totalEmployees.
    Employee employee1 = new Employee("Jose");
    Employee employee2 = new Employee("Pedro");
    Employee employee3 = new Employee("Juan");
        System.out.println("Total Employees: "+ Employee.totalEmployees);











    }
}