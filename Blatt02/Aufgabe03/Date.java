package uebung_2;

import java.time.LocalDate;

/**
 * Implementation of a),b) and c)
 * prints today's date
 * prints the date four weeks ago and the date one month ago
 * prints the associated day of the week
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class Date {
    public static void main(String[]args){
        LocalDate date = LocalDate.now();
        System.out.println(date);

        //the two dates differ, because four weeks ago ago is equivalent to:
        // todays date - 4 weeks = today's date - 28 days
        //todays date - 1 month = day - 0; month -1
        System.out.println(date.minusWeeks(4));
        System.out.println(date.minusMonths(1));

        System.out.println(date.minusWeeks(4).getDayOfWeek());
        System.out.println(date.minusMonths(1).getDayOfWeek());

    }
}
