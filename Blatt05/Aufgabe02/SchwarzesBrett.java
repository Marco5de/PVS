package Blatt05;


/**
 * Klasse SchwarzesBrett stellt zu Beobachtendes Objekt dar
 *
 *  * @author Benedikt Jutz
 *  * @author Marco Deuscher
 */
public class SchwarzesBrett extends java.util.Observable {
    private String message;

    void changeMessage(String s){
        this.message = s;
        setChanged();
        notifyObservers("Die Nachricht wurde geändert: \n" + s);
    }


}
