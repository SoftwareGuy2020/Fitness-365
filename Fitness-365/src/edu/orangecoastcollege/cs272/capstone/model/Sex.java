package edu.orangecoastcollege.cs272.capstone.model;

/**
 * Enum for the two genders of the human species.
 * @author Travis
 *
 */
public enum Sex {
	Male("Male"), Female("Female");
	
	private String mDescription;
	
	private Sex(String s) {
		this.mDescription = s;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.mDescription;
	}
	
	/**
	 * Parses an int and returns an enum.
	 * @param selection
	 * @return
	 */
	public static Sex parseInt(int selection) {
		switch (selection) {		
		case 1:
			return Sex.Female;
		default:
			return Sex.Male;
		}		
	}
}
