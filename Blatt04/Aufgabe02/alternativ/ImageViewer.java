package uebung_5;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class ImageViewer extends WindowAdapter implements ActionListener{
	private ImageViewerUI ui = new ImageViewerUI();

	public ImageViewer() {
		ui.addWindowListener(this);
		JButton [] buttons = ui.getButtons();
		for(JButton b: buttons)
			b.addActionListener(this);
	}

	public static void main(String [] args) {
		new ImageViewer();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if(command.equals("Bild aus Datei laden")) {
			try {
				File file = ui.requestFile();
				if(file != null) {
					BufferedImage image = ImageIO.read(file);
					ui.setPicture(image);
				}
			}
			catch(Exception e) {
				ui.reportError(e.toString());
			}
		}
		else {
			try {
				String url = ui.requestURL();
				if(!url.equals("no URL set")) {
					URL adress = new URL(url);
					BufferedImage image = ImageIO.read(adress);
					ui.setPicture(image);
				}
			}
			catch(Exception e) {
				ui.reportError(e.toString());
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}