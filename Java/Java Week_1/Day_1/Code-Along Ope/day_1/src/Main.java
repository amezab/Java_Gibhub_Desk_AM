public class Main{
    public static void main(String[] ars){
        double  loanAmount = 25000.00; // in dollars
        double annualInterestRate = 5.0; // Represents 5.5% as a decimal
        int loanTermYears = 5;
        double monthlyPayment;

        monthlyPayment = (loanAmount * ( annualInterestRate/100)/12);
//1. Use Assignment Operators:
        double increase = loanAmount += 5_000;
        double reduce = annualInterestRate -= 1.0;
        loanTermYears++;

//2   Use Comparison Operators:

        if (loanAmount >= 30_000.00) {
            System.out.println("loanAmount exceeds $30,000.");
        }

        if (monthlyPayment > 500.00) {
            System.out.println("monthlyPayment is more than $500.");
        }

        if (monthlyPayment < 500.00 && loanTermYears > 3){
            System.out.println("Its Affordable");
        }
        if (monthlyPayment > 700.00 || annualInterestRate > 6 ){
            System.out.println("Expensive");
        }







    }
}

