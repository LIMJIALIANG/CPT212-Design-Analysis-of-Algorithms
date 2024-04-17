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
        long lastDigitX, extraPartial, oldpartial = -1, oldcarrier = 0;

        while (y != 0) {
            long resetX = x; // each time after ONE digit of multiplier multiplies with multiplicand, multiplicand will be reset
            long lastDigitY = y % 10; // last digit of multiplier, until multiplier turns "0", loop stops
            long multiplierPosition1 = 1;
            long newpartial = 0;
            long newcarrier = 0;

            // Determine the number of digits in the multiplicand for formatting
            int numDigits = String.valueOf(x).length();

            while (resetX != 0) {
                lastDigitX = resetX % 10; // take the last digit of multiplicand everytime to multiply with last digit of multiplier
                newpartial += lastDigitX * lastDigitY * multiplierPosition1;
                multiplierPosition1 *= 10; // multiply with 10 evertime partial product formed for the formation of carrier (if any)

                if (newpartial >= multiplierPosition1) { // if carrier exist
                    extraPartial = newpartial / multiplierPosition1;
                    newcarrier += extraPartial * (multiplierPosition1 / 10);
                    newpartial -= extraPartial * multiplierPosition1;
                }

                resetX /= 10;
            }

            // Format partial product and carrier to maintain leading zeros or ensure n digits of "0"
            String partialProductStr = (newpartial == 0) ? String.format("%0" + numDigits + "d", 0) : String.format("%0" + numDigits + "d", newpartial);
            String carrierStr = (newcarrier == 0) ? String.format("%0" + numDigits + "d", 0) : String.format("%0" + numDigits + "d", newcarrier);

            System.out.println("Partial Product for (=" + x + " x " + lastDigitY + ") : " + partialProductStr);
            System.out.println("Carrier for (=" + x + " x " + lastDigitY + ") : " + carrierStr);

            y /= 10; // multiplier have new last digit for multiplication continuation

            if (oldpartial == -1) { //use -1 to avoid logic error when oldpartial is 0 during multiplication process
                result += newpartial;
                oldpartial = newpartial;
                oldcarrier = newcarrier;
            } else {
                result += oldcarrier * multiplierPosition2 + newpartial * multiplierPosition2;
                oldpartial = newpartial;
                oldcarrier = newcarrier;
            }

            multiplierPosition2 *= 10;

            if (y == 0) {
                result += newcarrier * multiplierPosition2;
            }

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

        // Perform multiplication
        long result = multiply(multiplicand, multiplier);

        // Output the result and counters
        System.out.println("\nMultiplicand: " + multiplicand);
        System.out.println("Multiplier: " + multiplier);
        System.out.println("Result: " + result);
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
