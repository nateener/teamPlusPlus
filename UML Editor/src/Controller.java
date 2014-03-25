
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * TODO PLACEHOLDER FOR THE CONTROLLER CLASS.
 */
public class Controller {

	public static ArrayList<Node> nodes;
	public static ArrayList<Relationship> rels;
	public static int clickValue = -1; 

	public static View view;
	public static Node tempNode; // For use in creating relationships
	private static String curFile = "";
	
	
	

	public static void main(String[] args) {
		
		nodes = new ArrayList<Node>();
		rels = new ArrayList<Relationship>();
		view = new View();
		serveObjects();
		
	}

	/*
	 * Node Functions
	 */

/*
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


	public static void moveNode(int x, int y) {
		// Don't try to drag nodes                  unless in class mode
		if ((clickValue == 0 || clickValue == 1) && isNodeFull(x, y)) { 
													
			Node curNode = grabNode(x, y);
			curNode.setPosition(x - curNode.getWidth() / 2, y - curNode.getHeight() / 2);
			serveObjects();
			
		}

	}

	public static void editNode(Node node) {
		
		String newName = view.showCurInfo(node.getName(), node.getAttributes(), node.getMethods());
		
		int newNameLength = newName.length();
		node.setWidth(newNameLength * 12);
		
		node.setName(newName);
		serveObjects();
		
	}

	
	public static void deleteNode(int x, int y) {
		
		// Conditionals in the if statement are in this order so that the
		// confirmation only appears if you click on a node
		if (isNodeFull(x, y)
				&& view.confirmMessage("Deletion confirmation",
						"Are you sure you want to delete this node?")) {
			Node curNode = grabNode(x, y);

			nodes.remove(curNode);

			// Also delete relationships involving that node
			Iterator<Relationship> itr = rels.iterator();
			ArrayList<Relationship> toBeRemoved = new ArrayList<Relationship>();
			while (itr.hasNext()) {
				Relationship curRel = itr.next();

				if (curRel.involvesNode(curNode)) {
					toBeRemoved.add(curRel); // Can't just remove it outright
												// because we're in the middle
												// of iterating over this list
				}
			}

			Iterator<Relationship> revItr = toBeRemoved.iterator();

			while (revItr.hasNext()) { // There has to be a neater way to do
										// this, but I'm too tired to think of
										// one

				rels.remove(revItr.next());
			}

			serveObjects();
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
		
	/*
	 * Relationship Functions
	 */
/*
	private static void createRelationship(Node start, Node end, String type) {
		
		Relationship newRel = new Relationship(start, type);
		newRel.setEndpoint(end);

		rels.add(newRel);

		serveObjects();
		
	}

	private static void deleteRelationship(int x, int y) {
		
		if (isRelFull(x, y)){
			Relationship curRel = grabRel(x, y);
			String message = "Are you sure you want to delete this " + curRel.getRelationshipType() + " between node " + curRel.getStartNode().getName() + " and node " + curRel.getEndNode().getName() + "?";
			Boolean delete = view.confirmMessage("Deletion confirmation",
						message);
		
			
			if ( delete ){
			rels.remove(curRel);
			serveObjects();
			}
		}
		
	}

	public static boolean isRelFull(int x, int y) {
		
		Iterator<Relationship> itr = rels.iterator();

		while (itr.hasNext()) {
			Relationship curRel = itr.next();

			if ((x <= curRel.getStartX() + 10 && x >= curRel.getStartX() - 10
					&& y <= curRel.getStartY() + 10 && y >= curRel.getStartY() - 10)
					|| (x <= curRel.getEndX() + 10
							&& x >= curRel.getEndX() - 10
							&& y <= curRel.getEndY() + 10 && y >= curRel
							.getEndY() - 10)) {
				return true;
			}

		}

		return false;

	}

	public static Relationship grabRel(int x, int y) {

		Iterator<Relationship> itr = rels.iterator();

		while (itr.hasNext()) {
			Relationship curRel = itr.next();

			if ((x <= curRel.getStartX() + 10 && x >= curRel.getStartX() - 10
					&& y <= curRel.getStartY() + 10 && y >= curRel.getStartY() - 10)
					|| (x <= curRel.getEndX() + 10
							&& x >= curRel.getEndX() - 10
							&& y <= curRel.getEndY() + 10 && y >= curRel
							.getEndY() - 10)) {
				return curRel;
			}

		}

		return null;

	}
	
	private static void prepRel(int x, int y, String type) {
		
		if (tempNode != null && NodeController.isNodeFull(x, y)) {
			createRelationship(tempNode, NodeController.grabNode(x, y), type);
			tempNode = null;
		} else if (NodeController.isNodeFull(x, y)) {
			tempNode = NodeController.grabNode(x, y);
		}
		
		serveObjects();
		
	}
	
	*/
	
	/*
	 * View Functions
	 */

