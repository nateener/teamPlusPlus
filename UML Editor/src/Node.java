
import java.io.Serializable;

public class Node implements Serializable {

	/**
	 * 
	 */
	// I put in this id to shut up the warning, don't actually know if we Need it
	private static final long serialVersionUID = 1L;
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
	private int leftX;
	private int rightX;
	private int topY;
	private int bottomY;
	private String name;
	private String attributes;
	private String methods;

public Node(int x, int y) {
	width = 80;
	height = 80;
	setPosition(x, y);
	name = "Class";

	findDimensions();
 }

public void setPosition(int x, int y) {
	
	// x or y % 10 allows snapping to a grid.
	xCoor = x - (x%10);
	yCoor = y - (y%10);
	
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

public void setWidth(int width) {
	
	if(width > 200){
		
		this.width = 200;
		
	} else if(width < 80){
		
		this.width = 80;
		
	} else{
	
		this.width = width;

	}
}

public String getAttributes() {
	
	return attributes;
	
}

public void setAttributes(String attributes) {
	
	this.attributes = attributes;
	
}

public String getMethods() {
	
	return methods;
	
}

public void setMethods(String methods) {
	
	this.methods = methods;
	
}

public void setName(String name) {
	
	this.name = name;
	
}


public void findDimensions() {
	
	leftX = xCoor - width/2;
	rightX = xCoor + width/2;
	topY = yCoor - height/2;
	bottomY = yCoor + height/2;
	
}

public void findCenter() {
	
	xCoor = xCoor + width/2;
	yCoor = yCoor + height/2;
	
}

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
}