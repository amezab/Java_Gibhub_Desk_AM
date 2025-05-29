public class Employee {
    String name;
    private int employeeID;
    static  int totalEmployees = 0;



    public Employee(String name, int employeeId) {
        this.name = name;
        this.employeeID = employeeId;
        totalEmployees++; // Increment totalEmployees each time a new Employee is created
        System.out.println("Employee " + this.name + " (ID: " + this.employeeID + ") hired. Total employees: " + totalEmployees);
    }

    }







