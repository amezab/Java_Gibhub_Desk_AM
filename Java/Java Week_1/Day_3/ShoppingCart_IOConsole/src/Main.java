//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the
import java.util.Scanner;
public class Main {
    //Create enums for order status and shipping status.
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
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner console = new Scanner(System.in);

        //Biz Name
        String bizName = "========The Shopping Cart App=======";
        String contactInfo = "========Contact us";
        String itemDescription = "========A command-line shopping cart application built with Java";
        System.out.println(bizName + "\n"+ contactInfo + "\n" + itemDescription + "\n");



        int productID = 0;
        int productCategory = 0;
        double productCost = 0.00;
        double productPrice = 4.99;
        int productQuantity = 78;

        double taxRate = .07;
        double hundredDollarDiscount = .05;
        double fiveHundredDollarDiscount = .10;
        double standardShipping = 2.00;
        double overnightShipping = 10.00;
        double twoDayShipping = 5.00;


        System.out.println("Welcome to the shopping cart App");

        //Shopping Cart Console IO
        System.out.println("Are you tax-exempt? (y/n)");
        String taxExempt = console.nextLine();
        System.out.println("Shipping? (standard / overnight/ two-day)");
        String shipping = console.nextLine();
        System.out.println("Order quantity?");
        int quantity = console.nextInt();
        System.out.println("Promo code for free shipping?");
        String promoCode = console.nextLine();

        System.out.println("Details: ");
        System.out.println("Tax-Exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Order quantity : " + quantity);
        System.out.println("Promo Code: " + promoCode);

        double totalCostProduct = productQuantity * productCost;
        System.out.println("Total Cost Product: $" + totalCostProduct);
        double profitPerProduct = productPrice - productCost;
        System.out.println("Profit in dollars: " + profitPerProduct);
        //3. Write code to calculate the profit margin of the product. Print the value.
        /*double profitMargin = ((productPrice - productCost) / productPrice) * 100;
        System.out.println("Profit Margin: $" + String.format("%.2f", profitMargin));
        //4. Write code to calculate the total potential profit. Print the value.*/
        double potentialProfit = ((productQuantity * productPrice) - (productQuantity * productCost));
        System.out.println("Total Potential Profit: $" + String.format("%.2f", potentialProfit));

//Use enums to print out their values.
        ShippingStatus[] shippingMethod = ShippingStatus.values();
        System.out.println("Shipping Method: " + shippingMethod[0]);
        System.out.println("Shipping Method: " + shippingMethod[1]);
        System.out.println("Shipping Method: " + shippingMethod[2]);

        OrderStatus orderStatus1 = OrderStatus.PENDING;
        System.out.println( orderStatus1);
        OrderStatus orderStatus2 = OrderStatus.PROCESSING;
        System.out.println( orderStatus2);
        OrderStatus orderStatus3 = OrderStatus.SHIPPED;
        System.out.println( orderStatus3);
        OrderStatus orderStatus4 = OrderStatus.DELIVERED;
        System.out.println("Order Status: " + orderStatus4);



    }
}
