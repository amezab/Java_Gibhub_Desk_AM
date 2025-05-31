import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Scanner console = new Scanner(System.in);

        System.out.println("Welcome to the line item app!");


        System.out.println("What is your name? ");
        String name = console.nextLine();

        System.out.println("What product would you like to purchase? ");
        String product = console.nextLine();


        System.out.println("How many would you like? ");
        String quantityStr = console.nextLine();
        int quantity = Integer.parseInt(quantityStr);

        System.out.println("What is the product cost? ");
        String costStr = console.nextLine();
        double cost = Double.parseDouble(costStr);

        System.out.printf("Line Item Summary for: %s\n",name);
        System.out.println("Item: Qty\tUnit Price\tLine Total (incl. tax)");
        System.out.println("====================\t==========\t==========");
        System.out.printf("%s\t%s\t$%s\t\t$%.2f", product,
                quantity, cost, (quantity * cost * 1.75));








    }
}