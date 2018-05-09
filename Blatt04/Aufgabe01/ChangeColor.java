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
 * In der Klasse wird ein Dialogfenster mit einer Combobox implementiert
 * Wird ein EIntrag der Combobox gewaehlt wird die Farbe des Hintergrunds entsprechend angepasst
 */
public class ChangeColor extends JFrame implements ActionListener {
    private JFrame dialog;
    private JComboBox comboBox = new JComboBox();

    /**
     * erstellen der GUI
     */
    public ChangeColor() {
        dialog = new JFrame();
        dialog.setSize(300, 300);
        dialog.setTitle("Dialog with Combobox");
        FlowLayout flowLayout = new FlowLayout();
        dialog.addWindowListener(new WindowCloser());
        dialog.setLayout(flowLayout);
        dialog.getContentPane().setBackground(Color.black);

        comboBox.addItem("red");
        comboBox.addItem("blue");
        comboBox.addItem("green");
        comboBox.addActionListener(this);

        dialog.getContentPane().add(comboBox);




        dialog.setVisible(true);
    }


    public static void main(String[] args) {
        new ChangeColor();
    }
    /**
     * Prueft welcher Button gedrueckt wurde und fuehrt entsprechende Aktion aus
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBox.getSelectedItem().toString().equals("red")){
            dialog.getContentPane().setBackground(Color.RED);
        }
        else if (comboBox.getSelectedItem().toString().equals("green"))
            dialog.getContentPane().setBackground(Color.green);
        else if (comboBox.getSelectedItem().toString().equals("blue"))
            dialog.getContentPane().setBackground(Color.blue);

    }
}

