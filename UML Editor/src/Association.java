<<<<<<< HEAD
import java.io.Serializable;

public class Association implements Serializable {
=======
public class Association {
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
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
<<<<<<< HEAD
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

=======
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

>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
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
<<<<<<< HEAD
}

public boolean isSelfAss(){
	
	return startNode.equals(endNode);
	
=======
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
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