package uebung_1;

/**
 * Implementation of a field of integers.
 * It can be read and written using its own methods,
 * which throw an exception if a field which is out
 * of bounds is accessed.
 *
 * @author Jutz Benedikt
 * @version 20.4.2018
 */

public class Field {
	/**
	 * Array which stores the values.
	 */
	private int [] values;

	/**
	 * Creates a new field.
	 * @param size int
	 * The number of values which can be stored.
	 */
	public Field(int size) {
		values = new int[size];
	}

	/**
	 * Used to print the field and its contents to a terminal.
	 * @return String
	 */
	@Override
	public String toString() {
		final int lenArray = values.length;
		StringBuilder str = new StringBuilder("[");
		for(int i = 0; i < lenArray; i++) {
			str.append(values[i]);
			if(i < lenArray)
				str.append(", ");
		}
		str.append("]");
		return str.toString();
	}

	/**
	 * Returns the field value stored at the specified position.
	 * If the position is out of bounds, a {@link MissingFieldException} is thrown.
	 * @param position int
	 * @return values[position] int
	 * @throws MissingFieldException
	 */
	public int getFieldNumber(int position) throws MissingFieldException{
		if(position < 0)
			throw new MissingFieldException("An underflow occured!");
		else if(position >= values.length)
			throw new MissingFieldException("An overflow occured!");
		else
			return values[position];
	}

	/**
	 * Sets the field value at the specified position to the specified value.
	 * If the position is out of bounds, a {@link MissingFieldException} is thrown. 
	 * @param position int
	 * @param value int
	 * @throws MissingFieldException
	 */
	public void setFieldNumber(int position, int value) throws MissingFieldException{
		if(position < 0)
			throw new MissingFieldException("An underflow occured!");
		else if(position >= values.length)
			throw new MissingFieldException("An overflow occured!");
		else
			values[position] = value;
	}
}