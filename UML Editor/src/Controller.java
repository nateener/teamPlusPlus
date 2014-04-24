import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;

/*
 * TODO PLACEHOLDER FOR THE CONTROLLER CLASS.
 */
public class Controller {

	public static ArrayList<Node> nodes;
	public static ArrayList<Relationship> rels;
	public static UndoRedoStack history;
	public static int clickValue = -1;

	public static View view;
	public static Node tempNode; // For use in creating relationships
	private static JButton lastClickedButton;
	private static String curFile = "";

	public static void main(String[] args) {

		nodes = new ArrayList<Node>();
		rels = new ArrayList<Relationship>();
		view = new View();
		history = new UndoRedoStack();
		serveObjects();

	}

	/*
	 * View Functions
	 */

	/**
	 * serveObjects readies the arraylist of nodes and relationships into their
	 * leaner Info variations for drawing also handles keeping track of the
	 * tempNode/halfRel, this being the first node selected in creating a
	 * relationship Once it's done readying it will call to draw the objects
	 */
	public static void serveObjects() {

		if(view == null) {
			return;
		}
		
		// needs to serve everything simultaneously
		ArrayList<NodeInfo> nodeInfo = new ArrayList<NodeInfo>();
		ArrayList<RelInfo> relInfo = new ArrayList<RelInfo>();
		Iterator<Node> nodeItr = nodes.iterator();
		Iterator<Relationship> relItr = rels.iterator();

		while (nodeItr.hasNext()) {
			Node curNode = nodeItr.next();
			NodeInfo curInfo = new NodeInfo(curNode.getX(), curNode.getY(),
					curNode.getWidth(), curNode.getHeight(), curNode.getName(),
					curNode.getAttributes(), curNode.getMethods());
			if (curNode.equals(tempNode)) {
				curInfo.setHigh(true);
			}
			nodeInfo.add(curInfo);

		}

		while (relItr.hasNext()) {
			Relationship curRel = relItr.next();
			curRel.recalculateEndPoints(); // Make sure they point to the right
											// places
			RelInfo curInfo = new RelInfo(curRel.getStartX(),
					curRel.getStartY(), curRel.getEndX(), curRel.getEndY(),
					curRel.isSelfRel(), curRel.getRelationshipType());

			relInfo.add(curInfo);
		}
		NodeInfo halfRel = null;
		if (tempNode != null) {
			halfRel = new NodeInfo(tempNode.getX(), tempNode.getY(),
					tempNode.getWidth(), tempNode.getHeight(),
					tempNode.getName(), tempNode.getAttributes(),
					tempNode.getMethods());
		}
		view.drawObjects(nodeInfo, relInfo, halfRel);

	}

	/**
	 * This method runs when the mouse is clicked and does the correct action
	 * based on the current clickValue
	 * 
	 * @param x
	 *            x-position of the mouse
	 * @param y
	 *            y-position of the mouse
	 * 
	 */
	public static void mouseClick(int x, int y) {

		if (clickValue > 0) {
			history.undoPush(nodes, rels);
			history.redoClear();
		}

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

	/**
	 * This method handles when the mouse is right-clicked and does the
	 * appropriate action based on the click value
	 * 
	 * @param x
	 *            x-position of the mouse
	 * @param y
	 *            y-position of the mouse
	 */
	public static void mouseClickRight(int x, int y) {

		if (NodeController.isNodeFull(x, y)) {
			Node node = NodeController.grabNode(x, y);
			ContextMenu popup = new ContextMenu(node);
			popup.show(view.drawPanel, x, y);
		}

	}

	public static void undo() {
		UndoRedoStack.State newState = history.undoPop();

		if (newState == null) {
			return;
		}

		history.redoPush(nodes, rels);
		nodes = newState.getNodes();
		rels = newState.getRelations();

		serveObjects();
	}

	public static void redo() {
		UndoRedoStack.State newState = history.redoPop();

		if (newState == null)
			return;

		history.undoPush(nodes, rels);
		nodes = newState.getNodes();
		rels = newState.getRelations();

		serveObjects();
	}

	/**
	 * Runs when changing clickValue to empty out any variables still sticking
	 * around
	 */
	private static void clearClickMode() { // Make sure we don't store clickmode
											// specific stuff when changing
											// clickmodes
		tempNode = null;
		if (lastClickedButton != null) {
			// Set to default background color
			lastClickedButton.setBackground(new Color(240, 240, 240));
		}
		serveObjects();

	}

	public static void selectorButton(JButton button) {

		// Model
		clearClickMode();
		clickValue = 0;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void classButton(JButton button) {

		// Model
		clearClickMode();
		clickValue = 1;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void deleteButton(JButton button) {

		clearClickMode();
		clickValue = 2;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void aggButton(JButton button) {
		clearClickMode();
		clickValue = 3;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void compButton(JButton button) {

		clearClickMode();
		clickValue = 4;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void genButton(JButton button) {

		clearClickMode();
		clickValue = 5;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void assButton(JButton button) {

		clearClickMode();
		clickValue = 6;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void dependButton(JButton button) {

		clearClickMode();
		clickValue = 7;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void impButton(JButton button) {

		clearClickMode();
		clickValue = 8;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static void basicButton(JButton button) {

		clearClickMode();
		clickValue = 9;
		lastClickedButton = button;
		button.setBackground(new Color(255, 255, 153));

	}

	public static boolean hasUndo() {
		return history.hasUndoStates();
	}

	public static boolean hasRedo() {
		return history.hasRedoStates();
	}

	/**
	 * Prompts user and exits program
	 */
	public static void exit() {

		if (view.confirmMessage("Exit confirmation",
				"Are you sure you want to exit?")) {
			System.exit(0);
		}

	}

	/*
	 * Other Functions
	 */

	/**
	 * Takes a serialized string of the objects and creates a save file
	 * 
	 * @param file
	 *            Contains both arrayLists serialized into a string
	 */
	public static void save(String file) {

		if (file.equals("")) {
			if (curFile.equals("")) {
				view.showFileSaver();

			} else {
				file = curFile;
			}
		}
		curFile = file;
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(nodes);
			out.writeObject(rels);
			// System.out.println("Saving");
			// for (Relationship r : rels){
			// r.printRelationship();
			// }
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in H:/save.uml");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	/**
	 * Takes in a serialized string of the objects and loads that information
	 * into the program
	 * 
	 * @param file
	 *            Contains both arrayLists serialized into a string
	 */
	@SuppressWarnings("unchecked")
	public static void load(String file) {

		nodes.clear();
		rels.clear();
		System.out.println(file);
		curFile = file;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			nodes = (ArrayList<Node>) in.readObject();
			rels = (ArrayList<Relationship>) in.readObject();
			// System.out.println("Loading");
			// for (Relationship r : rels){
			// r.printRelationship();
			// }
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}

		serveObjects();
	}

	/**
	 * Clear everything out and create a new, clean UML editor
	 */
	public static void newUML() {

		nodes.clear();
		rels.clear();
		history.undoClear();
		history.redoClear();
		curFile = "";
		clearClickMode();

	}

}
