package uebung_1;

/**
 * Program creates random instances of circles and pentagons,
 * then sums up their perimeters and areas, and prints them both
 * to the console.
 * 
 * @author Jutz Benedikt
 * @version 20.4.2018
 */
public class Program {
	/**
	 * The array which stores the geometric objects.
	 */
	private static GeomCalculation [] geomObjects;

	/**
	 * Execute the main program.
	 * @param args
	 */
	public static void main(String [] args) {
		/*
		 * Create a random number of shapes. Decide randomly
		 * what side length/radius they have, and whether they
		 * become circles or pentagons.
		 */
		int numOfObjects = (int) (Math.random() * 15 + 10);
		geomObjects = new GeomCalculation[numOfObjects];
		for(int i = 0; i < numOfObjects; i++) {
			int side = (int) (Math.random() * 20 + 1);
			if(Math.random() < 0.5)
				geomObjects[i] = new Circle(side);
			else
				geomObjects[i] = new Pentagon(side);
		}

		/*
		 * Go through the shape array, and sum up perimeters and areas.
		 * Also print information about the created objects.
		 */
		double sumArea = 0, sumPerimeter = 0;
		for(int i = 0; i < geomObjects.length; i++) {
			System.out.println(geomObjects[i].toString());
			sumArea += geomObjects[i].getArea();
			sumPerimeter += geomObjects[i].getPerimeter();
		}

		// Print the sums to the console.
		System.out.println("Total sum of all areas: "+sumArea
				+"\nTotal sum of all perimeters: "+sumPerimeter);
	}
}