/*
 * File: View.java
 * Project: UML Editor
 * Iteration: 1
 * Description: The View class is an extension of the JFrame class in the Swing API, as per the Java Model-View-Control
 * conventions for design. It is designed to be as inclusive as possible; managing the view port, window, frames, panels
 * tool bars, and menu bar. 
 */

/*
 * TODO Add action calls for menu items and tool bar buttons.
 * TODO Add mouse listener to draw panel.
 * TODO Add icons for tool bar buttons.
 */

/* Imports */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class View extends JFrame implements ActionListener {
	
	/* Default constructor for the View class.
	 * Parameters: None
	 * Return: Void
	 * Sets up the 'Look And Feel' in the UI Manager.
	 * Calls the build for the tool bars, menu bars, and draw panels.
	 * Initializes the display settings.
	 */
	public View() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		buildMenuBar();
		buildToolBar();
		buildDrawPanel();
		this.setTitle("UML Editor v1.0");
		this.setSize(800,600);
		this.setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	/* Builder function for the menu bar.
	 * Parameters: None
	 * Return: Void
	 * Generates the menu bar, menus, and menu items.
	 */
	
	private JMenuItem itemExit;
	private JMenuItem itemSave;
	private JMenuItem itemSaveAs;
	private JMenuItem itemOpen;
	private JMenuItem itemNew;
	private JMenuItem itemExportImage;
	
	
	private void buildMenuBar() {
		
		JMenuBar menuBar;
		JMenu menu;
		JMenu subMenu;
		JMenuItem item;
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuBar.add(menu);
		itemNew = new JMenuItem("New");
		itemNew.setMnemonic(KeyEvent.VK_N);
		itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menu.add(itemNew);
		itemNew.addActionListener(this);
		
		itemOpen = new JMenuItem("Open...");
		itemOpen.setMnemonic(KeyEvent.VK_O);
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menu.add(itemOpen);
		itemOpen.addActionListener(this);
		
		menu.addSeparator();
		itemSave = new JMenuItem("Save");
		itemSave.setMnemonic(KeyEvent.VK_S);
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menu.add(itemSave);
		itemSave.addActionListener(this);
		itemSaveAs = new JMenuItem("Save As...");
		itemSaveAs.setMnemonic(KeyEvent.VK_A);
		itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		menu.add(itemSaveAs);
		itemSaveAs.addActionListener(this);
		itemExportImage = new JMenuItem("Export as Image");
		itemExportImage.setMnemonic(KeyEvent.VK_A);
		menu.add(itemExportImage);
		itemExportImage.addActionListener(this);
		menu.addSeparator();
		itemExit = new JMenuItem("Exit");
		itemExit.setIcon(new ImageIcon("exit.png"));
		menu.add(itemExit);
		itemExit.addActionListener(this);
		
		this.setJMenuBar(menuBar);
		
	}
	
	/* Builder function for the tool bar.
	 * Parameters: None
	 * Return: Void
	 * Generates the tool bar and buttons.
	 */
	private JButton selectorButton;
	private JButton classButton;
	private JButton deleteButton;
	private JButton aggButton;
	private JButton compButton;
	private JButton genButton;
	private JButton assButton;
	private JButton dependButton;
	private JButton impButton;
	private JButton basicButton;
	
	private void buildToolBar() {

		JToolBar toolBar;
		
		toolBar = new JToolBar(JToolBar.VERTICAL);
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.LINE_START);
		
		  
		
		selectorButton = new JButton("Selector");
		selectorButton.addActionListener(this);
		try {
		    Image img = ImageIO.read(getClass().getResource("awesome_test.png"));
		    selectorButton.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
		toolBar.add(selectorButton);
		
		classButton = new JButton("Class");
		classButton.addActionListener(this);
		toolBar.add(classButton);
		
		toolBar.addSeparator();
		aggButton = new JButton("Aggregation");
		aggButton.addActionListener(this);
		toolBar.add(aggButton);
		
		compButton = new JButton("Composition");
		compButton.addActionListener(this);
		toolBar.add(compButton);
		
		genButton = new JButton("Generalization");
		genButton.addActionListener(this);
		toolBar.add(genButton);
		
		assButton = new JButton("Association");
		assButton.addActionListener(this);
		toolBar.add(assButton);
		
		dependButton = new JButton("Depend");
		dependButton.addActionListener(this);
		toolBar.add(dependButton);
		
		impButton = new JButton("Implements");
		impButton.addActionListener(this);
		toolBar.add(impButton);
		
		basicButton = new JButton("Basic");
		basicButton.addActionListener(this);
		toolBar.add(basicButton);
		
		toolBar.addSeparator();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		toolBar.add(deleteButton);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object src = e.getSource();
		if(src.equals(classButton)){
			Controller.classButton();
		} else if (src.equals(selectorButton)) {
			Controller.selectorButton();	
		} else if (src.equals(deleteButton)) {
			Controller.deleteButton();
		} else if (src.equals(aggButton)) {
			Controller.aggButton();
		} else if (src.equals(compButton)) {
			Controller.compButton();
		} else if (src.equals(genButton)) {
			Controller.genButton();
		} else if (src.equals(assButton)) {
			Controller.assButton();
		} else if (src.equals(dependButton)) {
			Controller.dependButton();
		} else if (src.equals(impButton)) {
			Controller.impButton();
		} else if (src.equals(basicButton)) {
			Controller.basicButton();
		} else if (src.equals(itemExit)) {
			Controller.exit();
		} else if (src.equals(itemSaveAs)) {
			 showFileSaver();
		} else if (src.equals(itemSave))	{
	
			Controller.save("");
		
		} else if (src.equals(itemOpen)) {
			
			showFileOpener();
		} else if (src.equals(itemExportImage)) {
			 exportAsImage();
		} else if (src.equals(itemNew)){
			Controller.newUML();
		}
	}

	/* Builder function for the tool bar.
	 * Parameters: None
	 * Return: Void
	 * Generates the draw panel.
	 */
	DrawPanel drawPanel;
	
	private void buildDrawPanel() {
		
		drawPanel = new DrawPanel();
		JScrollPane scroller = new JScrollPane(drawPanel);
		drawPanel.setPreferredSize(new Dimension(500, 500));
		this.add(scroller);
	}
	
	
	// Controller/View Methods
	
	/**
	 * Show file saver
	 */
	public void showFileSaver() {
		
		JFileChooser c = new JFileChooser();
	      // Demonstrate "Save" dialog:
	      int rVal = c.showSaveDialog(View.this);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	    	  Controller.save( c.getSelectedFile().toString());
	      }
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	    	  	// Do nothing! YAY!
	      }
	      
	}
	
	/**
	 * Show file opener
	 */
	public void showFileOpener() {
		
		final JFileChooser fc = new JFileChooser();
		 JFileChooser c = new JFileChooser();
	      //
	      int rVal = c.showOpenDialog(View.this);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	    	  
	        Controller.load( c.getSelectedFile().toString());
	      }
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	    	  // Do nothing! Yay!
	      }
	      
	}
	
	public void exportAsImage() {
		System.out.println("I sure did export the view as an image");	
	}
	
	/**
	 * Sets the draw panel information and sends command to draw them
	 * @param nodeInfo
	 * Arraylist of all the NodeInfos
	 * @param relInfo
	 * ArrayList of all the RelInfos
	 * @param halfRel
	 * NodeInfo if there is a relationship in the process of having an end node selected
	 */
	public void drawObjects(ArrayList<NodeInfo> nodeInfo, ArrayList<RelInfo> relInfo, NodeInfo halfRel ){
		
		drawPanel.setNodeInfo(nodeInfo);
		drawPanel.setRelInfo(relInfo);
		drawPanel.halfRel = halfRel;
		drawPanel.repaint();
		
	}
	
	/**
	 * Creates and shows window asking for the name, attributes, and methods
	 * then separates each line into a string that is stored into an arrayList which is then saved to the node
	 * 
	 * @param n
	 * Node to show and edit current information
	 */
	public void showCurInfo(Node n) {
		
		JTextField newName = new JTextField(n.getName());
		JTextArea newAttributes = new JTextArea(5,1);
		for (int i = 0; i < n.getAttributes().size(); i++){
			newAttributes.append(n.getAttributes().get(i) + "\n");
		}
		JTextArea newMethods = new JTextArea(5,1);
		for (int i = 0; i < n.getMethods().size(); i++){
			newMethods.append(n.getMethods().get(i) + "\n");
		}
		JScrollPane attrScroll = new JScrollPane(newAttributes);
		JScrollPane methScroll = new JScrollPane(newMethods);
		
		Object[] message = {
		    "Name:", newName,
		    "Attributes:", attrScroll,
		    "Methods:", methScroll
		    
		};
		

		
		int option = JOptionPane.showConfirmDialog(null, message, "Edit Class", JOptionPane.OK_CANCEL_OPTION);
		String[] attributesArray = (newAttributes.getText()).split("\\r?\\n");
		ArrayList<String> attrList = new ArrayList<String>(Arrays.asList(attributesArray));
		
		String[] methodsArray = (newMethods.getText()).split("\\r?\\n");
		ArrayList<String> methList = new ArrayList<String>(Arrays.asList(methodsArray));

		if (option == JOptionPane.OK_OPTION) {
			n.setName(newName.getText());
			n.setAttributes(attrList);
			n.setMethods(methList);
		}
		else
		{
		    System.out.println("canceled");
		}
		
	}
	
	public boolean confirmMessage(String title, String message) {
		
		//If the function returns 0, it means the user selected "yes"
		return (JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION) == 0);
		
	}
	
	

	/*
	private class DrawPanel extends JPanel {
	    @Override public void paintComponent(Graphics g) {
	        g.drawRect(50, 50, 20, 10); // <-- draws a rect on the panel
	        System.out.println("X");
	    }
	}
	
	
	/*
	@Override public void paintComponents(Graphics g)
	{
		super.paintComponents(g);
		g.drawRect(10, 20, 20, 10);
		System.out.println("oi");
	
	}
	*/

}
