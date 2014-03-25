import java.util.Iterator;


public class RelationshipController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	private static void createRelationship(Node start, Node end, String type) {
		
		Relationship newRel = new Relationship(start, type);
		newRel.setEndpoint(end);

		Controller.rels.add(newRel);

		Controller.serveObjects();
		
	}

	public static void deleteRelationship(int x, int y) {
		
		if (isRelFull(x, y)){
			Relationship curRel = grabRel(x, y);
			String message = "Are you sure you want to delete this " + curRel.getRelationshipType() + " between node " + curRel.getStartNode().getName() + " and node " + curRel.getEndNode().getName() + "?";
			Boolean delete = Controller.view.confirmMessage("Deletion confirmation",
						message);
		
			
			if ( delete ){
				Controller.rels.remove(curRel);
			Controller.serveObjects();
			}
		}
		
	}

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
	
	public static void prepRel(int x, int y, String type) {
		
		if (Controller.tempNode != null && NodeController.isNodeFull(x, y)) {
			createRelationship(Controller.tempNode, NodeController.grabNode(x, y), type);
			Controller.tempNode = null;
		} else if (NodeController.isNodeFull(x, y)) {
			Controller.tempNode = NodeController.grabNode(x, y);
		}
		
		Controller.serveObjects();
		
	}

}
