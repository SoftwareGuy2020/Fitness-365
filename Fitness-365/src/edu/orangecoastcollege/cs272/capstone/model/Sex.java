package edu.orangecoastcollege.cs272.capstone.model;

/**
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
	
	public static Sex parseInt(int selection) {
		switch (selection) {		
		case 1:
			return Sex.Female;
		default:
			return Sex.Male;
		}		
	}
}
