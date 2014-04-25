/**
 * File: RelInfo.java
 * Project: UML Editor
 * Iteration: 3
 * Description: A data structure for passing relationship information to the View formated for rendering graphically.
 */

public class RelInfo {
	private int startX;
	private int startY;
	private int endX;
	private int endY;

	private boolean selfRel;
	private String relType;
	private String startDetail;
	private String endDetail;
	private String orientation;

	/**
	 * RelInfo constructor
	 * 
	 * @param startX
	 *            starting x-coordinate
	 * @param startY
	 *            starting y-coordinate
	 * @param endX
	 *            ending x-coordinate
	 * @param endY
	 *            ending y-coordinate
	 * @param self
	 *            if it has a relationship with itself
	 * @param type
	 *            type of the relationship
	 */
	public RelInfo(int startX, int startY, int endX, int endY, boolean self,
			String type, String startDetail, String endDetail,
			String orientation) {

		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.setSelfRel(self);
		this.setRelType(type);
		this.setStartDetail(startDetail);
		this.setEndDetail(endDetail);
		this.orientation = orientation;
	}

	/**
	 * Gets the starting x
	 * 
	 * @return The starting x
	 */
	public int getStartX() {

		return startX;

	}

	/**
	 * Sets the starting x
	 * 
	 * @param startX
	 *            The new starting x
	 */
	public void setStartX(int startX) {

		this.startX = startX;

	}

	/**
	 * Gets the starting y
	 * 
	 * @return The starting y
	 */
	public int getStartY() {

		return startY;

	}

	/**
	 * Sets the starting y
	 * 
	 * @param startY
	 *            The new starting y
	 */
	public void setStartY(int startY) {

		this.startY = startY;

	}

	/**
	 * Gets the ending x
	 * 
	 * @return The ending x
	 */
	public int getEndX() {

		return endX;

	}

	/**
	 * Sets the ending x
	 * 
	 * @param endX
	 *            The new ending x
	 */
	public void setEndX(int endX) {

		this.endX = endX;

	}

	/**
	 * Gets the ending y
	 * 
	 * @return The ending y
	 */
	public int getEndY() {

		return endY;

	}

	/**
	 * Sets the ending y
	 * 
	 * @param endY
	 *            The new ending y
	 */
	public void setEndY(int endY) {

		this.endY = endY;

	}

	/**
	 * Checks if the relationship is a self relationship
	 * 
	 * @return True if it is a self relationship
	 */
	public boolean isSelfRel() {

		return selfRel;

	}

	/**
	 * Sets if the relationship is a self relationship
	 * 
	 * @param selfRel
	 *            If the relationship is a self relationship
	 */
	public void setSelfRel(boolean selfRel) {

		this.selfRel = selfRel;

	}

	/**
	 * Gets the relationship type
	 * 
	 * @return The relationship type
	 */
	public String getRelType() {

		return relType;

	}

	/**
	 * Sets the relationship type
	 * 
	 * @param relType
	 *            The new relationship type
	 */
	public void setRelType(String relType) {

		this.relType = relType;

	}

	/**
	 * Gets the start detail
	 * 
	 * @return The start detail
	 */
	public String getStartDetail() {
		return startDetail;
	}

	/**
	 * Sets the start detail
	 * 
	 * @param startDetail
	 *            The new start detail
	 */
	public void setStartDetail(String startDetail) {
		this.startDetail = startDetail;
	}

	/**
	 * Gets the end detail
	 * 
	 * @return The end detail
	 */
	public String getEndDetail() {
		return endDetail;
	}

	/**
	 * Sets the end detail
	 * 
	 * @param endDetail
	 *            The new end detail
	 */
	public void setEndDetail(String endDetail) {
		this.endDetail = endDetail;
	}

	/**
	 * Gets the orientation
	 * 
	 * @return The orientation
	 */
	public String getOrientation() {
		return orientation;
	}

}