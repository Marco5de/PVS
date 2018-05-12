package uebung_4;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Solution to exercise 2.
 * The ImageViewer class is the main/control class,
 * implementing {@link WindowAdapter} and {@link Actionlistener}.
 * It has access to the buttons in the UI class,
 * and loads pictures or requests to show error messages.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */

public class ImageViewer extends WindowAdapter implements ActionListener{
	/**
	 * The separate ui object.
	 */
	private ImageViewerUI ui = new ImageViewerUI();

	/**
	 * Create the image viewer.
	 */
	private ImageViewer() {
		// Listen to window events.
		ui.addWindowListener(this);

		// Listen to the button events.
		JButton [] buttons = ui.getButtons();
		for(JButton b: buttons)
			b.addActionListener(this);
	}

	/**
	 * Create a new ImageViewer.
	 * @param args String []
	 */
	public static void main(String [] args) {
		new ImageViewer();
	}

	/**
	 * Handle a button press in the UI (a request
	 * to show a new picture).
	 * @param argo {@link ActionEvent}
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// If a picture should be shown from a local file...
		String command = arg0.getActionCommand();
		if(command.equals("Bild aus Datei laden")) {
			try {
				/*
				 * ... request a valid file from the UI.
				 * If we get it, we create a BufferedImage from
				 * it and tell the UI to show it.
				 */
				File file = ui.requestFile();
				if(file != null) {
					BufferedImage image = ImageIO.read(file);
					ui.setPicture(image);
				}
			}
			/* Should something go wrong, the UI will also
			 * show the error message. */
			catch(Exception e) {
				ui.reportError(e.toString());
			}
		}
		// If a remote file from a URL is requested instead...
		else {
			try {
				/*
				 * ...request a valid URL. If we get it,
				 * we create an URL object, then a BufferedImage
				 * with the URL as its source, and show this picture then.
				 */
				String url = ui.requestURL();
				if(!url.equals("no URL set")) {
					URL adress = new URL(url);
					BufferedImage image = ImageIO.read(adress);
					ui.setPicture(image);
				}
			}
			/* This could go wrong to. */
			catch(Exception e) {
				ui.reportError(e.getMessage());
			}
		}
	}

	@Override
	/**
	 * When the UI window is closed, we end the program.
	 */
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}