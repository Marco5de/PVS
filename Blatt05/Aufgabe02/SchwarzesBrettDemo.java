package Blatt05;

public class SchwarzesBrettDemo {

    /**
     * Main zum testen des Observer Patterns
     * @param args
     */
    public static void main(String[]args){
        SchwarzesBrett blackboard = new SchwarzesBrett();
        Student bob = new Student("Bob");
        blackboard.addObserver(bob);

        blackboard.changeMessage("Neue Nachricht");
    }
}
