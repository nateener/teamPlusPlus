import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

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
			NodeController.editNode(node);
		} else if(src.equals(editRels)) {
			RelationshipController.editRelationship(node);
		} else if(src.equals(delete)) {
			NodeController.deleteNode(node.getX(), node.getY());
		}

	}

}