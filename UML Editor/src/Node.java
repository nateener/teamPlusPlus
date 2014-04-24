import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int origX; // These keep track of where the node was actually set to be for copying purposes.
	private int origY;
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
	private int leftX;
	private int rightX;
	private int topY;
	private int bottomY;
	private String name;
	private ArrayList<String> attributes;
	private ArrayList<String> methods;

	/**
	 * Constructor for the node class
	 * 
	 * @param x
	 *            x-coordinate of the new node
	 * @param y
	 *            y-coordinate of the new node
	 */
	public Node(int x, int y) {
		width = 80;
		height = 80;
		setPosition(x, y);
		name = "Class";
		attributes = new ArrayList<String>();
		methods = new ArrayList<String>();

		findDimensions();
	}

	/**
	 * Moves and snaps node to grid
	 * 
	 * @param x
	 *            x-coordinate of the new node
	 * @param y
	 *            y-coordinate of the new node
	 */
	public void setPosition(int x, int y) {

		// x or y % 10 allows snapping to a grid.
		xCoor = x - (x % 10);
		yCoor = y - (y % 10);

		origX = x;
		origY = y;

		// xCoor = x;
		// yCoor = y;
	}
	
	/**
	 * Gets the node's x coordinate
	 * @return
	 * 	The x coordinate
	 */
	public int getX() {

		return xCoor;

	}

	/**
	 * Gets the node's y coordinate
	 * @return
	 * 	The y coordinate
	 */
	public int getY() {

		return yCoor;

	}

	/**
	 * Gets the node's width
	 * @return
	 * 	The width
	 */
	public int getWidth() {

		return width;

	}

	/**
	 * Gets the node's height
	 * @return
	 * 	The height
	 */
	public int getHeight() {

		return height;

	}

	/**
	 * Gets the node's name
	 * @return
	 * 	The node's name
	 */
	public String getName() {

		return name;

	}

	/**
	 * Sets width of node while maintaining a minimum of 80 and a maximum of 200
	 * 
	 * @param width
	 */
	public void setWidth(int width) {

		if (width > 200) {

			this.width = 200;

		} else if (width < 80) {

			this.width = 80;

		} else {

			this.width = width;

		}
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<String> getAttributes() {

		return attributes;

	}

	/**
	 * First remove any empty strings from the arraylist, trim it, then save it to the node
	 * 
	 * @param attributes
	 *            ArrayList of strings, each being an attribute of the node
	 */
	public void setAttributes(ArrayList<String> attributes) {
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i).isEmpty()) {
				attributes.remove(i);
			}
		}
		attributes.trimToSize();
		this.attributes = attributes;

	}

	/**
	 * Get the methods of a node.
	 * 
	 * @return The methods stored on a node
	 */
	public ArrayList<String> getMethods() {

		return methods;

	}

	/**
	 * First remove any empty strings from the arraylist, trim it, then save it to the node
	 * 
	 * @param methods
	 *            ArrayList of strings, each being a method of the node
	 */
	public void setMethods(ArrayList<String> methods) {
		for (int i = 0; i < methods.size(); i++) {
			if (methods.get(i).isEmpty()) {
				methods.remove(i);
			}
		}
		methods.trimToSize();
		this.methods = methods;

	}

	/**
	 * Sets the node's name
	 * 
	 * @param name
	 *            The new name
	 */
	public void setName(String name) {

		this.name = name;

	}

	/**
	 * Finds the center coordinates of the top, bottom, left, and right sides of the node
	 */
	public void findDimensions() {

		leftX = xCoor - width / 2;
		rightX = xCoor + width / 2;
		topY = yCoor - height / 2;
		bottomY = yCoor + height / 2;

	}

	/**
	 * Finds the direct center of the node
	 */
	public void findCenter() {

		xCoor = xCoor + width / 2;
		yCoor = yCoor + height / 2;

	}

	/**
	 * Prints out all the basic information about the node Useful for testing
	 */
	public void printNodeInfo() {

		System.out.println("Name:" + name);
		System.out.println("Attributes:" + attributes);
		System.out.println("Methods:" + methods);
		System.out.println("Width:" + width);
		System.out.println("Height:" + height);
		System.out.println("Position:" + xCoor + "," + yCoor);
		System.out.println("Original Position:" + origX + "," + origY);
		System.out.println("Dimensions: Left: " + leftX + ", Right: " + rightX
				+ ", Top: " + topY + ", Bottom: " + bottomY);
		System.out.println();

	}

	/**
	 * Decides if nodes are equal based on their internal ids.
	 * 
	 * @param node
	 *            Node to be compared.
	 * @return True if the nodes are equal
	 */
	/*
	 * public boolean equals(Node node) { if(node == null) return false; return this.nodeId == node.nodeId; }
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Node otherNode = (Node) obj;

		if (!this.name.equals(otherNode.name))
			return false;

		if (!this.methods.equals(otherNode.methods))
			return false;

		if (!this.attributes.equals(otherNode.attributes))
			return false;

		if ((this.origX != otherNode.origX) || (this.origY != otherNode.origY))
			return false;
		
		return true;
	}

	/**
	 * Creates a copy of the Node.
	 * 
	 * @return A copy of the Node.
	 */
	@SuppressWarnings("unchecked")
	public Node copy() {
		Node retVal = new Node(origX, origY);
		retVal.setName(this.getName());
		retVal.setAttributes((ArrayList<String>) this.getAttributes().clone());
		retVal.setMethods((ArrayList<String>) this.getMethods().clone());

		retVal.width = this.width;
		retVal.height = this.height;

		return retVal;
	}
}