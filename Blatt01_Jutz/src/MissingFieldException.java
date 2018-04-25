package uebung_1;

/**
 * MissingFieldException is a subclass of the Exception class,
 * used by the Field class in this project.
 * 
 * @author Jutz Benedikt
 * @version 20.4.2018
 */

public class MissingFieldException extends Exception {
	private static final long serialVersionUID = 4989316085205496507L;

	/**
	 * Creates a new MissingFieldException.
	 * @param cause String
	 * When the exception is thrown, cause will be printed on the terminal.
	 */
	public MissingFieldException(String cause) {
		super(cause);
	}
}