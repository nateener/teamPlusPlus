
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class RelDetailsEditMenu extends JFrame implements ActionListener{
	
	private Node node;
	JComboBox<Relationship> relationshipsComboBox;
	JTextField startDetailField;
	JTextField endDetailField;
	JButton deleteButton;
	JButton cancelButton;
	JButton saveButton;

	public RelDetailsEditMenu(Node n) {
		node = n;
		// Get relationships then
		relationshipsComboBox = new JComboBox<>();
		relationshipsComboBox.addActionListener(this);
		add(relationshipsComboBox);
		
		startDetailField = new JTextField();
		startDetailField.addActionListener(this);
		add(startDetailField);
		
		endDetailField = new JTextField();
		endDetailField.addActionListener(this);
		add(endDetailField);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(deleteButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		add(cancelButton);
		
		saveButton = new JButton("Save/Done");
		saveButton.addActionListener(this);
		add(saveButton);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if (src.equals(relationshipsComboBox)) {
			System.out.println("ComboBox");
		} else if (src.equals(startDetailField)) {
			System.out.println("start Detail Field");
		} else if (src.equals(endDetailField)) {
			System.out.println("end detail field");
		}

	}
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
