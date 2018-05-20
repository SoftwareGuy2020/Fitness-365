package edu.orangecoastcollege.cs272.capstone.model;

import java.time.LocalDate;

/**
 * @author Travis
 *
 */
public class FoodDiaryEntry {
	private int mId;
	private Meal mMeal;
	private double mNumServings;
	private Category mCategory;
	private LocalDate mDate;
	private int mUserId;
	
	
	/**
	 * @param id
	 * @param meal
	 * @param category
	 * @param date
	 * @param userId
	 */
	public FoodDiaryEntry(int id, Meal meal, double numServings, Category category, LocalDate date, int userId) {
		this.mId = id;
		this.mMeal = meal;
		this.mNumServings = numServings;
		this.mCategory = category;
		this.mDate = date;
		this.mUserId = userId;
	}


	/**
	 * @param meal
	 * @param category
	 * @param date
	 * @param userId
	 */
	public FoodDiaryEntry(Meal meal, double numServings, Category category, LocalDate date, int userId) {
		this.mMeal = meal;
		this.mNumServings = numServings;
		this.mCategory = category;
		this.mDate = date;
		this.mUserId = userId;
	}


	/**
	 * Gets the id of FoodDiaryEntry
	 * @return the id
	 */
	public int getId() {
		return this.mId;
	}


	/**
	 * Sets the id of FoodDiaryEntry
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.mId = id;
	}


	/**
	 * Gets the meal of FoodDiaryEntry
	 * @return the meal
	 */
	public Meal getMeal() {
		return this.mMeal;
	}


	/**
	 * Sets the meal of FoodDiaryEntry
	 * @param meal the meal to set
	 */
	public void setMeal(Meal meal) {
		this.mMeal = meal;
	}
	

	/**
	 * Gets the numServings of FoodDiaryEntry
	 * @return the numServings
	 */
	public double getNumServings() {
		return this.mNumServings;
	}


	/**
	 * Sets the numServings of FoodDiaryEntry
	 * @param numServings the numServings to set
	 */
	public void setNumServings(double numServings) {
		this.mNumServings = numServings;
	}


	/**
	 * Gets the category of FoodDiaryEntry
	 * @return the category
	 */
	public Category getCategory() {
		return this.mCategory;
	}


	/**
	 * Sets the category of FoodDiaryEntry
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.mCategory = category;
	}


	/**
	 * Gets the date of FoodDiaryEntry
	 * @return the date
	 */
	public LocalDate getDate() {
		return this.mDate;
	}


	/**
	 * Sets the date of FoodDiaryEntry
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.mDate = date;
	}


	/**
	 * Gets the userId of FoodDiaryEntry
	 * @return the userId
	 */
	public int getUserId() {
		return this.mUserId;
	}


	/**
	 * Sets the userId of FoodDiaryEntry
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.mUserId = userId;
	}
	
	public String getMealName() {
		return this.mMeal.getName();		
	}
	
	public double getMealServingSize() {
		return this.mMeal.getServingSize();
	}

	public int getMealCalories() {
		return (int) (this.mMeal.getCalories() * this.mNumServings);
	}

	public double getMealFat() {
		return (int) (this.mMeal.getFat() * this.mNumServings);
	}
	
	public double getMealCarbs() {
		return (int) (this.mMeal.getCarbs() * this.mNumServings);
	}
	
	public double getMealProtein() {
		return (int) (this.mMeal.getProtein() * this.mNumServings);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.mCategory == null) ? 0 : this.mCategory.hashCode());
		result = prime * result + ((this.mDate == null) ? 0 : this.mDate.hashCode());
		result = prime * result + this.mId;
		result = prime * result + ((this.mMeal == null) ? 0 : this.mMeal.hashCode());
		result = prime * result + this.mUserId;
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
		if (!(obj instanceof FoodDiaryEntry))
			return false;
		FoodDiaryEntry other = (FoodDiaryEntry) obj;
		if (this.mCategory != other.mCategory)
			return false;
		if (this.mDate == null) {
			if (other.mDate != null)
				return false;
		} else if (!this.mDate.equals(other.mDate))
			return false;
		if (this.mId != other.mId)
			return false;
		if (this.mMeal == null) {
			if (other.mMeal != null)
				return false;
		} else if (!this.mMeal.equals(other.mMeal))
			return false;
		if (this.mUserId != other.mUserId)
			return false;
		return true;
	}
	
}
