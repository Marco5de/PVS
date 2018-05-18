package Blatt05;

/**
 * Controller für die View
 *
 *  @author Benedikt Jutz
 *  * @author Marco Deuscher
 */

public class Controller extends java.util.Observable {

    public Controller(){
    }

    String returnMessage(String name, String message){
        this.addObserver(new Student(name));
        setChanged();
        notifyObservers("Nachricht wurde geupdated: " + message);
        return new Message(name,message).formatMessage();
    }

}
