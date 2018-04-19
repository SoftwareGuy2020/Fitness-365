package com.github.bigtravis.fitness_365.model;

import java.util.Arrays;

/**
 * 
 * Represents a user of the Fitness 365 application
 * @author Travis Morrissey
 *
 */
public class User {
	private static final int NUM_UNITS_CATAGORIES = 3;
	
	private int mId;
	private String mName;
	private double mWeight;
	private int mAge;
	private Sex mSex;
	// height in inches/centimeters
	private int mHeight; 
	private Units[] mUnitsOfChoice = {Units.POUNDS, Units.INCHES, Units.MILES};
	
	
	/**
	 * @param id
	 * @param name
	 * @param weight
	 * @param age
	 * @param sex
	 * @param height
	 * @param unitsOfChoice
	 */
	public User(int id, String name, double weight, int age, Sex sex, int height, Units[] unitsOfChoice) {
		this.mId = id;
		this.mName = name;
		this.mWeight = weight;
		this.mAge = age;
		this.mSex = sex;
		this.mHeight = height;
		if (unitsOfChoice != null && unitsOfChoice.length == NUM_UNITS_CATAGORIES)
			this.mUnitsOfChoice = Arrays.copyOf(unitsOfChoice, NUM_UNITS_CATAGORIES);
	}


	/**
	 * Gets the name of User
	 * @return the name
	 */
	public String getName() {
		return this.mName;
	}


	/**
	 * Sets the name of User
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.mName = name;
	}


	/**
	 * Gets the weight of User
	 * @return the weight
	 */
	public double getWeight() {
		return this.mWeight;
	}


	/**
	 * Sets the weight of User
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.mWeight = weight;
	}


	/**
	 * Gets the age of User
	 * @return the age
	 */
	public int getAge() {
		return this.mAge;
	}


	/**
	 * Sets the age of User
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.mAge = age;
	}


	/**
	 * Gets the sex of User
	 * @return the sex
	 */
	public Sex getSex() {
		return this.mSex;
	}


	/**
	 * Sets the sex of User
	 * @param sex the sex to set
	 */
	public void setSex(Sex sex) {
		this.mSex = sex;
	}


	/**
	 * Gets the height of User
	 * @return the height
	 */
	public int getHeight() {
		return this.mHeight;
	}


	/**
	 * Sets the height of User
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.mHeight = height;
	}


	/**
	 * Gets the unitsOfChoice of User
	 * @return the unitsOfChoice
	 */
	public Units[] getUnitsOfChoice() {
		return this.mUnitsOfChoice;
	}


	/**
	 * Sets the unitsOfChoice of User
	 * @param unitsOfChoice the unitsOfChoice to set
	 */
	public void setUnitsOfChoice(Units[] unitsOfChoice) {
		this.mUnitsOfChoice = unitsOfChoice;
	}


	/**
	 * Gets the id of User
	 * @return the id
	 */
	public int getId() {
		return this.mId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.mAge;
		result = prime * result + this.mHeight;
		result = prime * result + this.mId;
		result = prime * result + ((this.mName == null) ? 0 : this.mName.hashCode());
		result = prime * result + ((this.mSex == null) ? 0 : this.mSex.hashCode());
		result = prime * result + Arrays.hashCode(this.mUnitsOfChoice);
		long temp;
		temp = Double.doubleToLongBits(this.mWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (this.mAge != other.mAge)
			return false;
		if (this.mHeight != other.mHeight)
			return false;
		if (this.mId != other.mId)
			return false;
		if (this.mName == null) {
			if (other.mName != null)
				return false;
		} else if (!this.mName.equals(other.mName))
			return false;
		if (this.mSex != other.mSex)
			return false;
		if (!Arrays.equals(this.mUnitsOfChoice, other.mUnitsOfChoice))
			return false;
		if (Double.doubleToLongBits(this.mWeight) != Double.doubleToLongBits(other.mWeight))
			return false;
		return true;
	}
}
