package uebung_2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.HashSet;

/**
 * Implementation of parts b and c of exercise 2.
 * 
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class CollectionsExample {
	public static void main(String [] args){
		/*
		 * Part 2b):
		 * - Create a LinkedList.
		 * - Fill it with seven dates (and others).
		 * - Sort it and print its elements.
		 */

		LinkedList<MyDate> dates = new LinkedList<MyDate>();
		dates.add(new MyDate(2, 3, 44));
		dates.add(new MyDate(2, 3, 2044));
		dates.add(new MyDate(2, 3, 441));
		dates.add(new MyDate(2, 3, 494));
		dates.add(new MyDate(2, 3, 844));
		dates.add(new MyDate(22, 3, 44));
		dates.add(new MyDate(2, 3, 44));
		
		// Other MyDate elements.
		dates.add(new MyDate(27, 4, 2016));
		dates.add(new MyDate(7, 5, 344));

		/*
		 * Use Collections.sort() to sort the linked list.
		 */
		Collections.sort(dates);

		for(MyDate date: dates)
			System.out.println(date.toString());
		System.out.println();


		/*
		 * Part 2c):
		 * - Insert all elements of dates into the HashedSet dates2.
		 * - Print of any duplicate dates found.
		 */
		HashSet<MyDate> dates2 = new HashSet<MyDate>();
		for(MyDate dateToBeInserted: dates){
			if(!dates2.add(dateToBeInserted)){
				System.out.println("Duplicate detected: "+dateToBeInserted);
			}
		}
	}
}
