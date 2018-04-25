package uebung_1;

/**
 * Pentagon implements the interface {@link GeomCalculation}, too.
 * @author Jutz Benedikt
 * @version 20.4.2018
 */

public class Pentagon implements GeomCalculation {
	/**
	 * The length l of a side
	 * of the pentagon.
	 */
	private int length;

	/**
	 * Creates a new pentagon.
	 * @param length int
	 */
	public Pentagon(int length) {
		this.length = length;
	}

	/**
	 * Implements the getArea() method.
	 * The area of a perimeter is
	 * (5*(l^2)*Math.tan(54 degrees))/4.
	 * @see GeomCalculation
	 */
	@Override
	public double getArea() {
		double radians = Math.toRadians(54);
		return (5*Math.pow(length, 2)*Math.tan(radians))/4;
	}

	/**
	 * Implements the getPerimeter() method.
	 * The perimeter of a pentagon is 5*l.
	 * @see GeomCalculation
	 */
	@Override
	public double getPerimeter() {
		return 5*length;
	}

	/**
	 * Overrides the default toString() method.
	 * Used to describe the pentagon for debugging purposes.
	 */
	@Override
	public String toString() {
		return "Pentagon: Length "+length+", Area "+getArea()+", Perimeter: "+getPerimeter();
	}
}