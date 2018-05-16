package Blatt04;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowCloser extends WindowAdapter {
    @Override
    public void windowActivated(WindowEvent e){
      System.out.println("Geoeffnet");
  }

  @Override
  public void windowClosing(WindowEvent e){
        System.out.println("Geschlossen");
      System.exit(0);
  }
}
