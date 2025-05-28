//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
      double temperatureCelcius = 25.0;// Current temperature in Celsius
      boolean isRaining = false;// Current temperature in Celsius
      int windSpeedKmh = 10;// Wind speed in km/h
        
        double tempetuareFahrenheit = (temperatureCelcius * ((double) 9 /5) + 32);
        System.out.println(tempetuareFahrenheit);
        
        temperatureCelcius += 5;
        System.out.println(temperatureCelcius);

        windSpeedKmh += 5;
        System.out.println(windSpeedKmh);

        if (tempetuareFahrenheit > 85) {
            System.out.println("Fahrenheit exceeds 85°F");
        }
        if (windSpeedKmh > 20 ) {
            System.out.println("wind speed is greater than 20 km/h");
        }
        if (tempetuareFahrenheit >= 60 && tempetuareFahrenheit <= 85 && isRaining) {
            System.out.println(" it's a good day to go outside");
        }
        if (windSpeedKmh >= 30 || temperatureCelcius < 0) {
            System.out.println("there's a weather warning");
        }
//Gaming - Player Score and Level Up System

        int currentXP = 1500; //experience points
        int level = 9;
        int xpTopNextLevel = 1500;
        boolean levelUp;

        int questCompleted = 300;// This variable now holds the XP awarded by the quest
        currentXP += questCompleted; //currentXP is now 1200 + 300 = 1500
        xpTopNextLevel -= questCompleted;// whats left to move on to the next level is subtracted from questCompleted
        boolean isDoubleXPActive;
        int boost = currentXP *=2;
        System.out.println("new boost " + boost);

//2. Use Comparison Operators:
        if (currentXP >= xpTopNextLevel) {
            System.out.println("currentXP is greater or equal to xpToNextLevel. ");
        }
        if (10 == level){
            System.out.println("level 10 achieved");
        }
        if (currentXP == 1500 && level < 10){
            System.out.println("levels up");
        }
        if (level > 7 || currentXP > 5_000){
            System.out.println("Its a Pro");
        }









        }



    }
