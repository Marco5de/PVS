package Blatt05;


/**
 * Klasse stellt eine Nachricht dar die auf dem Blackboard dargestellt werden kann
 *
 *  @author Benedikt Jutz
 *  * @author Marco Deuscher
 */
public class Message {
    private String name;
    private String text;
    public String message;

    public Message(){

    }
    public Message(String name, String message){
        this.name = name;
        this.text = message;
        this.formatMessage();
    }

    String formatMessage(){
        this.message = text + "\n" + "- " + this.name;
        //System.out.println(this.message);
        return this.message;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
