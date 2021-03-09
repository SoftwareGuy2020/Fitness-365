package model;

/**
 * Enum for storing user's unit preference
 * 
 * @author Travis
 *
 */
public enum Units {
	POUNDS, KILOGRAMS, INCHES, CENTIMETERS, MILES, KILOMETERS;

	/**
	 * Parses an array of bytes into an array of Units
	 * 
	 * @param selection
	 * @return
	 */
	public static Units[] parse(byte[] selection) {
		if (selection.length == 3) {
			Units[] units = new Units[3];
			units[0] = selection[0] == 0 ? Units.POUNDS : Units.KILOGRAMS;
			units[1] = selection[1] == 0 ? Units.INCHES : Units.CENTIMETERS;
			units[2] = selection[2] == 0 ? Units.MILES : Units.KILOMETERS;
			return units;
		}
		return null;
	}
}
