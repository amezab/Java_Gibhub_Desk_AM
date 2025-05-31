//1. Create a class named file Car.java with:
//Creating a Class and Instantiating Objects
public class Car {
    //Data Members
    String brand;
    String model;
    int year;
//Creating a Class and Instantiating Objects
    //Creates the car object, often times we would pass in started data
    //A constructor to initialize all variables
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;

    }

    //A method displayInfo() that prints the car’s details in main.
    public void displayInfo (int itemNumber){
        System.out.println("Car: " + itemNumber + " " + brand + " " + model + " (" + year + ")");
    }
}