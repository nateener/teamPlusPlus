import java.util.ArrayList;
import java.util.Iterator;


public class NodeController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
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

	public static void moveNode(int x, int y) {
		// Don't try to drag nodes                  unless in class mode
		if ((Controller.clickValue == 0 || Controller.clickValue == 1) && isNodeFull(x, y)) { 
													
			Node curNode = grabNode(x, y);
			curNode.setPosition(x - curNode.getWidth() / 2, y - curNode.getHeight() / 2);
			Controller.serveObjects();
			
		}

	}

	public static void editNode(Node node) {
		
		Controller.view.showCurInfo(node);
		
		int newNameLength = node.getName().length();
		node.setWidth(newNameLength * 12);
		for (int i = 0; i < node.getAttributes().length; i++){
			if( node.getAttributes()[i].length() * 12 > node.getWidth()){
				node.setWidth(node.getAttributes()[i].length() * 12);
			}
		}
		for (int i = 0; i < node.getMethods().length; i++){
			if( node.getMethods()[i].length() * 12 > node.getWidth()){
				node.setWidth(node.getMethods()[i].length() * 12);
			}
		}
		
		node.setHeight(80 + node.getAttributes().length * 8 + node.getMethods().length * 8 );
		Controller.serveObjects();
		
	}

	
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
