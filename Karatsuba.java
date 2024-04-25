import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

class Karatsuba {

    // Counter for primitive operations
    static long counter = 0;

    public static BigInteger mult(BigInteger x, BigInteger y) {
        // 2 operations (2 comparisons)
        counter += 2;
        if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
            // 1 operation (1 return statement)
            counter++;
            return x.multiply(y);
        }

        // 4 operations (2 function calls, 2 assignments)
        counter += 4;
        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);

        // 2 operations (1 max function, 1 assignment)
        counter += 2;
        int maxNumLength = Math.max(noOneLength, noTwoLength);

        // 4 operations (1 division, 1 modulus, 1 addition, 1 assignment)
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

        // 11 operations (3 multiplications, 2 power functions, 2 additions,
        // 2 subtractions, 1 assignment, 1 return )
        counter += 11;
        BigInteger ans = z0.multiply(maxNumLengthTen.pow(halfMaxNumLength * 2))
                .add((z1.subtract(z0).subtract(z2)).multiply(maxNumLengthTen.pow(halfMaxNumLength)))
                .add(z2);

        return ans;
    }

    public static int numLength(BigInteger n) {
        // 2 operations (1 assignment, 1 comparison)
        counter += 2;
        int noLen = 0;

        while (n.compareTo(BigInteger.ZERO) > 0) {
            // 4 operations per loop (1 addition, 1 division, 2 assignments)
            counter += 4;
            noLen++;
            n = n.divide(BigInteger.TEN);
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