package y2022.day02;

import java.util.Scanner;

public class Day02 {

	public static void main(String[] args) {

		// Create a scanner to read input from the console.
		Scanner scanner = new Scanner(System.in);

		// Add a message telling users what to do.
		System.out.println("Paste puzzle input here and hit enter to enter a blank line...");

		/*
		 * This puzzle reads a set of instructions (integers) from the input
		 *
		 * Part 1: determine far and deep the submarine goes
		 *
		 * Part 2: use the aim to modify the command behaviors and determine
		 *         how far and deep the submarine goes using the new instructions.
		 */

		// Values used for Part 1
		int depth = 0;
		int horizontalPosition = 0;

		// Values used for Part 2
		int aim2 = 0;
		int depth2 = 0;
		int horizontalPosition2 = 0;

		while (true) {

			// Read a line and split it on the blank space between
			// the command and the amount value
			String [] inputs = scanner.nextLine().split(" ");

			// If there are fewer than 2 inputs after splitting the line
			// then stop.
			if (inputs.length < 2) {
				break;
			}

			String command = inputs[0];
			int    amount  = Integer.valueOf(inputs[1]);

			/*
			 * Command processing
			 */
			switch (command) {

			case "forward":

				// Part 1: Command "forward" increases the position
				horizontalPosition += amount;

				// Part 2: Command "forward" increases the position and depth
				horizontalPosition2 += amount;
				depth2              += amount * aim2;
				break;

			case "down":

				// Part 1: Command "down" increases the depth
				depth += amount;

				// Part 2: Command "down" increases the aim
				aim2 += amount;

				break;

			case "up":

				// Part 1: Command "up" decreases the depth
				depth -= amount;

				// Part 2: Command "up" decreases the aim
				aim2 -= amount;
				break;

			default:
				System.out.println("Unknown command encountered (" + command + ")");
			}
		}

		/*
		 * Part 1 - print the horizontal position multiplied by the depth
		 */
		System.out.println("Part 1: Horizontal position " + horizontalPosition +
				" * depth " + depth + " = " + horizontalPosition * depth);

		/*
		 * Part 2 - print the horizontal position multiplied by the depth
		 */
		System.out.println("Part 2: Horizontal position " + horizontalPosition2 +
				" * depth " + depth2 + " = " + horizontalPosition2 * depth2);

		// Clean up the scanner.
		scanner.close();
	}

}
