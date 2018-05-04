package Blatt03;

import javax.swing.*;
import java.awt.*;

public class Aufgabe01 {

    final static int HEIGHT = 200;
    final static int WIDTH = 300;

    final static String POEM = "<html> <p>" +
            "    Wer reitet so spät durch Nacht und Wind? <br>" +
            "    Es ist der Vater mit seinem Kind,<br>" +
            "    Er hat den Knaben wohl in dem Arm, <br>" +
            "    Er faßt ihn sicher, er hält ihn warm.<br>" +
            "</p></html>";

    public Aufgabe01(){
        JFrame window = new JFrame("Erlkoenig");
        window.setSize(WIDTH,HEIGHT);

        JLabel label = new JLabel(POEM);

        window.getContentPane().add(label);

        WindowHandler windest = new WindowHandler();
        window.addWindowListener(windest);

        window.setVisible(true);
    }
    public static void main(String[]args){
       new Aufgabe01();

    }
}


