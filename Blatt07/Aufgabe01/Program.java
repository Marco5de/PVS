package Blatt07;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;


/**
 * Demoklasse zur Serialisierung der Objekte
 * 
 *  @author Benedikt Jutz
 *  @author Marco Deuscher
 */
public class Program {
    /**
     * In dieser Klasse werden die zuvor implementierten Objekte serialisiert
     * Hierfür wurde in Car das Serializeable Interface implementiert
     * @param args
     */
    public static void main(String[]args){

        Mercedes c63 = new Mercedes("CR-MD-5","27.02.2015",4,4,3,"Mercedes C63 AMG",5);
        Porsche gr3_911 = new Porsche("CR-GT-911","24.06.2016",2,4,3,"Porsche 911 GT3",479);

        /*
            Im Folgenden werden die Objekte in XML-Datei serialisiert
         */
        XMLEncoder encoder = null;
        XMLDecoder decoder = null;
        //instanziiert XML Encoder
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("SaveCarData.xml")));
        }
        catch (IOException e){
            System.out.println("Error opening File");
        }
        //schreibt Objekte in xml-Datei
        encoder.writeObject(c63);
        encoder.writeObject(gr3_911);
        encoder.close();

        //instanziiert decoder
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("SaveCarData.xml")));
        }
        catch (FileNotFoundException e){
            System.out.println("Error: File not found");
        }
        //auslesen und Ausgabe auf der Konsole
        Mercedes xmlMercedes = (Mercedes) decoder.readObject();
        Porsche xmlPorsche = (Porsche) decoder.readObject();
        System.out.println(xmlMercedes);
        System.out.println("\n" + xmlPorsche);

        decoder.close();


        //BINARY SERIALIZATION

        /*
            Oeffnet Outputstream und versucht Objekte in .ser Datei zu schreiben
         */
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SaveCarData.ser"));
            out.writeObject(c63);
            out.writeObject(gr3_911);
            out.close();
        }
        catch (IOException e){
            System.out.println("Error openig file");
        }

        Mercedes deserializedMercedes = null;
        Porsche deserialziedPorsche = null;
        /*
            Versucht aus .ser Datei Objekte einzulesen und zu entsprechenden Typ zu casten
         */
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("SaveCarData.ser"));
            deserializedMercedes = (Mercedes)inputStream.readObject();
            deserialziedPorsche = (Porsche) inputStream.readObject();
            inputStream.close();

        }

        catch(IOException e){
            System.out.println("Error opening File");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error finding Class");
        }

        //Ausgabe der Objekte
        System.out.println("\n"+deserializedMercedes);
        System.out.println("\n" + deserialziedPorsche);
    }

}
