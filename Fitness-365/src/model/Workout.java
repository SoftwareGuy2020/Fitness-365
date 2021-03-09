package model;

import java.time.LocalDate;

/**
 * 
 * Represents a completed workout of one exercise.
 * 
 * @author Travis
 *
 */
public class Workout {
	private int mId;
	private int mUserId;
	private Exercise mExercise;
	private double mWeight;
	private int mReps;
	private LocalDate mDate;

	/**
	 * Instantiates a workout.
	 * 
	 * @param id       the database index
	 * @param userId   the user's database index
	 * @param exercise the exercise performed in the workout
	 * @param weight   the weight lifted
	 * @param reps     the number of times the weight was lifted
	 * @param date     the date that the workout occured on
	 */
	public Workout(int id, int userId, Exercise exercise, double weight, int reps, LocalDate date) {
		this.mId = id;
		this.mUserId = userId;
		this.mExercise = exercise;
		this.mWeight = weight;
		this.mReps = reps;
		this.mDate = date;
	}

	/**
	 * Gets the id of Workout
	 * 
	 * @return the id
	 */
	public int getId() {
		return this.mId;
	}

	/**
	 * Sets the id of Workout
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.mId = id;
	}

	/**
	 * Gets the userId of Workout
	 * 
	 * @return the userId
	 */
	public int getUserId() {
		return this.mUserId;
	}

	/**
	 * Sets the userId of Workout
	 * 
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.mUserId = userId;
	}

	/**
	 * Gets the exercise of Workout
	 * 
	 * @return the exercise
	 */
	public Exercise getExercise() {
		return this.mExercise;
	}

	public String getExerciseName() {
		return this.mExercise.getName();
	}

	public String getExerciseMuscleGroup() {
		return this.mExercise.getMuscleGroup();
	}

	/**
	 * Sets the exercise of Workout
	 * 
	 * @param exercise the exerciseId to set
	 */
	public void setExercise(Exercise exercise) {
		this.mExercise = exercise;
	}

	/**
	 * Gets the weight of Workout
	 * 
	 * @return the weight
	 */
	public double getWeight() {
		return this.mWeight;
	}

	/**
	 * Sets the weight of Workout
	 * 
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.mWeight = weight;
	}

	/**
	 * Gets the reps of Workout
	 * 
	 * @return the reps
	 */
	public int getReps() {
		return this.mReps;
	}

	/**
	 * Sets the reps of Workout
	 * 
	 * @param reps the reps to set
	 */
	public void setReps(int reps) {
		this.mReps = reps;
	}

	/**
	 * Gets the date of Workout
	 * 
	 * @return the date
	 */
	public LocalDate getDate() {
		return this.mDate;
	}

	/**
	 * Sets the date of Workout
	 * 
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.mDate = date;
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
		result = prime * result + ((this.mDate == null) ? 0 : this.mDate.hashCode());
		result = prime * result + ((this.mExercise == null) ? 0 : this.mExercise.hashCode());
		result = prime * result + this.mId;
		result = prime * result + this.mReps;
		result = prime * result + this.mUserId;
		long temp;
		temp = Double.doubleToLongBits(this.mWeight);
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
		if (!(obj instanceof Workout))
			return false;
		Workout other = (Workout) obj;
		if (this.mDate == null) {
			if (other.mDate != null)
				return false;
		} else if (!this.mDate.equals(other.mDate))
			return false;
		if (this.mExercise == null) {
			if (other.mExercise != null)
				return false;
		} else if (!this.mExercise.equals(other.mExercise))
			return false;
		if (this.mId != other.mId)
			return false;
		if (this.mReps != other.mReps)
			return false;
		if (this.mUserId != other.mUserId)
			return false;
		if (Double.doubleToLongBits(this.mWeight) != Double.doubleToLongBits(other.mWeight))
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
		builder.append("Workout [mId=");
		builder.append(this.mId);
		builder.append(", mUserId=");
		builder.append(this.mUserId);
		builder.append(", mExercise=");
		builder.append(this.mExercise);
		builder.append(", mWeight=");
		builder.append(this.mWeight);
		builder.append(", mReps=");
		builder.append(this.mReps);
		builder.append(", mDate=");
		builder.append(this.mDate);
		builder.append("]");
		return builder.toString();
	}
}
