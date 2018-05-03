package uebung_3;

/**
 * A scanner and a FileInputStream are required for this exercise.
 */
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Solution for exercise 3a).
 * Read integers from a file using a scanner;
 * sum them up and print the results.
 * Also, stop the time doing this.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class SumScanner {
	/**
	 * File path of "bigInput.txt".
	 */
	private static String file = "/home/homeone/Informatik/2.Semester/Programmierung von Systemen/PvS_Repo/src/uebung_3/bigInput.txt";

	/**
	 * The main program.
	 * @param args String ]
	 */
	public static void main(String [] args) {
		// Prepare a sum counter and a time counter.
		long sum = 0;
		long time = 0;

		try {
			// Take the current system time, multiplying it by -1.
			time = System.currentTimeMillis() * -1;

			// Open the scanner with a FileInputStream which reads bigInput.txt.
			// Sum up all integers with a while-loop (as long as there are any left).
			Scanner scanner = new Scanner(new FileInputStream(file));
			while(scanner.hasNext()) {
				sum += scanner.nextInt();
			}

			// Now add the current system time to time, and
			// that's how long it took to sum up.
			time += System.currentTimeMillis();

			// Print the sum of all integers and
			// the time it took to sum up.
			System.out.println("Sum: "+sum);
			System.out.println("Time: "+time+" ms");
		}
		// Required for exception handling.
		catch(Exception e) {
			System.out.println("An error occured: "+e.toString());
		}
	}
}