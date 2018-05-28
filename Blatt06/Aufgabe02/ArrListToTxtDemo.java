package Blatt06;


/**
 * Klasse zum Testen der ArraylistToTxt Klasse
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */
public class ArrListToTxtDemo {
    public static void main(String[]args){
        //Demo zu Aufgabe 2
        String[]arr = {"Es werden ", "der Arraylist", "mehrere Strings", "zum testen", "gegeben"};
        ArraylistToTxt test = new ArraylistToTxt(arr);

        test.addString("und noch einer");
        test.toTxt();

        test.txtToConsole("C:/Users/marco/Dropbox/Uni/Semester02/PVS/Übungen/ArrListContent.txt");


        //Demo zu Aufgabe 3
        StringToCSV testcsv = new StringToCSV("Josef Ulm josef@ulm.de;Flo Kuchen flo@ulm.de;Jens" +
                " Senden jens@ulm.de");

        testcsv.toCSV();
        testcsv.csvToConsole("C:/Users/marco/Dropbox/Uni/Semester02/PVS/Übungen/CSVTable.csv");

    }
}
