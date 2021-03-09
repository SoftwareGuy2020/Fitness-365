package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

/**
 * 
 * Represents a user of the Fitness 365 application
 *
 */
public class User {
	private static final int NUM_UNITS_CATAGORIES = 3;

	private int mId;
	private String mUserName;
	private String mSecurityQ;
	private String mSecurityA;
	private String mName;
	private LocalDate mBirthDate;
	private Sex mSex;
	private Units[] mUnitsOfChoice = { Units.POUNDS, Units.INCHES, Units.MILES };
	private int mHeight;
	private double mStartingWeight;
	private double mGoalWeight;
	private double mCurrentWeight;
	private double mWeeklyGoal;
	private int mTDEE;

	/**
	 * @param id
	 * @param userName
	 * @param securityQ
	 * @param securityA
	 * @param name
	 * @param birthDate
	 * @param sex
	 * @param unitsOfChoice
	 * @param height
	 * @param startingWeight
	 * @param goalWeight
	 * @param currentWeight
	 * @param weeklyGoal
	 */
	public User(int id, String userName, String securityQ, String securityA, String name, LocalDate birthDate, Sex sex,
			Units[] unitsOfChoice, int height, double startingWeight, double goalWeight, double currentWeight,
			double weeklyGoal, int tdee) {
		this.mId = id;
		this.mUserName = userName;
		this.mSecurityQ = securityQ;
		this.mSecurityA = securityA;
		this.mName = name;
		this.mBirthDate = birthDate;
		this.mSex = sex;

		if (unitsOfChoice != null)
			this.mUnitsOfChoice = unitsOfChoice;

		this.mHeight = height;
		this.mStartingWeight = startingWeight;
		this.mGoalWeight = goalWeight;
		this.mCurrentWeight = currentWeight;
		this.mWeeklyGoal = weeklyGoal;
		this.mTDEE = tdee;
	}

	/**
	 * Gets the userName of User
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return this.mUserName;
	}

	/**
	 * Sets the userName of User
	 * 
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.mUserName = userName;
	}

	/**
	 * Gets the securityQ of User
	 * 
	 * @return the securityQ
	 */
	public String getSecurityQ() {
		return this.mSecurityQ;
	}

	/**
	 * Sets the securityQ of User
	 * 
	 * @param securityQ the securityQ to set
	 */
	public void setSecurityQ(String securityQ) {
		this.mSecurityQ = securityQ;
	}

	/**
	 * Gets the securityA of User
	 * 
	 * @return the securityA
	 */
	public String getSecurityA() {
		return this.mSecurityA;
	}

	/**
	 * Sets the securityA of User
	 * 
	 * @param securityA the securityA to set
	 */
	public void setSecurityA(String securityA) {
		this.mSecurityA = securityA;
	}

	/**
	 * Gets the name of User
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.mName;
	}

	/**
	 * Sets the name of User
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.mName = name;
	}

	/**
	 * Gets the age of User
	 * 
	 * @return the age
	 */
	public int getAge() {
		return Period.between(this.mBirthDate, LocalDate.now()).getYears();
	}

	/**
	 * Gets the birth date of User
	 * 
	 * @return string representation of birth date in the format of: ISO-8601 format
	 *         uuuu-MM-dd
	 */
	public String getBirthDate() {
		return this.mBirthDate.toString();
	}

	/**
	 * Sets the birth date of User
	 * 
	 * @param birthDate - the birth date to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.mBirthDate = birthDate;
	}

	/**
	 * Gets the sex of User
	 * 
	 * @return the sex
	 */
	public Sex getSex() {
		return this.mSex;
	}

	/**
	 * Sets the sex of User
	 * 
	 * @param sex the sex to set
	 */
	public void setSex(Sex sex) {
		this.mSex = sex;
	}

	/**
	 * Gets the unitsOfChoice of User
	 * 
	 * @return the unitsOfChoice
	 */
	public Units[] getUnitsOfChoice() {
		return this.mUnitsOfChoice;
	}

	/**
	 * Sets the unitsOfChoice of User
	 * 
	 * @param unitsOfChoice the unitsOfChoice to set
	 */
	public void setUnitsOfChoice(Units[] unitsOfChoice) {
		this.mUnitsOfChoice = unitsOfChoice;
	}

	/**
	 * Gets the height of User
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return this.mHeight;
	}

	/**
	 * Sets the height of User
	 * 
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.mHeight = height;
	}

	/**
	 * Gets the startingWeight of User
	 * 
	 * @return the startingWeight
	 */
	public double getStartingWeight() {
		return this.mStartingWeight;
	}

	/**
	 * Sets the startingWeight of User
	 * 
	 * @param startingWeight the startingWeight to set
	 */
	public void setStartingWeight(double startingWeight) {
		this.mStartingWeight = startingWeight;
	}

