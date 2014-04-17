
import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {

	/**
	 * 
	 */
	// I put in this id to shut up the warning, don't actually know if we Need it
	private static final long serialVersionUID = 1L;
	private static long nextId = 0;
	private long nodeId;
	private int origX; //These keep track of where the node was actually set to be for copying purposes.
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
 * x-coordinate of the new node
 * @param y
 * y-coordinate of the new node
 */
public Node(int x, int y) {
	width = 80;
	height = 80;
	setPosition(x, y);
	name = "Class";
	attributes = new ArrayList<String>();
	methods = new ArrayList<String>();
	
	nodeId = nextId++;

	findDimensions();
 }

/**
 * Moves and snaps node to grid
 * 
 * @param x
 * x-coordinate of the new node
 * @param y
 * y-coordinate of the new node
 */
public void setPosition(int x, int y) {
	
	// x or y % 10 allows snapping to a grid.
	xCoor = x - (x%10);
	yCoor = y - (y%10);
	
	origX = x;
	origY = y;
	
	//xCoor = x;
	//yCoor = y;
}

public int getX() {
	
	return xCoor;
	
}

public int getY() {
	
	return yCoor;
	
}

public int getWidth() {
	
	return width;
	
}

public int getHeight() {
	
	return height;
	
}

public String getName() {
	
	return name;
	
}

/**
 * Sets width of node while maintaining a minimum of 80 and a maximum of 200
 * @param width
 */
public void setWidth(int width) {
	
	if(width > 200){
		
		this.width = 200;
		
	} else if(width < 80){
		
		this.width = 80;
		
	} else{
	
		this.width = width;

	}
}

public void setHeight (int height) {
	this.height = height;
}

public ArrayList<String> getAttributes() {
	
	return attributes;
	
}

/**
 * First remove any empty strings from the arraylist, trim it, then save it to the node
 * 
 * @param attributes ArrayList of strings, each being an attribute of the node
 */
public void setAttributes(ArrayList<String> attributes) {
	for (int i = 0; i < attributes.size(); i++){
		if(attributes.get(i).isEmpty() ){
			attributes.remove(i);
		}
	}
	attributes.trimToSize();
	this.attributes = attributes;
	
}

public ArrayList<String> getMethods() {
	
	return methods;
	
}

/**
 * First remove any empty strings from the arraylist, trim it, then save it to the node
 * 
 * @param methods ArrayList of strings, each being a method of the node
 */
public void setMethods(ArrayList<String> methods) {
	for (int i = 0; i < methods.size(); i++){
		if(methods.get(i).isEmpty()){
			methods.remove(i);
		}
	}
	methods.trimToSize();
	this.methods = methods;
	
}

public void setName(String name) {
	
	this.name = name;
	
}

/**
 * Finds the center coordinates of the top, bottom, left, and right sides of the node
 */
public void findDimensions() {
	
	leftX = xCoor - width/2;
	rightX = xCoor + width/2;
	topY = yCoor - height/2;
	bottomY = yCoor + height/2;
	
}

/**
 * Finds the direct center of the node
 */
public void findCenter() {
	
	xCoor = xCoor + width/2;
	yCoor = yCoor + height/2;
	
}
 /**
 * Prints out all the basic information about the node
 * Useful for testing
 */
public void printNodeInfo() {
	
    System.out.println("Name:"+ name );
    System.out.println("Attributes:" + attributes );
    System.out.println("Methods:" + methods );
    System.out.println("Width:" + width);
    System.out.println("Height:" + height);
    System.out.println("Position:" + xCoor + "," + yCoor);
    System.out.println("Dimensions: Left: " + leftX + ", Right: " + rightX + ", Top: " + topY + ", Bottom: " + bottomY);
    System.out.println();
    
 }

/**
 * Decides if nodes are equal based on their internal ids.
 * 
 * @param node
 * 		Node to be compared.
 * @return
 * 		True if the nodes are equal
 */
public boolean equals(Node node) {
	if(node == null)
		return false;
	return this.nodeId == node.nodeId;
}

/**
 * Creates a copy of the Node.
 * @return A copy of the Node.
 */
public Node copy() {
	Node retVal = new Node(origX, origY);
	retVal.setName(this.getName());
	retVal.setAttributes(this.getAttributes());
	retVal.setMethods(this.getMethods());
	return retVal;
}
}