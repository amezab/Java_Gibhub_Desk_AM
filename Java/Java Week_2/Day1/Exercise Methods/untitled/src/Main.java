//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        printWelcomeMessage();
        sum(1, 2);
        convertToFahrenheit(25);
        boolean result1 = isEven(20);
        printMultipleTimes("Hola", 5);
        findMax(5, 10, 25);
        factorial(63);
        greet("Mana", 25);
        System.out.println();
        String s1 = "Hello World";
        int count1 = countVowels(s1);
        System.out.println("Number of vowels " +count1 );
        String s2 = "java programing";
        int count2 = countVowels(s2);
        System.out.println("Number of vowels " +count2 );

        System.out.println("the result is" + calculator(1,5,'+'));



    }

    //Task1
    public static void printWelcomeMessage() {
        System.out.println("Welcome to the Java Method Exercise");
    }

    //Task2
    public static int sum(int num1, int num2) {
        int sum = num1 + num2;
        System.out.println(sum);
        return sum;
    }

    //Task3
    public static double convertToFahrenheit(double celsius) {
        double fahrenheit = (celsius * 9 / 5) + 32;
        System.out.println(celsius + "C is equal to " + fahrenheit + "F");
        return fahrenheit;
    }

    //Task4
    public static boolean isEven(int number) {
        if (number % 2 == 0) {
            System.out.println("Its even");
            return true;
        } else {
            System.out.println("its not even");
            return false;
        }
    }

    //task5
    public static void printMultipleTimes(String text, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(text);

        }
    }

    //task 6 Find the Max of three
    public static int findMax(int a, int b, int c) {
        int max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        System.out.println("Max is " + max);
        return max;
    }

    //Tas7 Factorial
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {//this takes care of errors!
            throw new IllegalArgumentException("Factorial is not for negative numbers");
        } else
            return n * factorial(n - 1);
    }

    //task8 method overloading
    public static String greet(String name) {
        System.out.println("Hello, " + name);
        return name;
    }

    public static void greet(String name, int age) {
        System.out.printf("Hello, %s! You are %d years old.", name, age);

    }

    //9 count vowels in a stream
    public static int countVowels(String text) {
        int vowelCount = 0; // Initialize counter for vowels

        // Convert the entire text to lowercase to simplify vowel checking
        // This means 'A' will be treated the same as 'a'.
        String lowerCaseText = text.toLowerCase();

        // Loop through each character of the string
        for (int i = 0; i < lowerCaseText.length(); i++) {
            char ch = lowerCaseText.charAt(i); // Get the character at the current index

            // Use an if statement to check if the character is one of the vowels
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCount++; // Increment the counter if it's a vowel
            }
        }

        // CORRECTED LINE: Return the final count after checking all characters
        return vowelCount;
    }
    public static int calculator(int num1, int num2,  char operator ){
        double result;
        switch (operator){
            case '+':
            result = (double) num1 + num2;
            break;
            case  '-':
                result = (double) num1 - num2;
                break;
            case  '*' :
                result = (double) num1 * num2;
                break;
            case '/':
                result = (double) num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid Operator, please enter '+'-'/'*'");

        }return (int) result;


    }

}



