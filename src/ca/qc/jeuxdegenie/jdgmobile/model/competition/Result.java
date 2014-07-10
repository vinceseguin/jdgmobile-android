package ca.qc.jeuxdegenie.jdgmobile.model.competition;

/**
 * 
 */
public class Result {

	private String name;
	private boolean isLeaf;
	private int leafId;
	private Result[] items;
	
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
	
}
