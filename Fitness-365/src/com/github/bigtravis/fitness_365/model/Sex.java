package com.github.bigtravis.fitness_365.model;

/**
 * @author Travis
 *
 */
public enum Sex {
	MALE("Male"), FEMALE("Female");
	
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
			return Sex.FEMALE;
		default:
			return Sex.MALE;
		}		
	}
}
