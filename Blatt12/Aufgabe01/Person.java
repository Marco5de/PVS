package uebung_12;

/**
 * The class Person extend Thread.
 * It publishes messages on a {@link BulletinBoard}.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class Person extends Thread{
	/**
	 * The messages a person has.
	 */
	private String [] messages;
	/**
	 * The BullteninBoard where those messages are published to.
	 */
	private BulletinBoard board;
	/**
	 * How often does every person publish their messages?
	 */
	private static int numberOfRepetitions = 6;

	/**
	 * Creates a new person.
	 * @param board - BulletinBoard
	 * @param messages - String[]
	 */
	public Person(BulletinBoard board, String[] messages){
		this.messages = messages;
		this.board = board;
	}

	/**
	 * The run() method.
	 * Each persons publishes their messages to the board
	 * for {@link Person#numberOfRepetitions} times.
	 * @see {@link Thread}
	 */
	public void run(){
		for(int i = 0; i < numberOfRepetitions; i++){
			board.publishToBoard(messages);
		}
	}
}