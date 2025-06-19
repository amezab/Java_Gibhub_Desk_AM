public class Main {
  public static void main(String[] args) {


        double loanAmount = 25000.00; // in dollars
        double annualInterestRate = 5.5; // as a percentage
        int loanTermYears = 5;
        double monthlyPayment = (loanAmount*(annualInterestRate/100)/12);
        System.out.println(monthlyPayment);


    //1. Use Assignment Operators:
    //○ Increase loanAmount by $5,000 (+=).
    //○ Reduce the annualInterestRate by 1% (-=).
    //○ Increase loanTermYears by 1 (++).

        loanAmount += 5000;
        System.out.println(loanAmount);
        annualInterestRate -= 1;
        loanTermYears++;
        System.out.println(loanTermYears);




        boolean isLoanAmount = loanAmount > 30000;
        System.out.println(isLoanAmount);
        boolean isMonthlyPayment = monthlyPayment > 500;
        System.out.println(isMonthlyPayment);

        //Determine if the loan is affordable (monthly payment is below $500 AND
        //term is over 3 years).
        //○ Determine if the loan is expensive (monthly payment is above $700 OR
        //interest rate is over 6%).

        boolean isLoanAffordable = monthlyPayment < 500;
        System.out.println(isLoanAffordable);
        boolean isLoanExpensive = monthlyPayment > 700;
        System.out.println(isLoanExpensive);


        //Weather - Temperature Conversion & Forecast Analysis

        double temperatureCelsius = 25.0; // Current temperature in Celsius
        boolean isRaining = false; // Indicates if it's raining
        int windSpeedKmh = 50; // Wind speed in km/h


        double temperatureFahrenheit = ((temperatureCelsius)*((double) 9 /5) + 32);
        temperatureFahrenheit += 5;
        windSpeedKmh += 5;

        boolean isTempHigher = temperatureFahrenheit > 85;
        System.out.println(isTempHigher);
        boolean isWindSpeedGreater = windSpeedKmh > 20 ;
        System.out.println(isWindSpeedGreater);

       if (!isRaining && temperatureFahrenheit > 65 && temperatureFahrenheit < 85){
           System.out.println("it's a good day to go outside");
       }

       if (windSpeedKmh > 30 || temperatureCelsius < 0 ){
           System.out.println("weather warning");
       }

       //Gaming - Player Score and Level Up System
        //Use Arithmetic Operators:
        //○ Add +300 XP when completing a quest (+=).
        //○ Reduce xpToNextLevel dynamically as XP increases (-=).
        //○ Multiply XP if player earns a double XP boost (*=).

        int currentXP = 1200; // experience points
        int level = 5;
        int xpToNextLevel = 1500;
        boolean levelUp;

        int questCompleted =+ 300;
        int xDoubleBoost = currentXP *=2;

        boolean levelsUp = (currentXP >= xpToNextLevel) && (level < 10);
        System.out.println(levelsUp);
        boolean isPro = (level > 7) || (currentXP > 5000);
        System.out.println(isPro);


    Shopping Cart First Excercise



}
}
