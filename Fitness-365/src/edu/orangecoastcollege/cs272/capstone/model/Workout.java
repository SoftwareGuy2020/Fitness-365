package edu.orangecoastcollege.cs272.capstone.model;

import java.time.LocalDate;

/**
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
	 * @param id
	 * @param userId
	 * @param exerciseId
	 * @param weight
	 * @param reps
	 * @param date
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
	 * @param id
	 *            the id to set
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
	 * @param userId
	 *            the userId to set
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
	 * @param exercise
	 *            the exerciseId to set
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
	 * @param weight
	 *            the weight to set
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
	 * @param reps
	 *            the reps to set
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
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.mDate = date;
	}	
}
