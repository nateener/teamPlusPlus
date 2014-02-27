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
	setStartPosition();
 }

public void setEndpoint(Node eNode){
	endNode = eNode;
	setEndPosition();
}

public void recalculateEndPoints() {
	setStartPosition();
	setEndPosition();
}

public boolean involvesNode(Node in) {
	return (startNode.equals(in) || endNode.equals(in));
}

public int getStartX() {
	return startX;
}

public int getStartY() {
	return startY;
}

public int getEndX() {
	return endX;
}

public int getEndY() {
	return endY;
}

public String getAssociationType() {
	return associationType;
}

private void setStartPosition(){
	if (startNode != null) {
		startX = startNode.getX() + startNode.getWidth() / 2;
		startY = startNode.getY() + startNode.getHeight();
	}
}

private void setEndPosition(){
	if(endNode != null) {
		endX = endNode.getX() + endNode.getWidth() / 2;
		endY = endNode.getY() + endNode.getHeight();
	}
}

public void printAssociation(){
    System.out.println("Type:"+ associationType );
    System.out.println("Start Position:" + startX + "," + startY);
    System.out.println("End Position:" + endX + "," + endY);
    System.out.println();
    
 }

}