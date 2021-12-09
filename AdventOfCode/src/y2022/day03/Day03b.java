package y2022.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day03b {

    public static void main(String[] args) {

        // Create a scanner to read input from the console.
        Scanner scanner = new Scanner(System.in);

        // Add a message telling users what to do.
        System.out.println("Paste puzzle input here and hit enter to enter a blank line...");

        /*
         * This puzzle reads a set of binary values (represented as hex strings) from the input
         *
         * Part 2: filter the binary inputs to find the oxygen and scrubber ratings
         */

        List<String> oxygenList   = new ArrayList<>();
        List<String> scrubberList = new ArrayList<>();

        // Read all of the inputs into the oxygen and scrubber lists
        // both calculators start with the same inputs.
        while (true) {

            String input = scanner.nextLine();

            // If the input is blank, then stop
            if (input.isBlank()) {
                break;
            }

            oxygenList.add(input);
            scrubberList.add(input);
        }

        // Starting at the most significant digit loop through
        // all digits filtering both the oxygen and scrubber lists

        int bitCount = oxygenList.get(0).length();

        for (int bit=0; bit<bitCount; bit++) {

            // Oxygen
            // Stop when only one value remains
            if (oxygenList.size() > 1) {

                // Find the most common digit in the list at the current bit position
                char mostCommonDigit = findMostCommonDigit(oxygenList, bit);

                // Blank represents a tie, in this case keep ones only
                if (mostCommonDigit == ' ') {
                    mostCommonDigit = '1';
                }

                // Filter the list keeping the most common digit at the bit
                filterList(oxygenList, bit, mostCommonDigit);
            }

            // Scrubber
            if (scrubberList.size() > 1) {

                // Find the most common digit in the list at the current bit position
                char mostCommonDigit = findMostCommonDigit(scrubberList, bit);

                // Note: leastCommonDigit value in case of a tie is '0'.
                char leastCommonDigit = '0';
                if (mostCommonDigit == '0') {
                    leastCommonDigit = '1';
                }

                // Filter the list keeping the least common digit at the bit
                filterList(scrubberList, bit, leastCommonDigit);
            }

        }

        // Hopefully there is only one value left in each list
        if (oxygenList.size() != 1) {
            System.out.println("Error in oxygen list \n" + oxygenList);
        }

        int oxygen = convertBinaryStringToInt(oxygenList.get(0));

        if (scrubberList.size() != 1) {
            System.out.println("Error in scrubber list \n" + scrubberList);
        }

        int scrubber = convertBinaryStringToInt(scrubberList.get(0));

        System.out.println("Part 2: oxygen " + oxygen + " * scrubber " + scrubber
                + " = life support rating " + (oxygen*scrubber));

        // Clean up the scanner.
        scanner.close();
    }

    /**
     * Converts the binary string to an integer value
     * @param string containing only ones and zeros
     * @return the integer representation of the string.
     */
    private static int convertBinaryStringToInt(String string) {

        int value = 0;

        char[] chars = string.toCharArray();

        // loop through all digits from the most significant to the
        // least significant.
        for (char c: chars) {

            // each digit
            value *= 2;
            if (c == '1') {
                value++;
            }
        }

        return value;
    }

    /**
     * Filter the list removing any entries that do not have the keeper
     * value in the specified column
     * @param list filtered to remove any elements without the keeper value
     * @param bit number to use
     * @param keepChar in the bit number to use as a filter, should be
     *        '1' or '0'
     */
    private static void filterList(List<String> list, int bit, char keepChar) {

        // You cannot modify the list while iterating over it, so keep
        // a list of keepers, and then clear the list and replace it with
        // the keepers.

        List<String> keeperList = new ArrayList<>();

        for (String element: list) {

            if (element.charAt(bit) == keepChar) {
                keeperList.add(element);
            }
        }

        // Clear the list and add all the values to keep
        list.clear();
        list.addAll(keeperList);
    }

    /**
     * Find the most common digit (char) in the specified bit/column.
     * @param list of numbers
     * @param bit / column to check
     * @return '1' if one is the most common, '0' if zero is the most common
     *         or ' ' (blank space character) if there is a tie.
     */
    private static char findMostCommonDigit(List<String> list, int bit) {

        int zeroCount = 0;
        int oneCount  = 0;

        for (String element: list) {

            char bitChar = element.charAt(bit);

            if (bitChar == '1') {
                oneCount ++;
            }
            else {
                zeroCount++;
            }
        }

        // Return blank in case of a tie
        if (oneCount == zeroCount) {
            return ' ';
        }

        if (oneCount > zeroCount) {
            return '1';
        }

        return '0';
    }

}
