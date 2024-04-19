# Karatsuba
To count the primitive operations executed by the Karatsuba algorithm, you can introduce a counter variable in your code. This counter will increment each time a primitive operation (like assignment, addition, etc.) is performed. Here's how you can modify your code:

```java
// Importing Random class from java.util packahge
import java.util.Random;

// Main class 
class Karatsuba {

    // Counter for primitive operations
    static long counter = 0;

    // Main driver method 
    public static long mult(long x, long y) {

        // Increment counter for comparison operation
        counter++;

        // Checking only if input is within range  
        if (x < 10 && y < 10) {
           
            // Increment counter for multiplication operation
            counter++;

            // Multiplying the inputs entered 
            return x * y;
        }

        // Increment counter for assignment operations
        counter += 4;

        // Declaring variables in order to  
        // Find length of both integer
        // numbers x and y
        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);

        // Increment counter for max function operation
        counter++;

        // Finding maximum length from both numbers
        // using math library max function
        int maxNumLength
            = Math.max(noOneLength, noTwoLength);

        // Increment counter for division and modulus operations
        counter += 2;

        // Rounding up the divided Max length
        Integer halfMaxNumLength
            = (maxNumLength / 2) + (maxNumLength % 2);

        // Increment counter for power function operation
        counter++;

        // Multiplier
        long maxNumLengthTen
            = (long)Math.pow(10, halfMaxNumLength);

        // Increment counter for division and modulus operations
        counter += 4;

        // Compute the expressions
        long a = x / maxNumLengthTen;
        long b = x % maxNumLengthTen;
        long c = y / maxNumLengthTen;
        long d = y % maxNumLengthTen;

        // Increment counter for function call operations
        counter += 3;

        // Compute all mutilpying variables
        // needed to get the multiplication    
        long z0 = mult(a, c);
        long z1 = mult(a + b, c + d);
        long z2 = mult(b, d);

        // Increment counter for power function, subtraction, addition, and multiplication operations
        counter += 7;

        long ans = (z0 * (long)Math.pow(10, halfMaxNumLength * 2) + 
                   ((z1 - z0 - z2) * (long)Math.pow(10, halfMaxNumLength) + z2));

        return ans;
    }

    // Method 1
    // To calculate length of the number
    public static int numLength(long n)
    {
        int noLen = 0;
        while (n > 0) {
            // Increment counter for comparison, increment, division operations
            counter += 3;

            noLen++;
            n /= 10;
        }

        // Returning length of number n
        return noLen;
    }

    // Method 2
    // Main driver function
    public static void main(String[] args)
    {
        // Showcasing karatsuba multiplication
        
        // Case 1: Big integer lengths
        long expectedProduct = 1234 * 5678;
        long actualProduct = mult(1234, 5678);

        // Printing the expected and corresponding actual product 
        System.out.println("Expected 1 : " + expectedProduct);
        System.out.println("Actual 1 : " + actualProduct + "\n\n");
    
        assert(expectedProduct == actualProduct);

        // Print the counter
        System.out.println("Number of operations: " + counter + "\n\n");

        // Reset the counter
        counter = 0;

        // Repeat for other test cases...
        expectedProduct = 102 * 313;
        actualProduct = mult(102, 313);

        System.out.println("Expected 2 : " + expectedProduct);
        System.out.println("Actual 2 : " + actualProduct + "\n\n");
        
        assert(expectedProduct == actualProduct);

        // Print the counter
        System.out.println("Number of operations: " + counter + "\n\n");

        // Reset the counter
        counter = 0;

        expectedProduct = 1345 * 63456;
        actualProduct = mult(1345, 63456);

        System.out.println("Expected 3 : " + expectedProduct);
        System.out.println("Actual 3 : " + actualProduct + "\n\n");
        
        assert(expectedProduct == actualProduct);        

        // Print the counter
        System.out.println("Number of operations: " + counter + "\n\n");

        // Reset the counter
        counter = 0;

        Integer x = null;
        Integer y = null;
        Integer MAX_VALUE = 10000;

        // Boe creating an object of random class
        // inside main() method 
        Random r = new Random();

        for (int i = 0; i < MAX_VALUE; i++) {
            x = (int) r.nextInt(MAX_VALUE);
            y = (int) r.nextInt(MAX_VALUE);

            expectedProduct = x * y;

            if (i == 9999) {
            
            // Prove assertions catch the bad stuff.
                expectedProduct = 1;    
            }
            actualProduct = mult(x, y);

            // Again printing the expected and 
            // corresponding actual product 
            System.out.println("Expected: " + expectedProduct);
            System.out.println("Actual: " + actualProduct + "\n\n");

            assert(expectedProduct == actualProduct);        

            // Print the counter
            System.out.println("Number of operations: " + counter + "\n\n");

            // Reset the counter
            counter = 0;
        }
    }
}
```

This code will count the number of primitive operations performed during the execution of the Karatsuba algorithm. You can print the counter after each multiplication to see the number of operations. Remember to reset the counter to zero before each new multiplication.

For the second part of your question, you can modify the program to randomly multiply two numbers of `n` digits, and then plot a graph of the number of operations versus `n`. This would require running the program multiple times with different values of `n`, collecting the number of operations for each run, and then using a tool like Excel or a Python script with matplotlib to create the plot.

The time complexity of the simple multiplication algorithm is $$O(n^2)$$, where `n` is the number of digits. The Karatsuba algorithm, on the other hand, has a time complexity of $$O(n^{log_2 3})$$, which is less than $$O(n^2)$$ for large `n`. This means that the Karatsuba algorithm should perform fewer operations than the simple multiplication algorithm for large `n`, which you should be able to observe in your experiment. However, keep in mind that the constant factors and overhead of the Karatsuba algorithm are higher, so it may not be faster for small `n`. 

Remember, the time complexity gives us an upper bound on the time taken by an algorithm in terms of the size of the input. It does not tell us the exact number of operations, and the actual number can vary depending on the specifics of the implementation and the input data. Therefore, the experimental results might not perfectly match the theoretical analysis, but they should show the same trend.