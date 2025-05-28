//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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


        int productID = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        System.out.println("Welcome to the shopping cart App");

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
        OrderStatus orderStatus1 = OrderStatus.PENDING;
        System.out.println("Order Status: " + orderStatus1);
        OrderStatus orderStatus2 = OrderStatus.PROCESSING;
        System.out.println("Order Status: " + orderStatus2);
        OrderStatus orderStatus3 = OrderStatus.SHIPPED;
        System.out.println("Order Status: " + orderStatus3);
        OrderStatus orderStatus4 = OrderStatus.DELIVERED;
        System.out.println("Order Status: " + orderStatus4);

        ShippingStatus[] shippingMethod = ShippingStatus.values();
        System.out.println("Shipping Method: " + shippingMethod[0]);
        System.out.println("Shipping Method: " + shippingMethod[1]);
        System.out.println("Shipping Method: " + shippingMethod[2]);

    }
}