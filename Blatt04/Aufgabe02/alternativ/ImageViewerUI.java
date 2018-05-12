package uebung_4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * Solution to exercise 2.
 * The ImageViewerUI extends JFrame. It can show a picture and
 * its thumbnail. Also, it can ask for a URL, a (picture) file,
 * and show an error message.
 * It has no methods of handling action events itself. Instead,
 * it gives the ImageViewer access to its buttons.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class ImageViewerUI extends JFrame{
	private static final long serialVersionUID = 1L;
	/**
	 * With these JButtons, we can choose to select a new picture.
	 * The ImageViewer gets them in the program.
	 */
	private JButton [] listeners = {new JButton("Bild aus Datei laden"),
			new JButton("Bild aus URL laden")
	};
	/**
	 * "Hooks" for the JPanels which will show the picture
	 * and the thumbnail.
	 */
	private JPanel picture = new JPanel(),
			thumbnail = new JPanel();

	/**
	 * Create the UI.
	 */
	public ImageViewerUI() {
		// Set up the window. Use the BorderLayout.
		setTitle("Bilder aus Date und aus dem WWW laden...");
		setSize(400, 200);
		setLayout(new BorderLayout());

		// The southern JPanel contains the JButtons in a FlowLayout.
		JPanel input = new JPanel();
		input.setLayout(new FlowLayout(FlowLayout.LEFT));
		input.add(listeners[0]);
		input.add(listeners[1]);
		getContentPane().add(input, BorderLayout.SOUTH);

		/* The center JPanel contains a JLabel for text and a JScrollPane.
		 * The scroll pane houses the JPanel which shows the original picture. */
		JPanel pictureContainer = new JPanel(new BorderLayout());
		pictureContainer.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		pictureContainer.add(new JLabel("Ganzes Bild"), BorderLayout.NORTH);
		picture.setLayout(new BorderLayout());
		JScrollPane p = new JScrollPane(picture);
		pictureContainer.add(p, BorderLayout.CENTER);
		getContentPane().add(pictureContainer, BorderLayout.CENTER);

		/* The western JPanel houses the thumbnail picture,
		 * and the JLabel describing it. */
		thumbnail.setLayout(new BorderLayout());
		thumbnail.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		thumbnail.add(new JLabel("Thumbnail"), BorderLayout.NORTH);
		thumbnail.add(new JLabel(), BorderLayout.CENTER);
		getContentPane().add(thumbnail, BorderLayout.WEST);

		setVisible(true);
	}

	/**
	 * Return the JButtons the main class needs to listen to.
	 * @return listeners JButton []
	 */
	public JButton [] getButtons() {
		return listeners;
	}

	/**
	 * Opens a JFileChooser, from which the user selects
	 * a picture file to be shown.
	 * @return a File object
	 */
	public File requestFile() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Bild öffnen");
		int response = fc.showOpenDialog(this);

		// Only return a valid file if the user chooses to.
		// Otherwise, return null.
		if(response == JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile();
		else
			return null;
	}

	/**
	 * Opens a JDialog with a text field, in which the user
	 * can type a URL string where a image can be found.
	 * @return String
	 */
	public String requestURL() {
		String response = JOptionPane.showInputDialog(this, "Geben Sie eine URL ein:                                                         ", "Adresse eingeben", JOptionPane.INFORMATION_MESSAGE);
		// If no valid string is typed,
		// return a special string to signal that.
		if(response == null)
			response = "no URL set";
		return response;
	}

	/**
	 * Shows the selected image on the window,
	 * as well as its thumbnail.
	 * @param image BufferedImage
	 */
	public void setPicture(BufferedImage image) {
		/* Get the picture size. 
		 * Create a reference to the new thumbnail. */
		int height = image.getHeight(),
				width = image.getWidth();
		BufferedImage resized = image;

		/*
		 * If it is too big to be shown in a thumbnail,
		 * the picture will be resized. First, calculate the scale,
		 * then the new picture dimensions.
		 * Create a Graphics2D drawer which then does the resizing.
		 */
		if(height > 150 || width > 150) {
			double scale = (Math.max(height, width)/ 150d);
			int newHeight = new Double(height/scale).intValue();
			int newWidth = new Double(width/scale).intValue();

			resized = new BufferedImage(newWidth, newHeight, image.getType());
			Graphics2D drawer = resized.createGraphics();
			drawer.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
			drawer.dispose();
		}

		// Remove the old picture and replace it with the new one.
		picture.removeAll();
		picture.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
		// Do the same with the thumbnails.
		thumbnail.remove(1);
		thumbnail.add(new JLabel(new ImageIcon(resized)));

		// Pack the window to show the new pictures properly.
		// As a side effect, the window is resized.
		pack();
		if(getHeight() < 200 || getWidth() < 400)
			setSize(400, 200);
	}

	/**
	 * Opens a JDialog which shows a error message.
	 * @param message String.
	 */
	public void reportError(String message) {
		JOptionPane.showMessageDialog(this, message, "Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
	}
}