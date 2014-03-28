
import java.io.Serializable;
import java.util.ArrayList;

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
	private ArrayList<String> attributes;
	private ArrayList<String> methods;

public Node(int x, int y) {
	width = 80;
	height = 80;
	setPosition(x, y);
	name = "Class";
	attributes = new ArrayList<String>();
	methods = new ArrayList<String>();

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

public void setHeight (int height) {
	this.height = height;
}

public ArrayList<String> getAttributes() {
	
	return attributes;
	
}

public void setAttributes(ArrayList<String> attributes) {
	for (int i = 0; i < attributes.size(); i++){
		System.out.println(attributes.get(i).isEmpty());
		if(attributes.get(i).isEmpty() ){
			System.out.println("remove");
			attributes.remove(i);
		}
	}
	attributes.trimToSize();
	this.attributes = attributes;
	
}

public ArrayList<String> getMethods() {
	
	return methods;
	
}

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