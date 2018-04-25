package uebung_1;

import java.util.LinkedList;

/**
 * ProgramGenerics behaves similarly like {@link Program}, but
 * uses a LinkedList instead of an array to store shapes.
 * Only the differences to {@link Program} are mentioned here.
 * 
 * @author Jutz Benedikt
 * @version 20.4.2018
 */
public class ProgramGenerics {
	/**
	 * The linked list which replaces the array.
	 */
	private static LinkedList<GeomCalculation> geomObjects = new LinkedList<>();

	/**
	 * @see Program
	 * @param args
	 */
	public static void main(String [] args) {
		// Store the shapes in the list.
		int numOfObjects = (int) (Math.random() * 15 + 10);
		for(int i = 0; i < numOfObjects; i++) {
			int side = (int) (Math.random() * 20 + 1);
			if(Math.random() < 0.5)
				geomObjects.add(new Circle(side));
			else
				geomObjects.add(new Pentagon(side));
		}

		// Use a for-each loop to sum up.
		double sumArea = 0, sumPerimeter = 0;
		for(GeomCalculation figure: geomObjects) {
			System.out.println(figure.toString());
			sumArea += figure.getArea();
			sumPerimeter += figure.getPerimeter();
		}

		System.out.println("Total sum of all areas: "+sumArea
				+"\nTotal sum of all perimeters: "+sumPerimeter);
	}
}