	/**
	 * Gets the goalWeight of User
	 * 
	 * @return the goalWeight
	 */
	public double getGoalWeight() {
		return this.mGoalWeight;
	}

	/**
	 * Sets the goalWeight of User
	 * 
	 * @param goalWeight the goalWeight to set
	 */
	public void setGoalWeight(double goalWeight) {
		this.mGoalWeight = goalWeight;
	}

	/**
	 * Gets the currentWeight of User
	 * 
	 * @return the currentWeight
	 */
	public double getCurrentWeight() {
		return this.mCurrentWeight;
	}

	/**
	 * Sets the currentWeight of User
	 * 
	 * @param currentWeight the currentWeight to set
	 */
	public void setCurrentWeight(double currentWeight) {
		this.mCurrentWeight = currentWeight;
	}

	/**
	 * Gets the weeklyGoal of User
	 * 
	 * @return the weeklyGoal
	 */
	public double getWeeklyGoal() {
		return this.mWeeklyGoal;
	}

	/**
	 * Sets the weeklyGoal of User
	 * 
	 * @param weeklyGoal the weeklyGoal to set
	 */
	public void setWeeklyGoal(double weeklyGoal) {
		this.mWeeklyGoal = weeklyGoal;
	}

	/**
	 * Gets the numUnitsCatagories of User
	 * 
	 * @return the numUnitsCatagories
	 */
	public static int getNumUnitsCatagories() {
		return NUM_UNITS_CATAGORIES;
	}

	/**
	 * Gets the id of User
	 * 
	 * @return the id
	 */
	public int getId() {
		return this.mId;
	}

	public void setID(int id) {
		mId = id;

	}

	/**
	 * Gets the TDEE of User
	 * 
	 * @return the TDEE
	 */
	public int getTDEE() {
		return this.mTDEE;
	}

	/**
	 * Sets the TDEE of User
	 * 
	 * @param tDEE the TDEE to set
	 */
	public void setTDEE(int tdee) {
		this.mTDEE = tdee;
	}

	public byte[] convertUnitsToByteArray() {
		byte[] bArray = new byte[3];
		byte zero = (byte) 0;
		byte one = (byte) 1;
		bArray[0] = (mUnitsOfChoice[0] == Units.POUNDS ? zero : one);
		bArray[1] = mUnitsOfChoice[1] == Units.INCHES ? zero : one;
		bArray[2] = mUnitsOfChoice[2] == Units.POUNDS ? zero : one;

		return bArray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.mBirthDate == null) ? 0 : this.mBirthDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(this.mCurrentWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.mGoalWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + this.mHeight;
		result = prime * result + this.mId;
		result = prime * result + ((this.mName == null) ? 0 : this.mName.hashCode());
		result = prime * result + ((this.mSecurityA == null) ? 0 : this.mSecurityA.hashCode());
		result = prime * result + ((this.mSecurityQ == null) ? 0 : this.mSecurityQ.hashCode());
		result = prime * result + ((this.mSex == null) ? 0 : this.mSex.hashCode());
		temp = Double.doubleToLongBits(this.mStartingWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(this.mUnitsOfChoice);
		result = prime * result + ((this.mUserName == null) ? 0 : this.mUserName.hashCode());
		temp = Double.doubleToLongBits(this.mWeeklyGoal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (this.mBirthDate == null) {
			if (other.mBirthDate != null)
				return false;
		} else if (!this.mBirthDate.equals(other.mBirthDate))
			return false;
		if (Double.doubleToLongBits(this.mCurrentWeight) != Double.doubleToLongBits(other.mCurrentWeight))
			return false;
		if (Double.doubleToLongBits(this.mGoalWeight) != Double.doubleToLongBits(other.mGoalWeight))
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
		if (this.mSecurityA == null) {
			if (other.mSecurityA != null)
				return false;
		} else if (!this.mSecurityA.equals(other.mSecurityA))
			return false;
		if (this.mSecurityQ == null) {
			if (other.mSecurityQ != null)
				return false;
		} else if (!this.mSecurityQ.equals(other.mSecurityQ))
			return false;
		if (this.mSex != other.mSex)
			return false;
		if (Double.doubleToLongBits(this.mStartingWeight) != Double.doubleToLongBits(other.mStartingWeight))
			return false;
		if (!Arrays.equals(this.mUnitsOfChoice, other.mUnitsOfChoice))
			return false;
		if (this.mUserName == null) {
			if (other.mUserName != null)
				return false;
		} else if (!this.mUserName.equals(other.mUserName))
			return false;
		if (Double.doubleToLongBits(this.mWeeklyGoal) != Double.doubleToLongBits(other.mWeeklyGoal))
			return false;
		return true;
	}
}
