import java.util.ArrayList;
import java.util.Iterator;

public class NodeController {

	/**
	 * Creates and saves a new node if there isn't already a node there then
	 * serve and draw the objects
	 * 
	 * @param x
	 *            x-coordinate of the new node
	 * @param y
	 *            y-coordinate of the new node
	 */
	public static void createNode(int x, int y) {

		// Code that creates a new node object.
		Node node = new Node(x, y);

		if (!isNodeFull(node.getX(), node.getY())
				&& !isNodeFull(node.getX() + node.getWidth(), node.getY())
				&& !isNodeFull(node.getX(), node.getY() + node.getHeight())
				&& !isNodeFull(node.getX() + node.getWidth(), node.getY()
						+ node.getHeight())) {

			Controller.nodes.add(node);
			// Call method serveNodes
			Controller.serveObjects();
		}
	}

	/**
	 * Checks if in proper clickValue then grabs and updates the node Then serve
	 * and draw the objects
	 * 
	 * @param x
	 *            x-coordinate of the node
	 * @param y
	 *            y-coordinate of the node
	 */
	public static void moveNode(int x, int y) {
		// Don't try to drag nodes unless in class mode
		if ((Controller.clickValue == 0 || Controller.clickValue == 1)
				&& isNodeFull(x, y)) {

			Node curNode = grabNode(x, y);
			curNode.setPosition(x - curNode.getWidth() / 2,
					y - curNode.getHeight() / 2);
			Controller.serveObjects();

		}

	}

	/**
	 * Updates the name, attributes, and methods of the node and the width based
	 * on length of the name, attributes, and methods Then serve and draw the
	 * objects
	 * 
	 * @param node
	 *            Node to be edited
	 */
	public static void editNode(Node node) {
		Controller.view.showCurInfo(node);

		int newNameLength = node.getName().length();
		node.setWidth(newNameLength * 12);
		for (int i = 0; i < node.getAttributes().size(); i++) {
			if (node.getAttributes().get(i).length() * 12 > node.getWidth()) {
				node.setWidth(node.getAttributes().get(i).length() * 12);
			}
		}
		for (int i = 0; i < node.getMethods().size(); i++) {
			if (node.getMethods().get(i).length() * 12 > node.getWidth()) {
				node.setWidth(node.getMethods().get(i).length() * 12);
			}
		}

		node.setHeight(80 + node.getAttributes().size() * 10
				+ node.getMethods().size() * 10);
		Controller.serveObjects();

	}

	/**
	 * Find the indicated node and remove it from the list Also delete all
	 * relationships attached to it Then serve and draw objects
	 * 
	 * @param x
	 *            x-coordinate of Node
	 * @param y
	 *            y-coordinate of Node
	 */
	public static void deleteNode(int x, int y) {

		// Conditionals in the if statement are in this order so that the
		// confirmation only appears if you click on a node
		if (isNodeFull(x, y)
				&& Controller.view.confirmMessage("Deletion confirmation",
						"Are you sure you want to delete this node?")) {
			Node curNode = grabNode(x, y);

			Controller.nodes.remove(curNode);

			// Also delete relationships involving that node
			Iterator<Relationship> itr = Controller.rels.iterator();
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

				Controller.rels.remove(revItr.next());
			}

			Controller.serveObjects();
		}
	}

	/**
	 * Checks if the given coordinates contain a node
	 * 
	 * @param x
	 *            x-coordinate of Node
	 * @param y
	 *            y-coordinate of Node
	 * @return Boolean value if given coordinates contains a node
	 */
	public static boolean isNodeFull(int x, int y) {

		Iterator<Node> itr = Controller.nodes.iterator();

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

	/**
	 * From the given coordinates, grab and return the node object that occupys
	 * that space
	 * 
	 * @param x
	 *            x-coordinate of node
	 * @param y
	 *            y-coordinate of node
	 * @return The actual node object from the given coordinates
	 */
	public static Node grabNode(int x, int y) {

		Iterator<Node> itr = Controller.nodes.iterator();

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

}
