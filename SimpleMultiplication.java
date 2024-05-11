import java.util.Random;
import java.math.BigInteger;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

class SimpleMultiplication {
    // Counters for primitive operations
    static long operations = 0;

    // Function to multiply two numbers using the simple multiplication algorithm
    public static BigInteger multiply(BigInteger x, BigInteger y) { 
        BigInteger result = BigInteger.ZERO;
        BigInteger multiplierPosition2 = BigInteger.ONE;
        // 2 assignments
        operations += 2;

        BigInteger numDigits = new BigInteger(String.valueOf(x).length() + "");
        // 1 assignment, 1 conversion of integer into String and 1 calculation of length of the String
        operations += 3;

        //initialization of i and last function call and comparison of i and numDigits
        operations += 3;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(numDigits) < 0; i = i.add(BigInteger.ONE), operations += 4) {
            // each time after ONE digit of multiplier multiplies with multiplicand, multiplicand will be reset
            BigInteger resetX = x;
            // last digit of multiplier, until multiplier turns "0", loop stops
            BigInteger lastDigitY = y.mod(BigInteger.TEN);
            BigInteger multiplierPosition1 = BigInteger.ONE;
            BigInteger partial = BigInteger.ZERO;
            BigInteger carrier = BigInteger.ZERO;

            // operations += 4 in for loop is for the increment of i, function call, the comparison of i with numDigits, and the assignment of i after addition in each iteration of the outer loop
            // operations += 6 here means 5 assignments and 1 modulus calculation
            operations += 6;

            //initialization of j and last function call and comparison of j and numDigits
            operations += 3;
            for (BigInteger j = BigInteger.ZERO; j.compareTo(numDigits) < 0; j = j.add(BigInteger.ONE), resetX = resetX.divide(BigInteger.TEN), operations += 6) {
                // take the last digit of multiplicand everytime to multiply with last digit of multiplier
                BigInteger lastDigitX = resetX.mod(BigInteger.TEN);
                BigInteger product = lastDigitX.multiply(lastDigitY);
                partial = partial.add((product.mod(BigInteger.TEN)).multiply(multiplierPosition1));
                carrier = carrier.add((product.divide(BigInteger.TEN)).multiply(multiplierPosition1));
                multiplierPosition1 = multiplierPosition1.multiply(BigInteger.TEN);

                // operations += 6 in for loop is for the increment of j, function call, the comparison of j with numDigits, the assignment of j after addition, division of resetX with 10 and assignment of resetX
                // operations += 14 here means 5 assignments, 2 additions, 4 multiplications, 1 division and 2 modulus calculations
                operations += 14;
            }

            partial = partial.multiply(multiplierPosition2);
            carrier = carrier.multiply(multiplierPosition2.multiply(BigInteger.TEN));
            result = result.add(partial).add(carrier);
            // 3 assignments, 2 additions and 3 multiplications
            operations += 8;

            multiplierPosition2 = multiplierPosition2.multiply(BigInteger.TEN);
            //The multiplier has a new last digit for the multiplication to continue
            y = y.divide(BigInteger.TEN);
            // 2 assignments, 1 multiplication and 1 division
            operations += 4;

            if (numDigits.compareTo(new BigInteger("10")) <= 0){
                System.out.println(String.format("%1$22s", partial) + " | " + "Partial Product for (" + x + " x " + lastDigitY + ")");
                if (i.equals(numDigits.subtract(BigInteger.ONE))){
                    System.out.println("+" + String.format("%1$21s", carrier) + " | " + "Carrier for (" + x + " x " + lastDigitY + ")");
                }
                else{
                    System.out.println(String.format("%1$22s", carrier) + " | " + "Carrier for (" + x + " x " + lastDigitY + ")");
                }
            }
        }
        // 1 return statement
        operations++;
        return result;
    }

    public static void main(String[] args) {
        // Generate random numbers of n digits
        Random random = new Random();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("SimpleMultiplication.csv"));
            pw.println("n,Number of Operations");
            for (int n = 1; n <= 1000; n++) {
                operations = 0; // Reset the counter
                // Generate two numbers of length n
                BigInteger multiplicand = generateRandomNumber(n, random);
                BigInteger multiplier = generateRandomNumber(n, random);

                if (n <= 10){
                    System.out.println("\nGenerating two numbers, multiplicand and multiplier with " + n + " number of digits...");
                    System.out.println("Multiplicand: " + multiplicand);
                    System.out.println("Multiplier: " + multiplier + "\n");

                    System.out.println(String.format("%1$22s", multiplicand));
                    System.out.println("x" + String.format("%1$21s", multiplier));
                    System.out.println("----------------------");
                }

                // Perform the multiplication operation
                BigInteger result = multiply(multiplicand, multiplier);
                assert (multiplicand.multiply(multiplier).equals(result));

                if (n <= 10){
                    System.out.println("----------------------");
                    System.out.println(String.format("%1$22s", result));
                    System.out.println("----------------------");
                    System.out.println("----------------------");
                    // Output the result and counters
                    System.out.println("\nResult: " + result);
                    System.out.println("Number of Primitive Operations: " + operations);
                }

                // Write the number of operations to the CSV file
                pw.println(n + "," + operations);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    // Function to generate a random number with n digits
    public static BigInteger generateRandomNumber(int n, Random random) {
        // Generate a random number of n digits
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int digit = (i == 0) ? random.nextInt(9) + 1 : random.nextInt(10); // Ensure the first digit is not zero
            sb.append(digit);
        }
        return new BigInteger(sb.toString());
    }
}