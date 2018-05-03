package uebung_3;

/**
 * Required classes.
 */
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Solution for exercise 3b).
 * Rather then a scanner, a BufferedReader is used
 * to sum up numbers as in 3a).
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class SumReader {
	/**
	 * File path for "bigInput.txt".
	 */
	private static String path = "/home/homeone/Informatik/2.Semester/Programmierung von Systemen/PvS_Repo/src/uebung_3/bigInput.txt";

	/**
	 * Comment is only provided where differences occur
	 * to {@link SumScanner#main(String[])}.
	 * @param args String[]
	 */
	public static void main(String [] args) {
		long sum = 0;
		long time = -1 * System.currentTimeMillis();

		try {
			// Set up the BufferedReader.
			BufferedReader reader = new BufferedReader(new FileReader(path));

			/*
			 * As long as lines still remain to be read,
			 * do so, and convert them to integers, then
			 * add them to sum.
			 */
			String s;
			while((s = reader.readLine()) != null) {
				sum += Integer.valueOf(s);
			}

			time += System.currentTimeMillis();
			System.out.println("Sum: "+sum);
			System.out.println("Time: "+time+" ms");
		}
		catch(Exception e){
			System.out.println("An error occured: "+e.toString());
		}
	}
}