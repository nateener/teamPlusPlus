public class Node {
	private int xCoor;
	private int yCoor;
	private String name;
	private String attributes;
	private String methods;
	private int width;
	private int height;
	private int leftX;
	private int rightX;
	private int topY;
	private int bottomY;


public Node(int x, int y){
	width = 80;
	height = 80;
	xCoor = x;
	yCoor = y;
	findDimensions();
 }

public void setPosition(int x, int y){
	xCoor = x;
	yCoor = y;
}

public int getX(){
	
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

public void findDimensions(){
	leftX = xCoor - width/2;
	rightX = xCoor + width/2;
	topY = yCoor - height/2;
	bottomY = yCoor + height/2;
}

public void findCenter(){
	xCoor = xCoor + width/2;
	yCoor = yCoor + height/2;
}

public void printNodeInfo(){
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