//1. Create a class named file Car.java with:

public class Car {
    //Data Members
    String brand;
    String model;
    int year;

    //Creates the car object, often times we would pass in started data
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;

    }

    //A method displayInfo() that prints the car’s details.

    public void displayInfo (){
        System.out.println("Brand: " + brand + "Model: " + model + "Year: " + year);
    }
}