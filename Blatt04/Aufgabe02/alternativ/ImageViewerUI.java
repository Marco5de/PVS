package uebung_5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageViewerUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton [] listeners = {new JButton("Bild aus Datei laden"),
			new JButton("Bild aus URL laden")
	};
	private JPanel picture = new JPanel();

	public ImageViewerUI() {
		setTitle("Image Viewer");
		setSize(600, 600);
		setLayout(new BorderLayout());

		JPanel input = new JPanel();
		input.setLayout(new FlowLayout(FlowLayout.LEFT));
		input.add(listeners[0]);
		input.add(listeners[1]);
		getContentPane().add(input, BorderLayout.SOUTH);

		JPanel pictureContainer = new JPanel(new BorderLayout());
		pictureContainer.add(new JLabel("Bild"), BorderLayout.NORTH);
		JScrollPane p = new JScrollPane(picture);
		pictureContainer.add(p, BorderLayout.CENTER);
		getContentPane().add(pictureContainer, BorderLayout.CENTER);

		JPanel thumbnail = new JPanel();
		thumbnail.setLayout(new BorderLayout());
		thumbnail.add(new JLabel("Thumbnail"), BorderLayout.NORTH);
		getContentPane().add(thumbnail, BorderLayout.WEST);

		setVisible(true);
	}

	public JButton [] getButtons() {
		return listeners;
	}

	public File requestFile() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Bild öffnen");
		int response = fc.showOpenDialog(this);
		if(response == JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile();
		else
			return null;
	}

	public void setPicture(BufferedImage image) {
		picture.removeAll();
		picture.add(new JLabel(new ImageIcon(image)));
	}

	public void reportError(String message) {
		JOptionPane.showMessageDialog(this, message, "Error message", JOptionPane.ERROR_MESSAGE);
	}

	public String requestURL() {
		return null;
	}
}