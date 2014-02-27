public class Association {
	private String associationType;
	private Node startNode;
	private Node endNode;
	private int startX;
	private int startY;
	private int endX;
	private int endY;


public Association(Node sNode, String type){
	associationType = type;
	startNode = sNode;
	setStartPosition(startNode.getX(), startNode.getY());

 }

public void setEndpoint( Node eNode){
	endNode = eNode;
	setEndPosition(endNode.getX(), endNode.getY());
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
	setStartPosition(startNode.getX(), startNode.getY());
	setEndPosition(endNode.getX(), endNode.getY());
}

public void printAssociation(){
    System.out.println("Type:"+ associationType );
    System.out.println("Start Position:" + startX + "," + startY);
    System.out.println("End Position:" + endX + "," + endY);
    System.out.println();
    
 }

}