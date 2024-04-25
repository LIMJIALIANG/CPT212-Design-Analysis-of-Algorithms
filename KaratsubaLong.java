import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class KaratsubaLong {

    // Counter for primitive operations
    static long counter = 0;

    public static long mult(long x, long y) {

        // 2 operations (2 comparison)
        counter += 2;
        if (x < 10 && y < 10) {
            // 1 operation (1 return statement)
            counter++;

            return x * y;
        }

        // 4 operations (2 function calls, 2 assignment)
        counter += 4;

        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);

        // 2 operations (1 max function, 1 assignment)
        counter += 2;

        int maxNumLength = Math.max(noOneLength, noTwoLength);

        // 4 operations (1 division, 1 modulus, 1 addition, 1 assignment)
        counter += 4;

        Integer halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);

        // 2 operations (1 power function, 1 assignment)
        counter += 2;

        // Multiplier
        long maxNumLengthTen = (long) Math.pow(10, halfMaxNumLength);

        // 8 operations (2 divisions, 2 modulus, 4 assignments)
        counter += 8;

        // Compute the expressions
        long a = x / maxNumLengthTen;
        long b = x % maxNumLengthTen;
        long c = y / maxNumLengthTen;
        long d = y % maxNumLengthTen;

        // 8 operations (3 function calls, 2 additions, 3 assignments)
        counter += 8;

        long z0 = mult(a, c);
        long z1 = mult(a + b, c + d);
        long z2 = mult(b, d);

        // 11 operations (3 multiplication, 2 power function, 2 addition, 2
        // subtractions, 1 assignment, 1 return )
        counter += 11;

        long ans = (z0 * (long) Math.pow(10, halfMaxNumLength * 2) +
                ((z1 - z0 - z2) * (long) Math.pow(10, halfMaxNumLength) + z2));

        return ans;
    }

    public static int numLength(long n) {
        // 2 operation (1 assignment, 1 comparison)
        counter += 2;
        int noLen = 0;

        while (n > 0) {
            // 4 operations per loop (1 addition, 1 division, 2 assignments)
            counter += 4;
            noLen++;
            n /= 10;
        }

        // 1 operation (return statement)
        counter++;
        return noLen;
    }

    public static void main(String[] args) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("Karatsuba.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the column names to the CSV file
        pw.println("n,Number of Operations");

        // Test case 1
        long expectedProduct = 1234 * 5678;
        long actualProduct = mult(1234, 5678);

        assert (expectedProduct == actualProduct);

        counter = 0;

        // Test case 2
        expectedProduct = 102 * 313;
        actualProduct = mult(102, 313);

        assert (expectedProduct == actualProduct);

        counter = 0;

        // Test case 3
        expectedProduct = 1345 * 63456;
        actualProduct = mult(1345, 63456);

        assert (expectedProduct == actualProduct);

        counter = 0;

        // for n > 18, need to use bigInteger otherwise n value is inaccurate
        for (int n = 1; n <= 18; n++) {
            counter = 0;
            // Generate two numbers of length n
            long num1 = (long) Math.pow(10, n - 1);
            long num2 = (long) Math.pow(10, n) - 1;

            long product = mult(num1, num2);

            // Write the number of operations to the CSV file
            pw.println(n + "," + counter);

            System.out.println(n + " : " + counter + " : " + product + "\n");
        }

        pw.close();

    }
}