package y2022.day01;

import java.util.Scanner;

public class Day01 {

	public static void main(String[] args) {

		// Create a scanner to read input from the console.
		Scanner scanner = new Scanner(System.in);

		// Add a message telling users what to do.
		System.out.println("Paste puzzle input here and enter -1 on the next line...");

		/*
		 * This puzzle reads a set of depths (integers) from the input
		 *
		 * Part 1: determine how many single step depths are an increase from the previous depth
		 *
		 * Part 2: determine how many 3 sequence average windows (groups) are increasing.
		 */

		// Set the starting depths from the first 3 input line
		int depth1 = Integer.valueOf(scanner.nextLine());
		int depth2 = Integer.valueOf(scanner.nextLine());
		int depth3 = Integer.valueOf(scanner.nextLine());

		int increasingSingleStepDepthCounter = 0;
		int increasingGroupDepthCounter  = 0;

		// Check the first two depths for the single step depth counter of part 1
		if (depth2 > depth1) {
			increasingSingleStepDepthCounter++;
		}
		if (depth3 > depth2) {
			increasingSingleStepDepthCounter++;
		}

		while (true) {

			int depth4 = Integer.valueOf(scanner.nextLine());

			// If we have found the -1, then stop reading
			if (depth4 < 0) {
				break;
			}

			// Part 1: count the increasing depths
			if (depth4 > depth3) {
				increasingSingleStepDepthCounter++;
			}

			// Part 2: count the increasing group depths
			int groupA = depth1 + depth2 + depth3;
			int groupB =          depth2 + depth3 + depth4;

			if (groupB > groupA) {
				increasingGroupDepthCounter++;
			}

			// Roll the depths in the group
			depth1 = depth2;
			depth2 = depth3;
			depth3 = depth4;
		}

		System.out.println("Part 1: Increasing single depths = " + increasingSingleStepDepthCounter );
		System.out.println("Part 2: Increasing group depths = " + increasingGroupDepthCounter );

		// Clean up the scanner.
		scanner.close();
	}

}
