import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
class ContextMenu extends JPopupMenu implements ActionListener {

	private Node node;
	JMenuItem editClass;
	JMenuItem editRels;
	JMenuItem delete;

	public ContextMenu(Node n) {
		node = n;
		editClass = new JMenuItem("Edit Class Details");
		editClass.addActionListener(this);
		add(editClass);
		editRels = new JMenuItem("Edit Relationship Details");
		editRels.addActionListener(this);
		add(editRels);
		delete = new JMenuItem("Delete");
		delete.addActionListener(this);
		add(delete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if (src.equals(editClass)) {
			Controller.history.undoPush(Controller.nodes, Controller.rels);
			Controller.history.redoClear();
			NodeController.editNode(node);
		} else if (src.equals(editRels)) {
			Controller.history.undoPush(Controller.nodes, Controller.rels);
			Controller.history.redoClear();
			Controller.view.showRelInfo(node);
			Controller.serveObjects();
		} else if (src.equals(delete)) {
			Controller.history.undoPush(Controller.nodes, Controller.rels);
			Controller.history.redoClear();
			NodeController.deleteNode(node.getX(), node.getY());
		}

	}

}