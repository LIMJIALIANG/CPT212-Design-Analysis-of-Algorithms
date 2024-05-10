/// Java Program to Implement Karatsuba Algorithm

// Importing Random class from java.util packahge
import java.util.Random;

// MAin class 
class KaratsubaModifiedOriginal {
    static long counter = 0;

    // Main driver method
    public static long mult(long x, long y) {

        // Checking only if input is within range
        if (x < 10 && y < 10) {

            // Multiplying the inputs entered
            return x * y;
        }

        // Declaring variables in order to
        // Find length of both integer
        // numbers x and y
        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);

        // Finding maximum length from both numbers
        // using math library max function
        int maxNumLength = Math.max(noOneLength, noTwoLength);

        // Rounding up the divided Max length
        Integer halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);

        // Multiplier
        long maxNumLengthTen = (long) Math.pow(10, halfMaxNumLength);

        // Compute the expressions
        long a = x / maxNumLengthTen;
        long b = x % maxNumLengthTen;
        long c = y / maxNumLengthTen;
        long d = y % maxNumLengthTen;

        // Compute all mutilpying variables
        // needed to get the multiplication
        long z0 = mult(a, c);
        long z1 = mult(a + b, c + d);
        long z2 = mult(b, d);

        System.out.println("z0: " + z0);
        System.out.println("z1: " + z1);
        System.out.println("z2: " + z2);

        long ans = (z0 * (long) Math.pow(10, halfMaxNumLength * 2) +
                ((z1 - z0 - z2) * (long) Math.pow(10, halfMaxNumLength) + z2));

        return ans;

    }

    // Method 1
    // To calculate length of the number
    public static int numLength(long n) {
        int noLen = 0;
        while (n > 0) {
            noLen++;
            n /= 10;
        }

        // Returning length of number n
        return noLen;
    }

    public static void runTestCases() {

        // Case 1: Big integer lengths
        long x1 = 1234;
        long y1 = 5678;
        long expectedProduct1 = x1 * y1;
        long actualProduct1 = mult(x1, y1);
        int n1 = numLength(Math.max(x1, y1));

        // Printing the expected and corresponding actual product
        System.out.println("Test Case 1:");
        System.out.println("Expected: " + expectedProduct1);
        System.out.println("Actual: " + actualProduct1);
        System.out.println("Counter (number of operations): " + counter);
        System.out.println("Length of number (n): " + n1 + "\n\n");

        assert (expectedProduct1 == actualProduct1);

        // Reset counter for next test case
        counter = 0;

        // Case 2:
        long x2 = 102;
        long y2 = 313;
        long expectedProduct2 = x2 * y2;
        long actualProduct2 = mult(x2, y2);
        int n2 = numLength(Math.max(x2, y2));

        System.out.println("Test Case 2:");
        System.out.println("Expected: " + expectedProduct2);
        System.out.println("Actual: " + actualProduct2);
        System.out.println("Counter (number of operations): " + counter);
        System.out.println("Length of number (n): " + n2 + "\n\n");

        assert (expectedProduct2 == actualProduct2);

        // Reset counter for next test case
        counter = 0;

        // Case 3:
        long x3 = 1345;
        long y3 = 63456;
        long expectedProduct3 = x3 * y3;
        long actualProduct3 = mult(x3, y3);
        int n3 = numLength(Math.max(x3, y3));

        System.out.println("Test Case 3:");
        System.out.println("Expected: " + expectedProduct3);
        System.out.println("Actual: " + actualProduct3);
        System.out.println("Counter (number of operations): " + counter);
        System.out.println("Length of number (n): " + n3 + "\n\n");

        assert (expectedProduct3 == actualProduct3);
    }

    // Method 2
    // Main driver function
    public static void main(String[] args) {
        // Showcasing karatsuba multiplication

        runTestCases();

        // Integer x = null;
        // Integer y = null;
        // Integer MAX_VALUE = 10000;

        // // Boe creating an object of random class
        // // inside main() method
        // Random r = new Random();

        // for (int i = 0; i < MAX_VALUE; i++) {
        // x = (int) r.nextInt(MAX_VALUE);
        // y = (int) r.nextInt(MAX_VALUE);

        // expectedProduct = x * y;

        // if (i == 9999) {

        // // Prove assertions catch the bad stuff.
        // expectedProduct = 1;
        // }
        // actualProduct = mult(x, y);

        // // Again printing the expected and
        // // corresponding actual product
        // System.out.println("Expected: " + expectedProduct);
        // System.out.println("Actual: " + actualProduct + "\n\n");

        // assert (expectedProduct == actualProduct);
        // }
    }
}