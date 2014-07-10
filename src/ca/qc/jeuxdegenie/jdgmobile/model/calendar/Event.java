package ca.qc.jeuxdegenie.jdgmobile.model.calendar;

import android.util.Log;

/**
 * 
 */
public class Event {
	
	private int id;
	private String day;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private String name;
	private String description;
	private String location;
	private boolean hasDescription;
	
	/**
	 * 
	 * @param day
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 * @param name
	 * @param description
	 * @param location
	 * @param hasDescription
	 */
	public Event(int id, String day, String startDate, String endDate, String startTime, String endTime, 
				 String name, String description, String location, String hasDescription) {
		this.id = id;
		this.day = day;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.description = description;
		this.location = location;
		this.hasDescription = (hasDescription.equals("true") ? true : false);
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the hasDescription
	 */
	public boolean hasDescription() {
		return hasDescription;
	}

	/**
	 * @param hasDescription the hasDescription to set
	 */
	public void setHasDescription(boolean hasDescription) {
		this.hasDescription = hasDescription;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
