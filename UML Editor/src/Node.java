public class Node {
	int xCoor;
	int yCoor;
	String name;
	String attributes;
	String methods;
	int width;
	int height;
	int leftX;
	int rightX;
	int topY;
	int bottomY;


public Node(int x, int y){
	width = 20;
	height = 10;
	xCoor = x + width/2;
	yCoor = y + height/2;
	findDimensions();
 }

public void setPosition(int x, int y){
	xCoor = x;
	yCoor = y;
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