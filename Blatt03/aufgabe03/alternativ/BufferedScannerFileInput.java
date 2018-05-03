package Blatt03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * In dieser Klasse wird anstatt eines Scanners ein Buffered Reader benutzt
 * um die Zahlen in der vorgebeben .txt Datei aufzusummieren
 *
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */

public class BufferedScannerFileInput {
    public static void main(String[] args) {
        long total = 0;
        long millis = System.currentTimeMillis();

        try {
            String currentLine = "";
            //Dateipfad
            File file = new File("C:\\Users\\marco\\Dropbox\\Uni\\Semester02\\PVS\\Übungen\\src\\Blatt03\\bigInput.txt");

            //erstellt BufferedReader Objekt
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //liest bis zur letzten Zeile, speichert jede Zeile in String
            //konvertiert zu Integer und summiert auf
            while ((currentLine = reader.readLine()) != null){
                total += Integer.parseInt(currentLine);
            }
            millis = System.currentTimeMillis() - millis;
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Die Gesamtsumme ist: " + total + "\n" + "Das Programm hat " + ((double)millis/1000) + " Sekunden benötigt.");
    }
}
