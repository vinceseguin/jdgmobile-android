package ca.qc.jeuxdegenie.jdgmobile.model.calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

/**
 * 
 */
public class Event implements Comparable<Event> {
	
	private static final String TAG_ID = "id";
	private static final String TAG_DAY = "day";
	private static final String TAG_START_DATE = "startDate";
	private static final String TAG_END_DATE = "endDate";
	private static final String TAG_START_TIME = "startTime";
	private static final String TAG_END_TIME = "endTime";
	private static final String TAG_NAME = "name";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_LOCATION = "location";
	private static final String TAG_HAS_DESCRIPTION = "hasDescription";
	
	private static final int NUM_COL_ID = 0;
	private static final int NUM_COL_DAY = 1;
	private static final int NUM_COL_START_DATE = 2;
	private static final int NUM_COL_END_DATE = 3;
	private static final int NUM_COL_START_TIME = 4;
	private static final int NUM_COL_END_TIME = 5;
	private static final int NUM_COL_NAME = 6;
	private static final int NUM_COL_DESCRIPTION = 7;
	private static final int NUM_COL_LOCATION = 8;
	private static final int NUM_COL_HAS_DESCRIPTION = 9;	
	
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
	 * @param obj
	 */
	public Event(JSONObject obj) {
		try {
			this.id = obj.getInt(TAG_ID);
			this.day = obj.getString(TAG_DAY);
			this.startDate = obj.getString(TAG_START_DATE); 
			this.endDate = obj.getString(TAG_END_DATE);
			this.startTime = obj.getString(TAG_START_TIME);
			this.endTime = obj.getString(TAG_END_TIME);
			this.name = obj.getString(TAG_NAME); 
			this.description = obj.getString(TAG_DESCRIPTION);
			this.location = obj.getString(TAG_LOCATION); 
			this.hasDescription = (obj.getString(TAG_HAS_DESCRIPTION).equals("true") ? true : false);			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public Event(Cursor c) {
		this.id = c.getInt(NUM_COL_ID);
		this.day = c.getString(NUM_COL_DAY);
		this.startDate = c.getString(NUM_COL_START_DATE); 
		this.endDate = c.getString(NUM_COL_END_DATE);
		this.startTime = c.getString(NUM_COL_START_TIME);
		this.endTime = c.getString(NUM_COL_END_TIME);
		this.name = c.getString(NUM_COL_NAME); 
		this.description = c.getString(NUM_COL_DESCRIPTION);
		this.location = c.getString(NUM_COL_LOCATION); 
		this.hasDescription = (c.getInt(NUM_COL_HAS_DESCRIPTION) == 1 ? true : false);
	}
	
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

	@Override
	public int compareTo(Event arg0) {
		return id - arg0.id;
	}
}
