package uebung_5;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;

public class ImageViewerUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton [] listeners = {new JButton("Bild aus Datei laden"),
			new JButton("Bild aus URL laden")
	};
	private JPanel picture = new JPanel(),
			thumbnail = new JPanel();

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

		thumbnail.setLayout(new BorderLayout());
		thumbnail.add(new JLabel("Thumbnail"), BorderLayout.NORTH);
		thumbnail.add(new JLabel(), BorderLayout.CENTER);
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
		double scale = 0;
		int height = image.getHeight(),
				width = image.getWidth();
		scale = (Math.max(height, width)/ 150d);
		int newHeight = new Double(height/scale).intValue();
		int newWidth = new Double(width/scale).intValue();

		BufferedImage resized = new BufferedImage(newWidth, newHeight, image.getType());
		Graphics2D drawer = resized.createGraphics();
		drawer.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
		drawer.dispose();

		picture.removeAll();
		picture.add(new JLabel(new ImageIcon(image)));
		thumbnail.remove(1);
		thumbnail.add(new JLabel(new ImageIcon(resized)));
		pack();
	}

	public void reportError(String message) {
		JOptionPane.showMessageDialog(this, message, "Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
	}

	public String requestURL() {
		String response = JOptionPane.showInputDialog(this, "Geben Sie eine URL ein:                                                         ", "Adresse eingeben", JOptionPane.INFORMATION_MESSAGE);
		if(response == null)
			response = "no URL set";
		return response;
	}
}