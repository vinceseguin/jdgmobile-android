package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

/**
 * 
 */
public class Result {

	private static final String TAG_NAME = "name";
	private static final String TAG_LEAF = "leaf";
	private static final String TAG_LEAF_ID = "leafId";
	private static final String TAG_ITEMS = "items";
	
	private static final int NUM_COL_COMPETITION_TYPE_ID = 1;
	private static final int NUM_COL_COMPETITION_TYPE_NAME = 2;
	private static final int NUM_COL_COMPETITION_ID = 3;
	private static final int NUM_COL_COMPETITION_NAME = 4;
	private static final int NUM_COL_COMPETITION_TYPE = 5;
	
	private String name;
	private boolean isLeaf;
	private int leafId;
	private Result[] items;
	private int competitionType;
	
	/**
	 * 
	 * @param obj
	 */
	public Result(JSONObject obj) {
		try {
			this.name = obj.getString(TAG_NAME);
			this.isLeaf = obj.getString(TAG_LEAF).equals("true")? true : false;
			this.leafId = obj.getInt(TAG_LEAF_ID);
			
			List<Result> children = new ArrayList<Result>();
			if (!isLeaf){
				JSONArray jArrChildren = obj.getJSONArray(TAG_ITEMS);
				for (int i=0; i<jArrChildren.length(); i++) {
					JSONObject child = jArrChildren.getJSONObject(i);
					children.add(new Result(child));
				}
			}
			this.items = children.toArray(new Result[children.size()]);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public Result(Cursor c) {
		// it's a competition type
		if(!c.isNull(NUM_COL_COMPETITION_TYPE_ID)) {
			this.isLeaf = false;
			this.leafId = c.getInt(NUM_COL_COMPETITION_TYPE_ID);
			this.name = c.getString(NUM_COL_COMPETITION_TYPE_NAME);
		} else { //it's a competition
			this.isLeaf = true;
			this.leafId = c.getInt(NUM_COL_COMPETITION_ID);
			this.name = c.getString(NUM_COL_COMPETITION_NAME);
			this.competitionType = c.getInt(NUM_COL_COMPETITION_TYPE);
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param isLeaf
	 * @param leafId
	 * @param items
	 */
	public Result(String name, boolean isLeaf, int leafId, Result[] items) {
		this.name = name;
		this.isLeaf = isLeaf;
		this.leafId = leafId;
		this.items = items;
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
	 * @return the isLeaf
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf the isLeaf to set
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * 
	 * @return the competitionType
	 */
	public int getCompetitionType() {
		return competitionType;
	}

	/**
	 * 
	 * @param competitionType the competitionType to set
	 */
	public void setCompetitionType(int competitionType) {
		this.competitionType = competitionType;
	}

	/**
	 * @return the leafId
	 */
	public int getLeafId() {
		return leafId;
	}

	/**
	 * @param leafId the leafId to set
	 */
	public void setLeafId(int leafId) {
		this.leafId = leafId;
	}

	/**
	 * @return the items
	 */
	public Result[] getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Result[] items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	
}
