package y2022.day03;

import java.util.Scanner;

public class Day03 {

    public static void main(String[] args) {

        // Create a scanner to read input from the console.
        Scanner scanner = new Scanner(System.in);

        // Add a message telling users what to do.
        System.out.println("Paste puzzle input here and hit enter to enter a blank line...");

        /*
         * This puzzle reads a set of binary values (represented as hex strings) from the input
         *
         * Part 1: determine the gamma and epsilon rates and power consumption
         */

        // Values used for Part 1
        int inputCount  = 0;
        int[] bitCounts = null;

        while (true) {

            String input = scanner.nextLine();

            // If the input is blank, then stop
            if (input.isBlank()) {
                break;
            }

            // Initialize the bit counter array to the length of the first input line
            if (bitCounts == null) {
                bitCounts = new int[input.length()];
            }

            char[] chars = input.toCharArray();

            // bit counters
            for (int i=0; i<chars.length; i++) {

                // If the character in the input is a 1, then
                // increment the counter for this position
                if (chars[i] == '1') {
                    bitCounts[i]++;
                }
            }

            // Keep track of the total number of lines
            inputCount ++;
        }

        /*
         * Part 1 - calculate the power by finding the gamma and epsilon
         */
        int gamma = 0;
        int epsilon = 0;

        // Starting at the most significant digit loop through
        // all digits checking if 1 is the most common digit,
        // and creating a number from the binary string at the same time.

        for (int i=0; i<bitCounts.length; i++) {

            gamma   *= 2;  // Multiply by 2 each bit because the number is binary
            epsilon *= 2;

            // The digit 1 is the most common if it occurs more than half the time
            // otherwise the digit 1 is the least common.
            // Zeroes look after themselves automatically
            if (bitCounts[i] > inputCount/2) {
                gamma ++;
            }
            else {
                epsilon++;
            }
        }

        System.out.println("Part 1: gamma " + gamma + " * epsilon " + epsilon + " = power " + (gamma*epsilon));

        // Clean up the scanner.
        scanner.close();
    }

}
