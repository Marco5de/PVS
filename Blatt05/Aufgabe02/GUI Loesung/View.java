package Blatt05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI für das Blackboard
 *
 *  @author Benedikt Jutz
 *  * @author Marco Deuscher
 */

public class View extends JFrame implements ActionListener{

    private Controller controller;

    private JFrame window;
    private JMenuBar menuBar;
    private JMenu options;
    private JMenuItem login;
    private JPanel center;
    private JTextArea[] arrArea = new JTextArea[5];

    public JFrame getWindow() {
        return window;
    }


    public View(Controller controller) {
        this.controller = controller;
        window = new JFrame();
        window.setTitle("Schwarzes Brett");
        window.setSize(600, 300);
        window.getContentPane().setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        options = new JMenu("Option");

        login = new JMenuItem(("Anmelden"));
        login.addActionListener(this);
        menuBar.add(options);
        options.add(login);
        center = new JPanel();
        center.setLayout(new FlowLayout());
        window.getContentPane().add(center);

        window.getContentPane().add(new JLabel("Schwartes Brett der Uni Ulm"), BorderLayout.NORTH);

        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void showInput() {
        String name;
        String mes;
        JDialog panel = new JDialog();
        JTextField tf1 = new JTextField();
        JTextArea tf2 = new JTextArea();
        tf1.setPreferredSize(new Dimension(200,20));
        tf2.setPreferredSize(new Dimension(200,100));
        panel.setVisible(true);
        panel.setLayout(new FlowLayout());
        panel.setSize(200,270);
        panel.add(new JLabel("Geben sie ihren Namen ein: "));
        panel.add(tf1);
        panel.add(new JLabel("Gebe sie ihre Nachricht ein"));
        panel.add(tf2);

        JButton submit = new JButton("Submit");
        panel.add(submit);
        submit.addActionListener(ae -> {addMessage(controller.returnMessage(tf1.getText(),tf2.getText()));
        panel.dispose();}
        );

    }

    void addMessage(String mes){
        center.removeAll();
        if(arrArea[arrArea.length-1] != null){
            for(int i=0; i<arrArea.length-1;i++){
                arrArea[i] = arrArea[i+1];
            }
            arrArea[arrArea.length-1] = null;
        }
        for(int i = 0; i < arrArea.length; i++){
            if(arrArea[i] == null) {
                arrArea[i] = new JTextArea(mes);
                break;
            }
        }
        for(int i= 0; i < arrArea.length; i++) {
            if(arrArea[i] != null)
                center.add(arrArea[i]);
        }
        window.pack();
        window.setSize(600,300);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Anmelden")) {
           showInput();
        }

    }
}


