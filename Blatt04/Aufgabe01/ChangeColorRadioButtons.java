package Blatt04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Benedikt Jutz
 * @author Marco Deuscher
 *
 * In der Klasse wird ein Dialogfenster mit drei RadioButtons implementiert
 * Wird einer der RadioButtons gedrückt wechselt der Hintergrund die Farbe
 */
public class ChangeColorRadioButtons extends JFrame implements ActionListener {
    private JFrame dialog;

    /**
     * erstellen der GUI
     */
    public ChangeColorRadioButtons() {
        dialog = new JFrame();
        dialog.setTitle("Dialog with Radio Buttons");
        dialog.setSize(300, 300);
        FlowLayout flowLayout = new FlowLayout();
        dialog.addWindowListener(new WindowCloser());
        dialog.setLayout(flowLayout);
        dialog.getContentPane().setBackground(Color.black);

        JRadioButtonMenuItem red = new JRadioButtonMenuItem("red");
        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("blue");
        JRadioButtonMenuItem green = new JRadioButtonMenuItem("green");
        red.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);

        /**
         * Zusammenfassen der RadioButtons in einer Gruppe
         * dadurch muss sich nicht mehr um Status der einzelnen Buttons gekuemmert werden
         */

        ButtonGroup group = new ButtonGroup();
        group.add(red);
        group.add(blue);
        group.add(green);


        dialog.getContentPane().add(red);
        dialog.getContentPane().add(blue);
        dialog.getContentPane().add(green);


        dialog.setVisible(true);
    }


    public static void main(String[] args) {
        new ChangeColorRadioButtons();
    }

    /**
     * Prueft welcher Button gedrueckt wurde und fuehrt entsprechende Aktion aus
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("red")){
            dialog.getContentPane().setBackground(Color.RED);
        }
        else if (e.getActionCommand().equals("green"))
            dialog.getContentPane().setBackground(Color.green);
        else if (e.getActionCommand().equals("blue"))
            dialog.getContentPane().setBackground(Color.blue);

    }
}

/**
 * In der Klasse wird die abstrakte Klasse WindowAdaptet implementiert um den Prozess beim schließen des Fensters zu beenden
 */
final class WindowCloser extends WindowAdapter{
    @Override
    public void windowClosing(WindowEvent e){
        System.out.println("Closed");
        System.exit(0);
    }

}
