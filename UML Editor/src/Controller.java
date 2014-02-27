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
	
	private static Node tempNode; //For use in creating associations

	public static void main(String[] args) {
		nodes = new ArrayList<Node>();
		asses = new ArrayList<Association>();
		view = new View();
		createNode(30, 30);
		createNode(300, 300);
	}

	private static void createNode(int x, int y) {

		// Code that creates a new node object.
		Node node = new Node(x, y);

		if (!isNodeFull(node.getX(), node.getY())
				&& !isNodeFull(node.getX() + node.getWidth(), node.getY())
				&& !isNodeFull(node.getX(), node.getY() + node.getHeight())
				&& !isNodeFull(node.getX() + node.getWidth(), node.getY()
						+ node.getHeight())) {

			nodes.add(node);
			// Call method serveNodes

			serveObjects();
		}
	}

	private static void createAssociation(Node start, Node end, String type) {
		Association newAssoc = new Association(start, type);
		newAssoc.setEndpoint(end);

		asses.add(newAssoc);

		serveObjects();
	}

	public static void moveNode(int x, int y) {

		if (clickValue != 1 && isNodeFull(x, y)) { // Don't try to drag nodes in
													// delete mode

			Node curNode = grabNode(x, y);

			curNode.setPosition(x - curNode.getWidth() / 2,
					y - curNode.getHeight() / 2);
			serveObjects();

		}

	}

	public static void deleteNode(int x, int y) {
		// Conditionals in the if statement are in this order so that the
		// confirmation only appears if you click on a node
		if (isNodeFull(x, y)
				&& view.confirmMessage("Deletion confirmation",
						"Are you sure you want to delete this?")) {
			Node curNode = grabNode(x, y);

			nodes.remove(curNode);
			
			//Also delete associations involving that node
			Iterator<Association> itr = asses.iterator();
			ArrayList<Association> toBeRemoved = new ArrayList<Association>();
			while(itr.hasNext()) {
				Association curAssoc = itr.next();
				
				if(curAssoc.involvesNode(curNode)) {
					toBeRemoved.add(curAssoc); //Can't just remove it outright because we're in the middle of iterating over this list
				}
			}
			
			Iterator<Association> revItr = toBeRemoved.iterator();
			
			while(revItr.hasNext()) { //There has to be a neater way to do this, but I'm too tired to think of one
				asses.remove(revItr.next());
			}

			serveObjects();
		}
	}

	private static void serveObjects() {
		// needs to serve everything simultaneously
		ArrayList<NodeInfo> nodeInfo = new ArrayList<NodeInfo>();
		ArrayList<AssocInfo> assocInfo = new ArrayList<AssocInfo>();
		Iterator<Node> nodeItr = nodes.iterator();
		Iterator<Association> assItr = asses.iterator();

		while (nodeItr.hasNext()) {
			Node curNode = nodeItr.next();
			NodeInfo curInfo = new NodeInfo(curNode.getX(), curNode.getY(),
					curNode.getWidth(), curNode.getHeight());

			nodeInfo.add(curInfo);

		}

		while (assItr.hasNext()) {
			Association curAss = assItr.next();
			curAss.recalculateEndPoints(); //Make sure they point to the right places
			AssocInfo curInfo = new AssocInfo(curAss.getStartX(), curAss.getStartY(), curAss.getEndX(), curAss.getEndY());
			
			assocInfo.add(curInfo);
		}

		view.drawObjects(nodeInfo, assocInfo);

	}

	public static void mouseClick(int x, int y) {
		switch (clickValue) {
		case 0:
			createNode(x, y);
			return; // Class
		case 1:
			deleteNode(x, y);
			return; // Delete
		case 2:
			prepAssoc(x, y, "Aggregation");
			return; // Aggregation
		case 3:
			prepAssoc(x, y, "Composition");
			return; // Composition
		default:
			return;
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

	public static Node grabNode(int x, int y) {
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
	
	private static void prepAssoc(int x, int y, String type) {
		if (tempNode != null && isNodeFull(x, y)) {
			createAssociation(tempNode, grabNode(x, y), type);
			tempNode = null;
		} else if (isNodeFull(x, y)) {
			tempNode = grabNode(x, y);
		}
	}
	
	private static void clearClickMode() { //Make sure we don't store clickmode specific stuff when changing clickmodes
		tempNode = null;
	}

	public static void classButton() {
		// Model
		clearClickMode();
		clickValue = 0;
	}

	public static void deleteButton() {
		clearClickMode();
		clickValue = 1;
	}
	
	public static void aggButton() {
		clearClickMode();
		clickValue = 2;
	}
	
	public static void compButton() {
		clearClickMode();
		clickValue = 3;
	}

}
