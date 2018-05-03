package Blatt03;

import java.io.File;
import java.util.Scanner;

/**
 * In dieser Klasse wird ein Scanner verwendet um die Summe der Zahlen in der vorgebebenen .txt Datei zu bilden
 *
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */
public class ScannerFileInput {
    public static void main(String[] args) {
        long total = 0;
        long millis = System.currentTimeMillis();
        try {
            //Dateipfad
            File file = new File("C:\\Users\\marco\\Dropbox\\Uni\\Semester02\\PVS\\Übungen\\src\\Blatt03\\bigInput.txt");

            //erstellt ein neues Scanner Objekt
            Scanner scan = new Scanner(file);

            //liest solange es eine nächste Zeile gibt
            while (scan.hasNext()) {
                total += scan.nextInt();
            }
            millis = System.currentTimeMillis() - millis;
            scan.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Die Gesamtsumme ist: " + total + "\n" + "Das Programm hat " + ((double)millis/1000) + " Sekunden benötigt.");
    }
}
