package model;

/**
 * Represents a food entry in the food diary
 *
 * @author Travis
 *
 */
public class Meal {
	private int mId;
	private String mName;
	private double mServingSize;
	private int calories;
	private double fat;
	private double carbs;
	private double protein;
	private String group;

	/**
	 * @param id
	 * @param servingSize
	 * @param calories
	 * @param fat
	 * @param carbs
	 * @param protein
	 */
	public Meal(int id, String name, double servingSize, int calories, double fat, double carbs,
			double protein, String group) {
		this.mId = id;
		this.mName = name;
		this.mServingSize = servingSize;
		this.calories = calories;
		this.fat = fat;
		this.carbs = carbs;
		this.protein = protein;
		this.group = group;
	}

	/**
	 * @param servingSize
	 * @param calories
	 * @param fat
	 * @param carbs
	 * @param protein
	 */
	public Meal(String name, double servingSize, int calories, double fat, double carbs,
			double protein, String group) {
		this.mId = -1;
		this.mName = name;
		this.mServingSize = servingSize;
		this.calories = calories;
		this.fat = fat;
		this.carbs = carbs;
		this.protein = protein;
		this.group = group;
	}

	/**
	 * Gets the id of Meal
	 *
	 * @return the id
	 */
	public int getId() {
		return this.mId;
	}

	/**
	 * Sets the id of Meal
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.mId = id;
	}

	/**
	 * Gets the name of Meal
	 *
	 * @return the name
	 */
	public String getName() {
		return this.mName;
	}

	/**
	 * Sets the name of Meal
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.mName = name;
	}

	/**
	 * Gets the servingSize of Meal
	 *
	 * @return the servingSize
	 */
	public double getServingSize() {
		return this.mServingSize;
	}

	/**
	 * Sets the servingSize of Meal
	 *
	 * @param servingSize
	 *            the servingSize to set
	 */
	public void setServingSize(double servingSize) {
		this.mServingSize = servingSize;
	}

	/**
	 * Gets the calories of Meal
	 *
	 * @return the calories
	 */
	public int getCalories() {
		return this.calories;
	}

	/**
	 * Sets the calories of Meal
	 *
	 * @param calories
	 *            the calories to set
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}

	/**
	 * Gets the fat of Meal
	 *
	 * @return the fat
	 */
	public double getFat() {
		return this.fat;
	}

	/**
	 * Sets the fat of Meal
	 *
	 * @param fat
	 *            the fat to set
	 */
	public void setFat(double fat) {
		this.fat = fat;
	}

	/**
	 * Gets the carbs of Meal
	 *
	 * @return the carbs
	 */
	public double getCarbs() {
		return this.carbs;
	}

	/**
	 * Sets the carbs of Meal
	 *
	 * @param carbs
	 *            the carbs to set
	 */
	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	/**
	 * Gets the group of Meal
	 *
	 * @return the group
	 */
	public String getGroup() {
		return this.group;
	}

	/**
	 * Sets the group of Meal
	 *
	 * @param group
	 *            the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	public double getProtein() {
        return this.protein;
    }

    /**
     * Sets the protein of Meal
     *
     * @param protein
     *            the protein to set
     */
    public void setProtein(double protein) {
        this.protein = protein;
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
		result = prime * result + this.calories;
		long temp;
		temp = Double.doubleToLongBits(this.carbs);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.fat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + this.mId;
		temp = Double.doubleToLongBits(this.mServingSize);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.protein);
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
		if (!(obj instanceof Meal))
			return false;
		Meal other = (Meal) obj;
		if (this.calories != other.calories)
			return false;
		if (Double.doubleToLongBits(this.carbs) != Double.doubleToLongBits(other.carbs))
			return false;
		if (Double.doubleToLongBits(this.fat) != Double.doubleToLongBits(other.fat))
			return false;
		if (this.mId != other.mId)
			return false;
		if (Double.doubleToLongBits(this.mServingSize) != Double.doubleToLongBits(other.mServingSize))
			return false;
		if (Double.doubleToLongBits(this.protein) != Double.doubleToLongBits(other.protein))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Name = ");
		builder.append(this.mName);
		builder.append(", Group = ");
		builder.append(this.group);
		builder.append(", Calories = ");
		builder.append(this.calories);
		builder.append(", Fat = ");
		builder.append(this.fat);
		builder.append(", Carbs = ");
		builder.append(this.carbs);
		builder.append(", Protein = ");
		builder.append(this.protein);
		
		return builder.toString();
	}
}
