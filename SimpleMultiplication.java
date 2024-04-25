import java.util.Scanner;
import java.util.Random;

class SimpleMultiplication {
    // Counters for primitive operations
    static int operations = 0;

    // Function to multiply two numbers using the simple multiplication algorithm
    public static long multiply(long x, long y) { // x = multiplicand, y = multiplier
        long result = 0;
        long multiplierPosition2 = 1; // not in the loop since it is used to multiply with the partial product and carrier from stage to stage
        long lastDigitX, product;
        operations += 2; // 2 assignments

        // Determine the number of digits in the multiplicand for formatting
        long numDigits = String.valueOf(x).length();
        operations += 3; // 1 assignment, 1 conversion of integer into String and 1 calculation of length of the String

        operations += 2; //initialization of i and last comparison of i and numDigits
        for (int i = 0; i < numDigits; i++, operations += 3) {
            long resetX = x; // each time after ONE digit of multiplier multiplies with multiplicand, multiplicand will be reset
            long lastDigitY = y % 10; // last digit of multiplier, until multiplier turns "0", loop stops
            long multiplierPosition1 = 1;
            long partial = 0;
            long carrier = 0;
            operations += 6; 
            // operations += 3 in for loop is for the increment of i, the comparison of i with numDigits, and the assignment of i after addition in each iteration of the outer loop
            // operations += 6 here means 5 assignments and 1 modulus calculation

            operations += 2; //initialization of j and last comparison of j and numDigits
            for (int j = 0; j < numDigits; j++, resetX /= 10, operations += 5) {
                lastDigitX = resetX % 10; // take the last digit of multiplicand everytime to multiply with last digit of multiplier
                product = lastDigitX * lastDigitY;
                partial += (product % 10) * multiplierPosition1;
                carrier += (product / 10) * multiplierPosition1;
                multiplierPosition1 *= 10;

                operations += 14;
                // operations += 5 in for loop is for the increment of j, the comparison of j with numDigits, the assignment of j after addition, division of resetX with 10 and assignment of resetX
                // operations += 14 here means 5 assignments, 2 additions, 4 multiplications, 1 division and 2 modulus calculations
            }

            partial *= multiplierPosition2;
            carrier *= multiplierPosition2 * 10;
            result += partial + carrier;
            operations += 8; // 3 assignments, 2 additions and 3 multiplications

            multiplierPosition2 *= 10;
            y /= 10; // multiplier have new last digit for multiplication continuation
            operations += 4; // 2 assignments, 1 multiplication and 1 modulus calculation

            System.out.println(String.format("%1$22s", partial) + " | " + "Partial Product for (" + x + " x " + lastDigitY + ")");
            if (i == numDigits - 1){
                System.out.println("+" + String.format("%1$21s", carrier) + " | " + "Carrier for (" + x + " x " + lastDigitY + ")");
            }
            else{
                System.out.println(String.format("%1$22s", carrier) + " | " + "Carrier for (" + x + " x " + lastDigitY + ")");
            }
        }
        operations++; // 1 return statement
        return result; 
    }

    public static void main(String[] args) {
        // Generate random numbers of n digits
        Random random = new Random();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of digits between 1 and 10 : ");
        int n = scanner.nextInt();

        while (n < 1 || n > 10) {
            System.out.println("Invalid input. Please enter a number between 1 and 10: ");
            n = scanner.nextInt();
        }

        System.out.println("You entered: " + n);
        System.out.println("Generating two numbers, multiplicand and multiplier with " + n + " number of digits...");

        long multiplicand = generateRandomNumber(n, random);
        long multiplier = generateRandomNumber(n, random);

        // Reset counters
        operations = 0;

        // Display the Multiplicand and Multiplier calculated
        System.out.println("Multiplicand: " + multiplicand);
        System.out.println("Multiplier: " + multiplier + "\n");

        System.out.println(String.format("%1$22s", multiplicand));
        System.out.println("x" + String.format("%1$21s", multiplier));
        System.out.println("----------------------");

        // Perform multiplication
        long result = multiply(multiplicand, multiplier);

        System.out.println("----------------------");
        System.out.println(String.format("%1$22s", result));
        System.out.println("----------------------");
        System.out.println("----------------------");

        // Output the result and counters
        System.out.println("\nResult: " + result);
        System.out.println("Number of Primitive Operations: " + operations);
    }

    // Function to generate a random number with n digits
    public static long generateRandomNumber(int n, Random random) {
        long min = (long) Math.pow(10, n - 1);
        long max = (long) Math.pow(10, n) - 1;
        return min + random.nextInt((int) (max - min + 1));
    }
}
