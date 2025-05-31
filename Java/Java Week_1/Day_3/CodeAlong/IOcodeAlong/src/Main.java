import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


    Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the order form!");

    System.out.print("What is your name?: ");
    String name = console.nextLine();
    System.out.printf("Hello, %s  Lets get Started with your order.\n", name);

        System.out.println("Please enter product name: ");
        String prodName = console.nextLine();


        System.out.println("Please enter product quantity: ");
        String prodQuantity = console.nextLine();
        int quantity = Integer.parseInt(prodQuantity);

        System.out.println("Whats the unit price: ");
        String unitPriceStr = console.nextLine();
        double unitPrice = Integer.parseInt(unitPriceStr);

        double subTotal = quantity * unitPrice;
        double tax = subTotal * 0.07;
        double grandTotal = subTotal + tax;

        System.out.println("Order Summary");
        System.out.println("----------------------");
        System.out.println("Item:                 " + prodName);
        System.out.println("Quantity:             " + quantity);
        System.out.println("Unit Price:           " + unitPrice);
        System.out.println("----------------------");
        System.out.println("Subtotal:             " + subTotal);
        System.out.println("Tax:                  " + tax);
        System.out.println("Grand Total:          " + grandTotal);
        System.out.println("Thank You for your order, " + name);








    }
}