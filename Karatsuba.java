import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

class Karatsuba {

    // Counter for primitive operations
    static long counter = 0;

    public static BigInteger mult(BigInteger x, BigInteger y) {
        // 5 operations (2 comparisons, 1 logical AND, 1 if statement, 2 comparison
        // function calls)
        counter += 5;
        if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
            // 2 operations (1 multiplication function call, 1 return statement)
            counter += 2;
            return x.multiply(y);
        }

        // 4 operations (2 function calls, 2 assignments)
        counter += 4;
        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);

        // 2 operations (1 max function call, 1 assignment)
        counter += 2;
        int maxNumLength = Math.max(noOneLength, noTwoLength);

        // 4 operations (1 division, 1 addition, 1 modulus, 1 assignment)
        counter += 4;
        int halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);

        // 2 operations (1 power function, 1 assignment)
        counter += 2;
        BigInteger maxNumLengthTen = BigInteger.TEN.pow(halfMaxNumLength);

        // 8 operations (2 divisions, 2 modulus, 4 assignments)
        counter += 8;
        BigInteger a = x.divide(maxNumLengthTen);
        BigInteger b = x.mod(maxNumLengthTen);
        BigInteger c = y.divide(maxNumLengthTen);
        BigInteger d = y.mod(maxNumLengthTen);

        // 8 operations (3 function calls, 2 additions, 3 assignments)
        counter += 8;
        BigInteger z0 = mult(a, c);
        BigInteger z1 = mult(a.add(b), c.add(d));
        BigInteger z2 = mult(b, d);

        // 11 operations (2 multiplication function call, 2 power function, 2 addition
        // function, 1 multiplication, 2 subtraction function, 1 assignment, 1 return )
        counter += 11;
        BigInteger ans = z0.multiply(BigInteger.TEN.pow(halfMaxNumLength * 2))
                .add((z1.subtract(z0).subtract(z2)).multiply(BigInteger.TEN.pow(halfMaxNumLength)))
                .add(z2);

        return ans;
    }

    public static int numLength(BigInteger n) {
        // 1 operations (1 assignment)
        counter++;
        int noLen = 0;

        // 2 operations (1 comparison, 1 comparison function call)
        counter += 2;

        while (n.compareTo(BigInteger.ZERO) > 0) {

            // 4 operations per loop (1 addition, 1 division, 2 assignments)
            counter += 4;
            noLen++;
            n = n.divide(BigInteger.TEN);

            // 2 operations (1 comparison, 1 comparison function call)
            counter += 2;
        }

        // 1 operation (return statement)
        counter++;
        return noLen;
    }

    public static void runTestCases() {
        // Case 1: Big integer lengths
        BigInteger x1 = BigInteger.valueOf(1234);
        BigInteger y1 = BigInteger.valueOf(5678);
        BigInteger expectedProduct1 = x1.multiply(y1);
        BigInteger actualProduct1 = mult(x1, y1);
        int n1 = numLength(x1.max(y1));

        // Printing the expected and corresponding actual product
        System.out.println("Test Case 1:");
        System.out.println("Expected: " + expectedProduct1);
        System.out.println("Actual: " + actualProduct1);
        System.out.println("Counter (number of operations): " + counter);
        System.out.println("Length of number (n): " + n1 + "\n\n");

        assert (expectedProduct1.equals(actualProduct1));

        // Reset counter for next test case
        counter = 0;

        // Case 2:
        BigInteger x2 = BigInteger.valueOf(102);
        BigInteger y2 = BigInteger.valueOf(313);
        BigInteger expectedProduct2 = x2.multiply(y2);
        BigInteger actualProduct2 = mult(x2, y2);
        int n2 = numLength(x2.max(y2));

        System.out.println("Test Case 2:");
        System.out.println("Expected: " + expectedProduct2);
        System.out.println("Actual: " + actualProduct2);
        System.out.println("Counter (number of operations): " + counter);
        System.out.println("Length of number (n): " + n2 + "\n\n");

        assert (expectedProduct2.equals(actualProduct2));

        // Reset counter for next test case
        counter = 0;

        // Case 3:
        BigInteger x3 = BigInteger.valueOf(1345);
        BigInteger y3 = BigInteger.valueOf(63456);
        BigInteger expectedProduct3 = x3.multiply(y3);
        BigInteger actualProduct3 = mult(x3, y3);
        int n3 = numLength(x3.max(y3));

        System.out.println("Test Case 3:");
        System.out.println("Expected: " + expectedProduct3);
        System.out.println("Actual: " + actualProduct3);
        System.out.println("Counter (number of operations): " + counter);
        System.out.println("Length of number (n): " + n3 + "\n\n");

        assert (expectedProduct3.equals(actualProduct3));
    }

    public static void main(String[] args) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("Karatsuba.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        runTestCases();

        // Write the column names to the CSV file
        pw.println("n,Number of Operations");

        for (int n = 1; n <= 1000; n++) {
            counter = 0; // Reset the counter

            // Generate two numbers of length n
            BigInteger num1 = BigInteger.TEN.pow(n - 1);
            BigInteger num2 = BigInteger.TEN.pow(n).subtract(BigInteger.ONE);

            // Perform the multiplication operation
            BigInteger product = mult(num1, num2);

            assert (num1.multiply(num2).equals(product));

            // Write the number of operations to the CSV file
            pw.println(n + "," + counter);
        }

        pw.close();

    }
}
