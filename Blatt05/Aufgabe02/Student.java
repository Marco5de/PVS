package Blatt05;

import java.util.Observable;
import java.util.Observer;

/**
 * Klasse Student stellt Beobachter dar
 *
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */
public class Student implements Observer {
    private String name;

    public String getName() {
        return name;
    }

    public Student(String name){
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }
}
