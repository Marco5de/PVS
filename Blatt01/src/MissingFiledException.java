
public class MissingFiledException extends Exception {
    MissingFiledException() {
		super("Das eingegebene Feld existiert nicht");
	}
}