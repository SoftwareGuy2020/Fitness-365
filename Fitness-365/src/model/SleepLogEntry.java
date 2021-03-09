package model;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class SleepLogEntry {

	private LocalDate mDate;
	private LocalTime mSleepTime, mWakeTime;
	private int mID, numOfInterruptions;

	/**
	 * @param date
	 * @param sleepTime
	 * @param wakeTime
	 * @param numOfInterruptions
	 */
	public SleepLogEntry(LocalDate date, LocalTime sleepTime, LocalTime wakeTime, int numOfInterruptions) {
		mDate = date;
		mSleepTime = sleepTime;
		mWakeTime = wakeTime;
		this.numOfInterruptions = numOfInterruptions;
	}

	/**
	 * @param id
	 * @param date
	 * @param sleepTime
	 * @param wakeTime
	 * @param numOfInterruptions
	 */
	public SleepLogEntry(int id, LocalDate date, LocalTime sleepTime, LocalTime wakeTime, int numOfInterruptions) {
		mID = id;
		mDate = date;
		mSleepTime = sleepTime;
		mWakeTime = wakeTime;
		this.numOfInterruptions = numOfInterruptions;
	}

	/**
	 * 
	 * @return Entry ID (int)
	 */
	public int getID() {
		return mID;
	}

	public void setID(int iD) {
		mID = iD;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return mDate;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		mDate = date;
	}

	/**
	 * @return the sleepTime
	 */
	public LocalTime getSleepTime() {
		return mSleepTime;
	}

	/**
	 * @param sleepTime the sleepTime to set
	 */
	public void setSleepTime(LocalTime sleepTime) {
		mSleepTime = sleepTime;
	}

	/**
	 * @return the wakeTime
	 */
	public LocalTime getWakeTime() {
		return mWakeTime;
	}

	/**
	 * @return the wake time (string) formatted to hh:mm am/pm, null if time is null
	 */
	public String getFormatedWakeTime() {
		if (mWakeTime == null)
			return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		return mWakeTime.format(formatter);
	}

	/**
	 * @return the sleep time (string) formatted to hh:mm am/pm, null if time is
	 *         null
	 * 
	 */
	public String getFormatedSleepTime() {
		if (mSleepTime == null)
			return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		return mSleepTime.format(formatter);
	}

	/**
	 * @param wakeTime the wakeTime to set
	 */
	public void setWakeTime(LocalTime wakeTime) {
		mWakeTime = wakeTime;
	}

	/**
	 * @return the numOfInteruptions
	 */
	public int getNumOfInterruptions() {
		return numOfInterruptions;
	}

	/**
	 * @param numOfInteruptions the numOfInteruptions to set
	 */
	public void setNumOfInterruptions(int numOfInterruptions) {
		this.numOfInterruptions = numOfInterruptions;
	}

	/**
	 * 
	 * @return the hours (double) between the sleep time and wake time of this entry
	 */
	public double getHoursAsleep() {
		if (getSleepTime() == null || getWakeTime() == null)
			return -1.0;
		double hours = 0.0;
		LocalTime sleepTimeCopy = getSleepTime();
		while (sleepTimeCopy.getHour() != getWakeTime().getHour()) {
			++hours;
			sleepTimeCopy = sleepTimeCopy.plusHours(1);
		}
		hours += (getWakeTime().getMinute() - sleepTimeCopy.getMinute()) / 60.0;

		return hours;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SleepLogEntry [Date=").append(mDate).append(", Sleep Time=").append(mSleepTime)
				.append(", Wake Time=").append(mWakeTime).append(", Number of Interruptions=")
				.append(numOfInterruptions).append("]");
		return builder.toString();
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
		result = prime * result + ((mDate == null) ? 0 : mDate.hashCode());
		result = prime * result + ((mSleepTime == null) ? 0 : mSleepTime.hashCode());
		result = prime * result + ((mWakeTime == null) ? 0 : mWakeTime.hashCode());
		result = prime * result + numOfInterruptions;
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
		if (getClass() != obj.getClass())
			return false;
		SleepLogEntry other = (SleepLogEntry) obj;
		if (mDate == null) {
			if (other.mDate != null)
				return false;
		} else if (!mDate.equals(other.mDate))
			return false;
		if (mSleepTime == null) {
			if (other.mSleepTime != null)
				return false;
		} else if (!mSleepTime.equals(other.mSleepTime))
			return false;
		if (mWakeTime == null) {
			if (other.mWakeTime != null)
				return false;
		} else if (!mWakeTime.equals(other.mWakeTime))
			return false;
		if (numOfInterruptions != other.numOfInterruptions)
			return false;
		return true;
	}

}