	public static void serveObjects() {
		
		// needs to serve everything simultaneously
		ArrayList<NodeInfo> nodeInfo = new ArrayList<NodeInfo>();
		ArrayList<RelInfo> relInfo = new ArrayList<RelInfo>();
		Iterator<Node> nodeItr = nodes.iterator();
		Iterator<Relationship> relItr = rels.iterator();

		while (nodeItr.hasNext()) {
			Node curNode = nodeItr.next();
			NodeInfo curInfo = new NodeInfo(curNode.getX(), curNode.getY(),
					curNode.getWidth(), curNode.getHeight(), curNode.getName());
			if(curNode.equals(tempNode)){
				curInfo.setHigh(true);
			}
			nodeInfo.add(curInfo);

		}

		while (relItr.hasNext()) {
			Relationship curRel = relItr.next();
			curRel.recalculateEndPoints(); // Make sure they point to the right
											// places
			RelInfo curInfo = new RelInfo(curRel.getStartX(),
					curRel.getStartY(), curRel.getEndX(), curRel.getEndY(), curRel.isSelfRel(), curRel.getRelationshipType());

			relInfo.add(curInfo);
		}
		NodeInfo halfRel = null;
		if(tempNode != null){
			halfRel = new NodeInfo(tempNode.getX(), tempNode.getY(), tempNode.getWidth(), tempNode.getHeight(), tempNode.getName());
		}
		view.drawObjects(nodeInfo, relInfo, halfRel);

	}

	public static void mouseClick(int x, int y) {
		
		switch (clickValue) {
		case 0:
			return; // Class
		case 1:
			NodeController.createNode(x, y);
			return; // Class
		case 2:
			NodeController.deleteNode(x, y);
			RelationshipController.deleteRelationship(x, y);
			return; // Delete
		case 3:
			RelationshipController.prepRel(x, y, "Aggregation");
			return; // Aggregation
		case 4:
			RelationshipController.prepRel(x, y, "Composition");
			return; // Composition
		case 5:
			RelationshipController.prepRel(x, y, "Generalization");
			return; // Generalization
		case 6:
			RelationshipController.prepRel(x, y, "Association");
			return; // Association
		case 7:
			RelationshipController.prepRel(x, y, "Depend");
			return; // Depend
		case 8:
			RelationshipController.prepRel(x, y, "Implements");
			return; // Implements	
		case 9:
			RelationshipController.prepRel(x, y, "Basic");
			return; // Basic		
		default:
			return;
			
		}
	}

	public static void mouseClickRight(int x, int y) {
		
		switch (clickValue) {
		case 0:
		case 1:
			if(NodeController.isNodeFull(x, y)){
				Node node = NodeController.grabNode(x, y);
				NodeController.editNode(node);
				
			}
			return; // Class
		default:
			return;
		}
		
	}
	
	private static void clearClickMode() { // Make sure we don't store clickmode
											// specific stuff when changing								
											// clickmodes
		tempNode = null;
		serveObjects();
		
	}

	public static void selectorButton() {
		
		// Model
		clearClickMode();
		clickValue = 0;
		
	}
	
	public static void classButton() {
		
		// Model
		clearClickMode();
		clickValue = 1;
		
	}

	public static void deleteButton() {
		
		clearClickMode();
		clickValue = 2;
		
	}

	public static void aggButton() {
		
		clearClickMode();
		clickValue = 3;
		
	}

	public static void compButton() {
		
		clearClickMode();
		clickValue = 4;
		
	}
	
	public static void genButton() {
		
		clearClickMode();
		clickValue = 5;
		
	}

	public static void assButton() {
		
		clearClickMode();
		clickValue = 6;
		
	}
	
	public static void dependButton() {
		
		clearClickMode();
		clickValue = 7;
		
	}
	
	public static void impButton() {
		
		clearClickMode();
		clickValue = 8;
		
	}
	
	public static void basicButton() {
		
		clearClickMode();
		clickValue = 9;
		
	} 
	
	
	public static void exit() {
		
		if(view.confirmMessage("Exit confirmation",
						"Are you sure you want to exit?")){
			System.exit(0);
		}
		
	}

	
	
	/*
	 * Other Functions
	 */
	
	public static void save(String file) {
		
		if(file.equals("")){
			if(curFile.equals("")){
				view.showFileSaver();
				
			} else  {
				file = curFile;
			}
		}
		curFile = file;
		try
	      {
	        FileOutputStream fileOut =
	        new FileOutputStream(file);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(nodes);
	        out.writeObject(rels);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized data is saved in H:/save.uml");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		
	}
	
	public static void load(String file) {
		
		nodes.clear();
		rels.clear();
		System.out.println(file);
		curFile = file;
		try
	      {
	         FileInputStream fileIn = new FileInputStream(file);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         nodes = (ArrayList<Node>) in.readObject();
	         rels = (ArrayList<Relationship>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
		
		serveObjects();
	}

	public static void newUML() {
		
		nodes.clear();
		rels.clear();
		curFile = "";
		clearClickMode();
		
	}
	
}
