package uebung_1;

/**
 * Circle implements the interface {@link GeomCalculation}.
 * @author Jutz Benedikt
 * @version 20.4.2018
 */

public class Circle implements GeomCalculation {
	/**
	 * The radius r of the circle.
	 */
	private int radius;

	/**
	 * Creates a new circle.
	 * @param radius int
	 */
	public Circle(int radius) {
		this.radius = radius;
	}

	/**
	 * Implements the getArea() method.
	 * The area of a circle is r^2*pi.
	 * @see GeomCalculation
	 */
	@Override
	public double getArea() {
		return Math.pow(radius, 2)*Math.PI;
	}

	/**
	 * Implements the getPerimeter() method.
	 * The perimeter of a circle is 2*r*pi.
	 * @see GeomCalculation
	 */
	@Override
	public double getPerimeter() {
		return 2*Math.PI*radius;
	}

	/**
	 * Overrides the default toString() method.
	 * Used to describe the circle for debugging purposes.
	 */
	@Override
	public String toString() {
		return "Circle: Radius "+radius+", Area "+getArea()+", Perimeter: "+getPerimeter();
	}
}