package Blatt06;


import java.io.*;

/**
 * Gegebener String wird nach Vorschrift in CSV Datei geschrieben
 * CSV Datei kann auf der Konsole ausgegeben werden
 *
 * @author Benedikt Jutz
 * @author Marco Deuscher
 */
public class StringToCSV {
    private static final String DEFAULT_SEP = ",";
    private static final String NEWLINE_SEP = "\n";

    private String[][]input;
    public StringToCSV(String str){
        this.input = toArray(str);
    }

    /**
     * Aufteilen des Arrays in zweidimensionales Array
     * @param str
     * @return
     */
    private String[][] toArray(String str){
        String [][]output = new String[3][3];
        String[]rows = str.split(";");

        String[] columns;
        for(int i=0; i<output.length;i++){
            columns = rows[i].split(" ");
            for(int j = 0; j< output[i].length ; j++){
                if(j<columns.length)
                    output[i][j] = columns[j];

            }
        }
        return output;
    }

    /**
     * Schreiben des Arrays in CSV file
     * Darstellung in Excel nicht korrekt
     * In sonstigen CSV Editoren korrekte Darstellung
     */
    public void toCSV(){
        PrintWriter outputStream = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            outputStream = new PrintWriter(new FileOutputStream("CSVTable.csv"));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        for(int i = 0; i< input.length;i++){
            for(int j = 0; j< input[i].length;j++) {
                outputStream.append(input[i][j]);
                outputStream.append(DEFAULT_SEP);
            }
            outputStream.append(NEWLINE_SEP);
        }
        outputStream.flush();
        outputStream.close();
    }

    public void csvToConsole(String path){
        try {
            csvToConsole(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening File");
        }
    }

    /**
     * Schreibt den Inhalt der gegebene Datei auf die Konsole
     * @param path
     */
    public void csvToConsole(FileReader path){
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(path);
            String currentLine;
            while ((currentLine = inputStream.readLine()) != null) {
                String[] arr = currentLine.split(DEFAULT_SEP);
                for(String x : arr)
                    System.out.print(x +" ");
                System.out.println();
            }
        }
        catch (IOException e){
            System.out.println("Error reading file");
        }
    }
}
