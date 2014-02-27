import java.util.ArrayList;
import java.util.Iterator;

/*
 * TODO PLACEHOLDER FOR THE CONTROLLER CLASS.
 */
public class Controller {

	private static ArrayList<Node> nodes;
	private static ArrayList<Association> asses;
	private static int clickValue = -1;

	private static View view;

	public static void main(String[] args) {
		nodes = new ArrayList<Node>();
		view = new View();
		createNode(30, 30);
		createNode(300, 300);
	}

	private static void createNode(int x, int y) {

		// Code that creates a new node object.
		Node node = new Node(x, y);

		if(!isNodeFull(node.getX(), node.getY()) && 
		   !isNodeFull(node.getX() + node.getWidth(), node.getY()) &&
		   !isNodeFull(node.getX(), node.getY() + node.getHeight()) &&
		   !isNodeFull(node.getX()+node.getWidth(), node.getY()+node.getHeight())
				){
		
			nodes.add(node);
			// Call method serveNodes

			serveNodes();
		}
	}

	public static void moveNode(int x, int y){
		
		if(clickValue != 1 && isNodeFull(x, y)){ //Don't try to drag nodes in delete mode
			
			Node curNode = grabNode(x, y);
			
			curNode.setPosition(x - curNode.getWidth()/2, y - curNode.getHeight()/2);
			serveNodes();
			
		}
		
	}
	
	public static void deleteNode(int x, int y) {
		//Conditionals in the if statement are in this order so that the confirmation only appears if you click on a node
		if(isNodeFull(x, y) && view.confirmMessage("Deletion confirmation", "Are you sure you want to delete this?")) {
			Node curNode = grabNode(x, y);
			
			nodes.remove(curNode);
			
			serveNodes();
		}
	}
	
 	private static void serveNodes() {
		// needs to serve everything simultaneously
		ArrayList<NodeInfo> info = new ArrayList<NodeInfo>();
		Iterator<Node> itr = nodes.iterator();

		while (itr.hasNext()) {
			Node curNode = itr.next();
			NodeInfo curInfo = new NodeInfo(curNode.getX(), curNode.getY(),
					curNode.getWidth(), curNode.getHeight());

			info.add(curInfo);

		}
		view.drawObjects(info);

	}

	public static void mouseClick(int x, int y) {
		/*if (clickValue == 0) { // class

			createNode(x, y);

		}*/
		
		switch (clickValue) {
		case 0: createNode(x, y); return; //Class
		case 1: deleteNode(x, y); return; //Delete
		default: return;
		}
	}

	public static boolean isNodeFull(int x, int y) {
		Iterator<Node> itr = nodes.iterator();

		while (itr.hasNext()) {
			Node curNode = itr.next();

			if (x >= curNode.getX() && x <= curNode.getX() + curNode.getWidth()) {

				if (y >= curNode.getY()
						&& y <= curNode.getY() + curNode.getHeight()) {

					return true;
				}

			}

		} // end loop

		return false;
	}

	public static Node grabNode (int x, int y){
		Iterator<Node> itr = nodes.iterator();
		
		while (itr.hasNext()) {
			Node curNode = itr.next();

			if (x >= curNode.getX() && x <= curNode.getX() + curNode.getWidth()) {

				if (y >= curNode.getY()
						&& y <= curNode.getY() + curNode.getHeight()) {

					return curNode;
				}

			}

		} // end loop

		
		return null;
	}
	
 	public static void classButton() {
		// Model
		clickValue = 0;
	}
 	
 	public static void deleteButton() {
 		clickValue = 1;
 	}

}
