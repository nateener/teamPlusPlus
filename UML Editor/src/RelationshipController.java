import java.util.Iterator;

public class RelationshipController {

	/**
	 * Create a new relationship Serve and draw objects
	 * 
	 * @param start
	 *            Starting Node object
	 * @param end
	 *            Ending Node object
	 * @param type
	 *            String type of the relationship
	 */
	private static void createRelationship(Node start, Node end, String type) {

		Relationship newRel = new Relationship(start, type);
		newRel.setEndpoint(end);

		Iterator<Relationship> itr = Controller.rels.iterator();

		while (itr.hasNext()) {
			Relationship curRel = itr.next();
			
			if (newRel.isSelfRel() && (curRel.isSelfRel() && curRel.involvesNode(start))) {
				Controller.rels.remove(curRel);
				break;
			} else if (!newRel.isSelfRel() && (curRel.involvesNode(start) && curRel.involvesNode(end))) {
				Controller.rels.remove(curRel);
				break;
			}
		}

		Controller.rels.add(newRel);

		Controller.serveObjects();

	}
	
	/**
	 * Check if given coordinates contain a relationship, prompt to delete and
	 * if yes delete
	 * 
	 * @param x
	 *            clicked x-coordinate
	 * @param y
	 *            clicked y-coordinate
	 */
	public static void deleteRelationship(int x, int y) {

		if (isRelFull(x, y)) {
			Relationship curRel = grabRel(x, y);
			String message = "Are you sure you want to delete this "
					+ curRel.getRelationshipType() + " between node "
					+ curRel.getStartNode().getName() + " and node "
					+ curRel.getEndNode().getName() + "?";
			Boolean delete = Controller.view.confirmMessage(
					"Deletion confirmation", message);

			if (delete) {
				Controller.rels.remove(curRel);
				Controller.serveObjects();
			}
		}

	}

	/**
	 * Checks if given coordinates contain a relationship
	 * 
	 * @param x
	 *            clicked x-coordinate
	 * @param y
	 *            clicked y-coordinate
	 * @return Boolean if the coordinates contain a relationship
	 */
	public static boolean isRelFull(int x, int y) {

		Iterator<Relationship> itr = Controller.rels.iterator();

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

	/**
	 * Gets the actual Relationship object from the given coordinates
	 * 
	 * @param x
	 *            given x-coordinate
	 * @param y
	 *            given y-coordinate
	 * @return Relationship object
	 */
	public static Relationship grabRel(int x, int y) {

		Iterator<Relationship> itr = Controller.rels.iterator();

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

	/**
	 * Starts the process of creating a relationship
	 * 
	 * @param x
	 *            clicked x-coordinate of the first node selected
	 * @param y
	 *            clicked y-coordinate of the first node selected
	 * @param type
	 *            string type of the new relationship
	 */
	public static void prepRel(int x, int y, String type) {

		if (Controller.tempNode != null && NodeController.isNodeFull(x, y)) {
			createRelationship(Controller.tempNode,
					NodeController.grabNode(x, y), type);
			Controller.tempNode = null;
		} else if (NodeController.isNodeFull(x, y)) {
			Controller.tempNode = NodeController.grabNode(x, y);
		}

		Controller.serveObjects();

	}

}
