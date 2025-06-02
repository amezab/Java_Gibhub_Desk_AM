//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class for user input

public class Main {
    // Create enums for order status and shipping status.
    enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }

    enum ShippingStatus {
        STANDARD,
        _2_DAY,
        OVERNIGHT
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // Business Name and Introduction
        String bizName = "========The Shopping Cart App=======";
        String contactInfo = "========Contact us";
        String itemDescription = "A command-line shopping cart application built with Java";
        System.out.println(bizName + "\n" + contactInfo + "\n" + itemDescription + "\n");


        // int productID = 0;
        // int productCategory = 0;
        double productCost = 0.00;
        double productPrice = 4.99;
        int productQuantity = 78;

        // double taxRate = .07;
        // double hundredDollarDiscount = .05;
        // double fiveHundredDollarDiscount = .10;

        // double standardShipping = 2.00;
        // double overnightShipping = 10.00;
        // double twoDayShipping = 5.00;

        boolean isConfirmed = false; // Flag to control the order confirmation loop


        // double totalCost = productPrice * productQuantity;
        // System.out.println("Product Price: " + productPrice);
        // System.out.println("Product Quantity: " + productQuantity);
        // System.out.println("Total Cost: " + totalCost);

        System.out.println("Welcome to the shopping cart app!");

        // Variables to store user input, declared once
        String taxExempt = "";
        String shipping = "";
        String promoCode = "";
        int orderQuantity = 0;

        //Array Strings address and sizes
        String[] addresses = {"123 Main St", "456 Main St ", "789 Main St"};
        String[] sizes = {"small", "medium", "large"};
        int addressIndex = 0;
        int sizeIndex = 0;






        // Main loop for order input and confirmation
        while (!isConfirmed) {
            System.out.println("Are you tax-exempt? (y/n)");
            taxExempt = console.nextLine(); // Read tax-exempt status


           for (int i = 0; i <addresses.length; i++) {
                System.out.println((i+1) + ". " + addresses[i]);
            };
            System.out.println("Shipping address?");
            String address = console.nextLine();
            addressIndex = Integer.parseInt(address);

            //System.out.println("(standard/overnight/two-day)");
            // Prompt for Size
            for (int i = 0; i < sizes.length; i++) {
                System.out.println((i + 1) + ". " + sizes[i]);
            }
            System.out.println("Size?");
            String size = console.nextLine();
            sizeIndex = Integer.parseInt(size);


            shipping = console.nextLine(); // Read shipping type

            System.out.println("Order quantity?");
            orderQuantity = Integer.parseInt(console.nextLine()); // Read order quantity

            System.out.println("Promo code for free shipping?");
            promoCode = console.nextLine(); // Read promo code

            System.out.println("Confirm Order y/n");
            isConfirmed = "y".equalsIgnoreCase(console.nextLine());
        }

        // Print details after confirmation
        System.out.println("\nDetails:");
        System.out.println("Address: " + addresses[addressIndex -1]);
        System.out.println("Size: " + sizes[sizeIndex - 1]);
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Order quantity: " + orderQuantity); // Added order quantity to details
        System.out.println("Promo code: " + promoCode);


        /*
        // Apply tax, if necessary
        if (taxExempt.equals("n")) {
            totalCost = totalCost + (totalCost * taxRate);
        }
        System.out.println("Total w/ tax: " + totalCost);

        // Apply discount
        if (totalCost >= 500) {
            totalCost = totalCost - (totalCost * fiveHundredDollarDiscount);
        } else if (totalCost >= 100) {
            totalCost = totalCost - (totalCost * hundredDollarDiscount);
        }
        System.out.println("Total after discount: " + totalCost);

        double shippingCost = 0.00;
        switch (shipping) {
            case "standard":
                shippingCost = standardShipping;
                if (promoCode.equals("FREE")) {
                    shippingCost = 0;
                }
                break;
            case "overnight":
                shippingCost = overnightShipping;
                break;
            case "two-day":
                shippingCost = twoDayShipping;
                break;
            default:
                System.out.println("Invalid shipping type");
        }
        totalCost += shippingCost;
        System.out.println("Shipping Cost: " + shippingCost);
        System.out.println("Final Total: " + totalCost);
        */

        System.out.println("Bye"); //


/*
        double totalCostProduct = productQuantity * productCost;
        System.out.println("Total Cost Product: $" + totalCostProduct);
        double profitPerProduct = productPrice - productCost;
        System.out.println("Profit in dollars: " + profitPerProduct);
        double potentialProfit = ((productQuantity * productPrice) - (productQuantity * productCost));
        System.out.println("Total Potential Profit: $" + String.format("%.2f", potentialProfit));

        // Use enums to print out their values.
        ShippingStatus[] shippingMethod = ShippingStatus.values();
        System.out.println("Shipping Method: " + shippingMethod[0]);
        System.out.println("Shipping Method: " + shippingMethod[1]);
        System.out.println("Shipping Method: " + shippingMethod[2]);

        OrderStatus orderStatus1 = OrderStatus.PENDING;
        System.out.println(orderStatus1);
        OrderStatus orderStatus2 = OrderStatus.PROCESSING;
        System.out.println(orderStatus2);
        OrderStatus orderStatus3 = OrderStatus.SHIPPED;
        System.out.println(orderStatus3);
        OrderStatus orderStatus4 = OrderStatus.DELIVERED;
        System.out.println("Order Status: " + orderStatus4);
*/

        console.close();
    }
}