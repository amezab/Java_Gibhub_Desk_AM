//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
         //*Requirements
        //        .
        //1. Assign variables to each of the elements above
        int productID = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        System.out.println("Welcome to the shopping cart App");

        //2. Write code to calculate the total cost of the product based on the inventory. Print
        double totalCostProduct = productQuantity * productCost;
        System.out.println("Total Cost Product: $" + totalCostProduct);

        double profitPerProduct = productPrice - productCost;
        System.out.println("Profit in dollars: " + profitPerProduct);

        //3. Write code to calculate the profit margin of the product. Print the value.
        double profitMargin = ((productPrice - productCost) / productPrice) * 100;
        System.out.println("Profit Margin: $" + String.format("%.2f", profitMargin));

        //4. Write code to calculate the total potential profit. Print the value.

        double potentialProfit = ((productQuantity * productPrice) - (productQuantity * productCost));
        System.out.println("Total Potential Profit: $" + String.format("%.2f", potentialProfit));


        }
    }
