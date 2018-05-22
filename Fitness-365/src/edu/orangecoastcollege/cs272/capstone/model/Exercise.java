package edu.orangecoastcollege.cs272.capstone.model;

/**
 * @author Travis
 *
 */
public class Exercise {
	private int mId;
	private String mName;
	private String mMuscleGroup;
	
	
	/**
	 * @param id
	 * @param name
	 * @param muscleGroup
	 */
	public Exercise(int id, String name, String muscleGroup) {
		this.mId = id;
		this.mName = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.mMuscleGroup = muscleGroup.substring(0, 1).toUpperCase() + muscleGroup.substring(1);
	}

	/**
	 * Gets the id of Exercise
	 * 
	 * @return the id
	 */
	public int getId() {
		return this.mId;
	}

	/**
	 * Sets the id of Exercise
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.mId = id;
	}

	/**
	 * Gets the name of Exercise
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.mName;
	}

	/**
	 * Sets the name of Exercise
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.mName = name;
	}

	/**
	 * Gets the muscleGroup of Exercise
	 * 
	 * @return the muscleGroup
	 */
	public String getMuscleGroup() {
		return this.mMuscleGroup;
	}

	/**
	 * Sets the muscleGroup of Exercise
	 * 
	 * @param muscleGroup
	 *            the muscleGroup to set
	 */
	public void setMuscleGroup(String muscleGroup) {
		this.mMuscleGroup = muscleGroup;
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
		result = prime * result + this.mId;
		result = prime * result + ((this.mMuscleGroup == null) ? 0 : this.mMuscleGroup.hashCode());
		result = prime * result + ((this.mName == null) ? 0 : this.mName.hashCode());
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
		if (!(obj instanceof Exercise))
			return false;
		Exercise other = (Exercise) obj;
		if (this.mId != other.mId)
			return false;
		if (this.mMuscleGroup == null) {
			if (other.mMuscleGroup != null)
				return false;
		} else if (!this.mMuscleGroup.equals(other.mMuscleGroup))
			return false;
		if (this.mName == null) {
			if (other.mName != null)
				return false;
		} else if (!this.mName.equals(other.mName))
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
		builder.append("Exercise [mId=");
		builder.append(this.mId);
		builder.append(", mName=");
		builder.append(this.mName);
		builder.append(", mMuscleGroup=");
		builder.append(this.mMuscleGroup);
		builder.append("]");
		return builder.toString();
	}
}