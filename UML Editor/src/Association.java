public class Association {
	String associationType;
	Node startNode;
	Node endNode;
	int startX;
	int startY;
	int endX;
	int endY;


public Association(Node sNode, String type){
	associationType = type;
	startNode = sNode;
	setStartPosition(startNode.xCoor, startNode.yCoor);

 }

public void setEndpoint( Node eNode){
	endNode = eNode;
	setEndPosition(endNode.xCoor, endNode.yCoor);
}
public void setStartPosition(int x, int y){
	startX = x;
	startY = y;
}

public void setEndPosition(int x, int y){
	endX = x;
	endY = y;
}

public void updatePosition(){
	setStartPosition(startNode.xCoor, startNode.yCoor);
	setEndPosition(endNode.xCoor, endNode.yCoor);
}

public void printAssociation(){
    System.out.println("Type:"+ associationType );
    System.out.println("Start Position:" + startX + "," + startY);
    System.out.println("End Position:" + endX + "," + endY);
    System.out.println();
    
 }

}