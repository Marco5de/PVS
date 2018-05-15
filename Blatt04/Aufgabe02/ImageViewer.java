package Blatt04;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Aufrufen eines Bildes auf dem FileChooser System oder über eine URL
 * oeffnen des Bilds als kleines Thumbnail und die grosse Version mit ScrollBars falls das Bild größer als das Fenster ist
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */
public class ImageViewer extends JFrame implements ActionListener {
    private JPanel left;
    private JPanel center;
    JFrame window;

    ImageViewer (){
        window = new JFrame();
        window.setSize(500,500);
        window.setTitle("Bilder aus Datei und aus dem WWW laden...");
        window.addWindowListener(new WindowCloser());
        BorderLayout borderLayout = new BorderLayout();
        window.setLayout(borderLayout);

        center= new JPanel();
        left = new JPanel();
        JPanel down = new JPanel();
        window.add(center,BorderLayout.CENTER);
        window.add(left,BorderLayout.WEST);
        window.add(down,BorderLayout.SOUTH);

        left.setLayout(new BorderLayout());
        center.setLayout(new BorderLayout());

        Label label_left = new Label("Thumbnail");
        Label label_center = new Label("Ganzes Bild");
        Label dummyLeft = new Label();
        Label dummyCenter = new Label();

        left.add(label_left,BorderLayout.NORTH);
        center.add(label_center,BorderLayout.NORTH);
        left.add(dummyLeft,BorderLayout.CENTER);
        center.add(dummyCenter,BorderLayout.CENTER);

        JButton button01 = new JButton("Bild aus Datei laden");
        JButton button02 = new JButton("Bild aus URL laden");
        button01.addActionListener(this);
        button02.addActionListener(this);
        down.setLayout(new FlowLayout(FlowLayout.LEFT));
        down.add(button01);
        down.add(button02);


        window.setVisible(true);
    }
    /**
     * Bearbeiten der groesse eines Bildes fuer das Thumbnail
     * @param img zu skalierendes Bild
     * @param newWidth neue breite
     * @param newHeight neue hoehe
     * @return neues skaliertes Bild
     */
    public static BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
        Image tmp = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    /**
     * laesst den benutzer eine Datei aus dem FIleChooser waehlen
     * Falls es sich um eine Bilddatei handelt wird diese dann geoeffnet
     */
    public void getFile() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            BufferedImage image = null;
            try {
                image = ImageIO.read(selectedFile);

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, "Beim Einlesen ist ein Fehler aufgetreten", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Done");
            left.remove(1);
            left.add(new JLabel(new ImageIcon(resize(image,150,150))));

            JLabel image_label = new JLabel(new ImageIcon(image));
            JScrollPane ScrollPane = new JScrollPane(image_label);
            ScrollPane.setPreferredSize(new Dimension(500,500));
            center.remove(1);
            center.add(ScrollPane,BorderLayout.CENTER);
            ScrollPane.setVisible(true);
            window.pack();

        }
    }

    JTextField url_input;
    JDialog dialog;

    /**
     * oeffnet Dialogfenster zur Eingabe der URL
     */
    public void openDialog(){
        dialog = new JDialog();
        dialog.setSize(300,300);
        url_input = new JTextField("Geben sie die URL ein: ");
        url_input.setPreferredSize(new Dimension(200,40));
        url_input.setEditable(true);
        JButton enter_url = new JButton("Enter");
        dialog.setLayout(new FlowLayout());
        dialog.add(url_input);
        dialog.add(enter_url);
        enter_url.addActionListener(this);

        dialog.setVisible(true);
    }

    /**
     * oeffnet das Bild bei angegebener URL, fall es sich um ein Bild handelt
     * stellt das Bild dar, falls zu gross mit scrollBars
     */
    public void getURLImage(){
        BufferedImage image = null;
        try {
            URL url = new URL(url_input.getText());
            System.out.println(url_input.getText());
            image = ImageIO.read(url);

        } catch (IOException e) {
            System.out.println("Beim einlesen der URL ist ein Fehler aufgetreten!");
            JOptionPane.showMessageDialog(
                    null, "Beim Einlesen ist ein Fehler aufgetreten", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Done");
        left.remove(1);
        left.add(new JLabel(new ImageIcon(resize(image,150,150))));
        JLabel image_label = new JLabel(new ImageIcon(image));
        JScrollPane ScrollPane = new JScrollPane(image_label);
        ScrollPane.setPreferredSize(new Dimension(500,500));
        center.remove(1);
        center.add(ScrollPane,BorderLayout.CENTER);
        ScrollPane.setVisible(true);
        window.pack();
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getActionCommand().equals("Bild aus Datei laden")) {
            System.out.println("Bild aus Datei Laden");
            getFile();
        }
        else if (e.getActionCommand().equals("Bild aus URL laden")) {
            System.out.println("Bild von URL laden");
            openDialog();
        } else if (e.getActionCommand().equals("Enter")) {
            System.out.println("URL eingegeben");
            getURLImage();
            dialog.dispose();
        }


    }

    public static void main(String[]args){
        new ImageViewer();
    }
}