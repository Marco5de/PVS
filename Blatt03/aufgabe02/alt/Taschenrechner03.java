package Blatt03;

import javax.swing.*;
import java.awt.*;

/**
 * Bauen der graphischen Oberfläche des Taschenrechners mit einem BorderLayout
 * BorderLayout enthält nochmals GridLayout zum anordnen der Buttons
 *
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */

public class Taschenrechner03 extends JFrame{
    /**
     * Erstellt neues Fenster
     */
    public Taschenrechner03(){
        JFrame window = new JFrame("calc using borderLayout ");
        Dimension dim = new Dimension(100,200);
        window.setSize(dim);
        WindowHandler handler = new WindowHandler();
        window.addWindowListener(handler);

        BorderLayout border = new BorderLayout();
        border.setHgap(5);
        border.setVgap(5);
        window.setLayout(border);

        JTextField tf01 = new JTextField("0");
        tf01.setPreferredSize(new Dimension(200,20));

        //adding Textfield to BorderLayout NORTH
        window.getContentPane().add(tf01,BorderLayout.NORTH);

        //Adding keyboard to BorderLayout Center
        window.getContentPane().add(getGrid(),BorderLayout.CENTER);

        window.setVisible(true);
    }

    //creates new instance of Taschenrechner03 --> opens new window
    public static void main(String[]args){
        new Taschenrechner03();
    }

    /**
     * returns the keyboard inside a panel
     * @return keyboard as JPanel
     */
    private JPanel getGrid(){
        JPanel inner = new JPanel();
        //erstellt neues GridLayout
        GridLayout grid = new GridLayout(4,4);
        grid.setHgap(5);
        grid.setVgap(5);
        inner.setLayout(grid);

        //erstellt Buttons und fügt sie in gridLayout ein

        JButton button01 = new JButton("/");
        JButton button02 = new JButton("1");
        JButton button03 = new JButton("2");
        JButton button04 = new JButton("3");
        JButton button05 = new JButton("*");
        JButton button06 = new JButton("4");
        JButton button07 = new JButton("5");
        JButton button08 = new JButton("6");
        JButton button09 = new JButton("+");
        JButton button10 = new JButton("7");
        JButton button11 = new JButton("8");
        JButton button12 = new JButton("9");
        JButton button13 = new JButton("-");
        JButton button14 = new JButton("0");
        JButton button15 = new JButton("=");
        JButton button16 = new JButton("C");

        inner.add(button01);
        inner.add(button02);
        inner.add(button03);
        inner.add(button04);
        inner.add(button05);
        inner.add(button06);
        inner.add(button07);
        inner.add(button08);
        inner.add(button09);
        inner.add(button10);
        inner.add(button11);
        inner.add(button12);
        inner.add(button13);
        inner.add(button14);
        inner.add(button15);
        inner.add(button16);


        return inner;
    }
}
