package uebung_2;

/**
 * Implementation of a date object:
 * - Contains fields for day, month and year.
 * - Can be displayed in the ISO-8601 format.
 * - Implements the Interface {@link Comparable}.
 * @author Deuscher Marco
 * @author Jutz Benedikt
 */
public class MyDate implements Comparable<MyDate>{
	// Three integer fields are required: day, month and year.
	private int day, month, year;

	/**
	 * Create a new MyDate object.
	 * @param day int
	 * @param month int
	 * @param year int
	 */
	public MyDate(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * Returns the ISO-8601 string of the date.
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		// Append the specified year.
		// If necessary, append 0es before the actual year.
		String date = "";
		if(year < 1000)
			date += "0";
		if(year < 100)
			date += "0";
		if(year < 10)
			date += "0";
		date += Integer.toString(year);

		// Append the specified month.
		// If necessary, append 0es before the actual month.
		date += "-";
		if(month < 10){
			date += "0";
		}
		date += Integer.toString(month);

		// Append the specified day.
		// If necessary, append 0es before the actual day.
		date += "-";
		if(day < 10){
			date += "0";
		}
		date += Integer.toString(day);
		return date;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MyDate))
			return false;
		MyDate other = (MyDate) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Compares another MyDate object to another.
	 * @param o MyDate
	 * Should be another MyDate object.
	 * @return -1 (if this.date < o)
	 * @return 1 (if this.date > o)
	 * @return 0 (if this.date.equals(o))
	 */
	@Override
	public int compareTo(MyDate date2) {
		if(date2 == null)
			throw new NullPointerException();
		if(date2.year < this.year)
			return 1;
		if(date2.year > this.year)
			return -1;

		if(date2.month < this.month)
			return 1;
		if(date2.month > this.month)
			return -1;

		if(date2.day < this.day)
			return 1;
		if(date2.day > this.day)
			return -1;
		return 0;
	}
}
