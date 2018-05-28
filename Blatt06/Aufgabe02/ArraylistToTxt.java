package Blatt06;


import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Im Konstruktor wird eine Arraylist vom Typ String erzeugt und kann anschließend in eine Datei gespeichert werden
 * Jedes Element der Arraylist wird in eine neue Zeile geschrieben
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */
public class ArraylistToTxt {
    private ArrayList <String> arrList = new ArrayList<>();

    /**
     * Im Konstruktor wird ArrayList aus String Array erstellt
     * @param arr
     */
    public ArraylistToTxt(String[] arr){
        for(String x : arr)
            arrList.add(x);
    }

    /**
     * Fügt der ArrayList weiteren String zu
     * @param str
     */
    public void addString(String str){
        arrList.add(str);
    }

    /**
     * Fügt der ArrayList StringArray an
     * @param strarr
     */
    public void addStringArr(String[] strarr){
        for(String x : strarr)
            arrList.add(x);
    }

    /**
     * schreibt die ArrayList in eine .txt Datei
     * Jeder Eintrag der ArrayList wird in eine neue Zeile geschreben
     */
    public void toTxt(){
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream("ArrListContent.txt"));
        }
        catch (FileNotFoundException e){
            System.out.println("Error opening file");
            System.exit(0);
        }
        for(String x : arrList)
            outputStream.println(x);
        outputStream.close();
    }


    public void txtToConsole(String path){
        try {
            txtToConsole(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file");
        }
    }

    /**
     * Bekommt eine Datei, erstellt einen inputStream und schreibt den Inhalt der Datei auf die Konsole
     * @param path
     */
    public void txtToConsole(FileReader path){
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(path);
            String currentLine = null;
            while((currentLine= inputStream.readLine())!= null){
                System.out.println(currentLine);
            }
        }
        catch(IOException e){
            System.out.println("Error reading from file");
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Error closing input Stream");
        }
    }
}
