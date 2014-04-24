import java.io.Serializable;

public class Relationship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String relationshipType;
	private String startDetail;
	private String endDetail;
	private Node startNode;
	private Node endNode;
	private int startX;
	private int startY;
	private int endX;
	private int endY;

	/**
	 * Relationship constructor
	 * 
	 * @param sNode
	 *            starting Node object
	 * @param type
	 *            Type of relationship
	 */
	public Relationship(Node sNode, String type) {

		relationshipType = type;
		startNode = sNode;
		setStartPosition();

	}

	/**
	 * Sets end-point of relationship
	 * 
	 * @param eNode
	 *            end-point Node object
	 */
	public void setEndpoint(Node eNode) {

		endNode = eNode;
		setEndPosition();

	}

	/**
	 * Updates the positions of both points of the relationship
	 */
	public void recalculateEndPoints() {

		setStartPosition();
		setEndPosition();

		if (isSelfRel()) {
			startX = startNode.getX();
			startY = startNode.getY();

		}
	}

	/**
	 * Checks if the given node has anything to do with the relationship
	 * 
	 * @param in
	 *            Node object to be checked
	 * @return Bool if it involves the node
	 */
	public boolean involvesNode(Node in) {

		return (startNode.equals(in) || endNode.equals(in));

	}

	/**
	 * Gets the starting node
	 * @return
	 * 		The starting node
	 */
	public Node getStartNode() {

		return startNode;

	}

	/**
	 * Gets the ending node
	 * @return
	 * 		The ending node
	 */
	public Node getEndNode() {

		return endNode;

	}
	
	/**
	 * Gets the starting x coordinate
	 * @return
	 * 		The starting x coordinate
	 */
	public int getStartX() {

		return startX;

	}

	/**
	 * Gets the starting y coordinate
	 * @return
	 * 		The starting y coordinate
	 */
	public int getStartY() {

		return startY;

	}

	/**
	 * Gets the ending x coordinate
	 * @return
	 * 		The ending x coordinate
	 */
	public int getEndX() {

		return endX;

	}

	/**
	 * Gets the ending y 
	 * @return
	 * 		The ending y coordinate
	 */
	public int getEndY() {

		return endY;

	}

	/**
	 * Gets the relationship type
	 * @return
	 * 		The relationship type
	 */
	public String getRelationshipType() {

		return relationshipType;

	}

	/**
	 * @return Boolean if the relationship is with itself
	 */
	public boolean isSelfRel() {

		return startNode.equals(endNode);

	}

	/**
	 * Sets the start coordinates of the relationship based on it's starting
	 * node
	 */
	private void setStartPosition() {

		if (startNode != null) {
			startX = startNode.getX() + startNode.getWidth() / 2;
			startY = startNode.getY() + startNode.getHeight();
		}

	}

	/**
	 * Sets the end coordinates of the relationship based on it's ending node
	 */
	private void setEndPosition() {

		if (endNode != null) {
			endX = endNode.getX() + endNode.getWidth() / 2;
			endY = endNode.getY() + endNode.getHeight();
		}

	}

	/**
	 * Prints out information about the relationship Useful for testing
	 */
	public void printRelationship() {

		System.out.println("Type:" + relationshipType);
		System.out.println("Start Position:" + startX + "," + startY);
		System.out.println("End Position:" + endX + "," + endY);
		System.out.println();

	}

	/**
	 * Creates a copy of the current relationship between the two given nodes.
	 * 
	 * @param startNode
	 *            The start node for the copy
	 * @param endNode
	 *            The end node for the copy
	 * @return A copy of the relationship
	 */
	public Relationship copy(Node startNode, Node endNode) {
		Relationship retVal = new Relationship(startNode, this.relationshipType);
		retVal.setEndpoint(endNode);
		// TODO: See if there's a way to make this work without having to pass
		// in copies of the end nodes
		return retVal;
	}

	/**
	 * Gets the start detail
	 * @return
	 * 		The start detail
	 */
	public String getStartDetail() {
		return startDetail;
	}

	/**
	 * Sets the start detail
	 * @param startDetail
	 * 		The new start detail
	 */
	public void setStartDetail(String startDetail) {
		this.startDetail = startDetail;
	}

	/**
	 * Gets the end detail
	 * @return
	 * 		The end detail
	 */
	public String getEndDetail() {
		return endDetail;
	}

	/**
	 * Sets the end detail
	 * @param endDetail
	 * 		
	 */
	public void setEndDetail(String endDetail) {
		this.endDetail = endDetail;
	}

}