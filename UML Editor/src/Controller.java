
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

	private static ArrayList<Node> nodes;
	private static ArrayList<Association> asses;
	private static int clickValue = -1; 

	private static View view;
	private static Node tempNode; // For use in creating associations
	private static String curFile = "";
	
	
	

	public static void main(String[] args) {
		nodes = new ArrayList<Node>();
		asses = new ArrayList<Association>();
		view = new View();
		serveObjects();
	}

	/*
	 * Node Functions
	 */


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

		if (clickValue == 0 && isNodeFull(x, y)) { // Don't try to drag nodes
													// unless in class mode


			Node curNode = grabNode(x, y);

			curNode.setPosition(x - curNode.getWidth() / 2,
					y - curNode.getHeight() / 2);
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
						"Are you sure you want to delete this?")) {
			Node curNode = grabNode(x, y);

			nodes.remove(curNode);

			// Also delete associations involving that node
			Iterator<Association> itr = asses.iterator();
			ArrayList<Association> toBeRemoved = new ArrayList<Association>();
			while (itr.hasNext()) {
				Association curAssoc = itr.next();

				if (curAssoc.involvesNode(curNode)) {
					toBeRemoved.add(curAssoc); // Can't just remove it outright
												// because we're in the middle
												// of iterating over this list
				}
			}

			Iterator<Association> revItr = toBeRemoved.iterator();

			while (revItr.hasNext()) { // There has to be a neater way to do
										// this, but I'm too tired to think of
										// one

				asses.remove(revItr.next());
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
	 * Association Functions
	 */

	private static void createAssociation(Node start, Node end, String type) {
		Association newAssoc = new Association(start, type);
		newAssoc.setEndpoint(end);

		asses.add(newAssoc);

		serveObjects();
	}

	private static void deleteAssociation(int x, int y) {
		if (isAssocFull(x, y)
				&& view.confirmMessage("Deletion confirmation",
						"Are you sure you want to delete this?")) {
			Association curAss = grabAss(x, y);

			asses.remove(curAss);

			serveObjects();
		}
	}

	public static boolean isAssocFull(int x, int y) {
		Iterator<Association> itr = asses.iterator();

		while (itr.hasNext()) {
			Association curAss = itr.next();

			if ((x <= curAss.getStartX() + 10 && x >= curAss.getStartX() - 10
					&& y <= curAss.getStartY() + 10 && y >= curAss.getStartY() - 10)
					|| (x <= curAss.getEndX() + 10
							&& x >= curAss.getEndX() - 10
							&& y <= curAss.getEndY() + 10 && y >= curAss
							.getEndY() - 10)) {
				return true;
			}

		}

		return false;

	}

	public static Association grabAss(int x, int y) {

		Iterator<Association> itr = asses.iterator();

		while (itr.hasNext()) {
			Association curAss = itr.next();

			if ((x <= curAss.getStartX() + 10 && x >= curAss.getStartX() - 10
					&& y <= curAss.getStartY() + 10 && y >= curAss.getStartY() - 10)
					|| (x <= curAss.getEndX() + 10
							&& x >= curAss.getEndX() - 10
							&& y <= curAss.getEndY() + 10 && y >= curAss
							.getEndY() - 10)) {
				return curAss;
			}

		}

		return null;

	}
	
	private static void prepAssoc(int x, int y, String type) {
		if (tempNode != null && isNodeFull(x, y)) {
			createAssociation(tempNode, grabNode(x, y), type);
			tempNode = null;
		} else if (isNodeFull(x, y)) {
			tempNode = grabNode(x, y);
		}
		
		serveObjects();
	}
	
	
	
	/*
	 * View Functions
	 */

	private static void serveObjects() {
		// needs to serve everything simultaneously
		ArrayList<NodeInfo> nodeInfo = new ArrayList<NodeInfo>();
		ArrayList<AssocInfo> assocInfo = new ArrayList<AssocInfo>();
		Iterator<Node> nodeItr = nodes.iterator();
		Iterator<Association> assItr = asses.iterator();

		while (nodeItr.hasNext()) {
			Node curNode = nodeItr.next();
			NodeInfo curInfo = new NodeInfo(curNode.getX(), curNode.getY(),
					curNode.getWidth(), curNode.getHeight(), curNode.getName());
			if(curNode.equals(tempNode)){
				curInfo.setHigh(true);
			}
			nodeInfo.add(curInfo);

		}

		while (assItr.hasNext()) {
			Association curAss = assItr.next();
			curAss.recalculateEndPoints(); // Make sure they point to the right
											// places
			AssocInfo curInfo = new AssocInfo(curAss.getStartX(),
					curAss.getStartY(), curAss.getEndX(), curAss.getEndY(), curAss.isSelfAss(), curAss.getAssociationType());

			assocInfo.add(curInfo);
		}
		NodeInfo halfAss = null;
		if(tempNode != null){
			halfAss = new NodeInfo(tempNode.getX(), tempNode.getY(), tempNode.getWidth(), tempNode.getHeight(), tempNode.getName());
		}
		view.drawObjects(nodeInfo, assocInfo, halfAss);

	}

	public static void mouseClick(int x, int y) {
		switch (clickValue) {
		case 0:
			createNode(x, y);
			return; // Class
		case 1:
			deleteNode(x, y);
			deleteAssociation(x, y);
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

	public static void mouseClickRight(int x, int y){
		switch (clickValue) {
		case 0:
			if(isNodeFull(x, y)){
				Node node = grabNode(x, y);
				editNode(node);
				
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

	public static void exit(){
		if(view.confirmMessage("Exit confirmation",
						"Are you sure you want to exit?")){
			System.exit(0);
		}
		
	}

	
	
	/*
	 * Other Functions
	 */
	
	public static void save(String file){
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
	        out.writeObject(asses);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized data is saved in H:/save.uml");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public static void load(String file){
		nodes.clear();
		asses.clear();
		System.out.println(file);
		curFile = file;
		try
	      {
	         FileInputStream fileIn = new FileInputStream(file);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         nodes = (ArrayList<Node>) in.readObject();
	         asses = (ArrayList<Association>) in.readObject();
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

	public static void newUML(){
		
		nodes.clear();
		asses.clear();
		curFile = "";
		clearClickMode();
		
	}
	
}
