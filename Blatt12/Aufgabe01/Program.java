package uebung_12;
/**
 * The main program.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class Program {
	/**
	 * All messages that will be published on the board.
	 */
	private static String [][] messages = {{"11"}, {"4a", "5as", "6"}, {"af7", "5as", "82323", "129"}, {"123230", "11", "12a"}};

	/**
	 * The main method. We create a bulletin board, then a person
	 * for each sub-array of messages, then start the thread.
	 * 
	 * @param args String[]
	 */
	public static void main(String [] args){
		BulletinBoard board = new BulletinBoard();

		for(String[] messagesOfOnePerson: messages){
			Person p = new Person(board, messagesOfOnePerson);
			p.start();
		}
	}
}