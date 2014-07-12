package ca.qc.jeuxdegenie.jdgmobile.model.competition;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 */
public class Result {

	private static final String TAG_NAME = "name";
	private static final String TAG_IS_LEAF = "leaf";
	private static final String TAG_LEAF_ID = "leafId";
	private static final String TAG_ITEMS = "items";
	
	private String name;
	private boolean isLeaf;
	private int leafId;
	private List<Result> items = new ArrayList<Result>();
	
	public Result(JSONObject obj) {
		try {
			this.name = obj.getString(TAG_NAME);
			this.isLeaf = (obj.getString(TAG_IS_LEAF).equals("true") ? true : false);
			this.leafId = obj.getInt(TAG_LEAF_ID);
			
			if (!isLeaf) {
				JSONArray items = obj.getJSONArray(TAG_ITEMS);
				
				for (int i=0; i<items.length(); i++) {				
					Result item = new Result(items.getJSONObject(i));
					this.items.add(item);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param isLeaf
	 * @param leafId
	 * @param items
	 */
	public Result(String name, boolean isLeaf, int leafId, List<Result> items) {
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
	public List<Result> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Result> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return name;
	}
}
