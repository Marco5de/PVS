package Blatt02;

import java.time.LocalDate;

public class Date {
    public static void main(String[]args){
        LocalDate date = LocalDate.now();
        System.out.println(date);


        //Sind unterschiedlich da vor vier Wochen heutigesDatum - 4 Wochen = heutigesDatum -28 Tage
        // und heutigesDatum - 1Monat 
        System.out.println(date.minusWeeks(4));
        System.out.println(date.minusMonths(1));

        System.out.println(date.minusWeeks(4).getDayOfWeek());
        System.out.println(date.minusMonths(1).getDayOfWeek());

    }
}
