package uebung_12;

/**
 * BulletinBoard allows to publish
 * messages on the console.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class BulletinBoard {
	/**
	 * Takes an array of strings and prints them on the console,
	 * one message each.
	 * This method is synchronized, so that a person publishing messages will
	 * not be interrupted by another.
	 * @param messages - String[]
	 */
	public synchronized void publishToBoard(String [] messages){
		System.out.println("Publishing messages:");
		for(String message: messages)
			System.out.println(message);
		System.out.println("Person has finished publishing");
	}
}