package uebung_1;

/**
 * The Interface GeomCalculation is used for reading
 * the area and perimeter of a geometric shape.
 * 
 * @author Jutz Benedikt
 * @version 20.4.2018
 */

public interface GeomCalculation {
	/**
	 * Returns the area of the shape.
	 * @return double
	 */
	double getArea();

	/**
	 * Returns the perimeter of the shape.
	 * @return double
	 */
	double getPerimeter();
}