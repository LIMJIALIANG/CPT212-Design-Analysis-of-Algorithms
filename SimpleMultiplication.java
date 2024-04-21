import java.util.Random;

class SimpleMultiplication {
    // Counters for primitive operations
    static int assignmentCount = 0;
    static int additionCount = 0;
    static int multiplicationCount = 0;

    // Function to multiply two numbers using the simple multiplication algorithm
    public static long multiply(long x, long y) { // x = multiplicand, y = multiplier
        long result = 0;
        long multiplierPosition2 = 1; // not in the loop since it is used to multiply with the partial product and carrier from stage to stage
        long lastDigitX, extraPartial;

        // Determine the number of digits in the multiplicand for formatting
        int numDigits = String.valueOf(x).length();

        for (int i = 0; i < numDigits; i++) {
            long resetX = x; // each time after ONE digit of multiplier multiplies with multiplicand, multiplicand will be reset
            long lastDigitY = y % 10; // last digit of multiplier, until multiplier turns "0", loop stops
            long multiplierPosition1 = 1;
            long partial = 0;
            long carrier = 0;

            for (int j = 0; j < numDigits; j++, resetX /= 10) {
                lastDigitX = resetX % 10; // take the last digit of multiplicand everytime to multiply with last digit of multiplier
                partial += lastDigitX * lastDigitY * multiplierPosition1;
                multiplierPosition1 *= 10; // multiply with 10 evertime partial product formed for the formation of carrier (if any)

                if (partial >= multiplierPosition1) { // if carrier exist
                    extraPartial = partial / multiplierPosition1;
                    carrier += extraPartial * (multiplierPosition1/10);
                    partial -= extraPartial * multiplierPosition1;
                }
            }

            partial *= multiplierPosition2;
            carrier *= multiplierPosition2 * 10;
            result += partial + carrier;

            multiplierPosition2 *= 10;
            y /= 10; // multiplier have new last digit for multiplication continuation

            System.out.println("Partial Product for (=" + x + " x " + lastDigitY + ") : " + partial);
            System.out.println("Carrier for (=" + x + " x " + lastDigitY + ") : " + carrier);

            // Increment counters
            assignmentCount += 2;
            multiplicationCount += 2;
            additionCount += 2;
        }
        return result;
    }

    public static void main(String[] args) {
        // Generate random numbers of n digits
        Random random = new Random();
        int minDigits = 1; // Minimum number of digits
        int maxDigits = 10; // Maximum number of digits
        int n = random.nextInt(maxDigits - minDigits + 1) + minDigits; // Random number of digits between 1 and 10
        long multiplicand = generateRandomNumber(n, random); // 50954; // 97301; // Testing numbers
        long multiplier = generateRandomNumber(n, random); // 80669; // 76171;

        // Reset counters
        assignmentCount = 0;
        additionCount = 0;
        multiplicationCount = 0;

        // Display the Multiplicand and Multiplier calculated
        System.out.println("Multiplicand: " + multiplicand);
        System.out.println("Multiplier: " + multiplier + "\n");

        // Perform multiplication
        long result = multiply(multiplicand, multiplier);

        // Output the result and counters
        System.out.println("\nResult: " + result);
        System.out.println("\nPrimitive Operations:");
        System.out.println("Assignments: " + assignmentCount);
        System.out.println("Additions: " + additionCount);
        System.out.println("Multiplications: " + multiplicationCount);
    }

    // Function to generate a random number with n digits
    public static long generateRandomNumber(int n, Random random) {
        long min = (long) Math.pow(10, n - 1);
        long max = (long) Math.pow(10, n) - 1;
        return min + random.nextInt((int) (max - min + 1));
    }
}
