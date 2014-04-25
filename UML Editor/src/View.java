/*
 * File: View.java
 * Project: UML Editor
 * Iteration: 1
 * Description: The View class is an extension of the JFrame class in the Swing API, as per the Java Model-View-Control
 * conventions for design. It is designed to be as inclusive as possible; managing the view port, window, frames, panels
 * tool bars, and menu bar. 
 */

/* Imports */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class View extends JFrame implements ActionListener {
	private JButton selectorButton;
	private JButton classButton;
	private JButton deleteButton;
	private JButton aggButton;
	private JButton compButton;
	private JButton genButton;
	private JButton assButton;
	private JButton dependButton;
	private JButton impButton;
	private JButton undoButton;
	private JButton redoButton;
	private JButton basicButton;
	/**
	 * Default constructor for the View class. Parameters: None Return: Void
	 * Sets up the 'Look And Feel' in the UI Manager. Calls the build for the
	 * tool bars, menu bars, and draw panels. Initializes the display settings.
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
		this.setSize(800, 600);
		this.setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Controller.selectorButton(selectorButton);

	}

	private JMenuItem itemExit;
	private JMenuItem itemSave;
	private JMenuItem itemSaveAs;
	private JMenuItem itemOpen;
	private JMenuItem itemNew;
	private JMenuItem itemExportImage;

	/**
	 * Builder function for the menu bar.
	 * Generates the menu bar, menus, and menu items.
	 */
	private void buildMenuBar() {

		JMenuBar menuBar;
		JMenu menu;
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuBar.add(menu);
		itemNew = new JMenuItem("New");
		itemNew.setMnemonic(KeyEvent.VK_N);
		itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		menu.add(itemNew);
		itemNew.addActionListener(this);

		itemOpen = new JMenuItem("Open...");
		itemOpen.setMnemonic(KeyEvent.VK_O);
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		menu.add(itemOpen);
		itemOpen.addActionListener(this);

		menu.addSeparator();
		itemSave = new JMenuItem("Save");
		itemSave.setMnemonic(KeyEvent.VK_S);
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		menu.add(itemSave);
		itemSave.addActionListener(this);
		itemSaveAs = new JMenuItem("Save As...");
		itemSaveAs.setMnemonic(KeyEvent.VK_A);
		itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		menu.add(itemSaveAs);
		itemSaveAs.addActionListener(this);
		itemExportImage = new JMenuItem("Export as Image");
		itemExportImage.setMnemonic(KeyEvent.VK_P);
		itemExportImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		menu.add(itemExportImage);
		itemExportImage.addActionListener(this);
		menu.addSeparator();
		itemExit = new JMenuItem("Exit");
		itemExit.setIcon(new ImageIcon("exit.png"));
		menu.add(itemExit);
		itemExit.addActionListener(this);

		this.setJMenuBar(menuBar);

	}

	/**
	 * Builder function for the tool bar.
	 * Generates the tool bar and buttons.
	 */
	private void buildToolBar() {

		JToolBar toolBar;
		BufferedImage img;

		toolBar = new JToolBar(JToolBar.VERTICAL);
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.LINE_START);

		selectorButton = new JButton("Selector");
		selectorButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("select.png"));
			selectorButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(selectorButton);

		classButton = new JButton("Class");
		classButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("class.png"));
			classButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(classButton);

		toolBar.addSeparator();
		aggButton = new JButton("Aggregation");
		aggButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("agg.png"));
			aggButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(aggButton);

		compButton = new JButton("Composition");
		compButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("comp.png"));
			compButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(compButton);

		genButton = new JButton("Generalization");
		genButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("gen.png"));
			genButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(genButton);

		assButton = new JButton("Association");
		assButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("assoc.png"));
			assButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(assButton);

		dependButton = new JButton("Depend");
		dependButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("dep.png"));
			dependButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(dependButton);

		impButton = new JButton("Implements");
		impButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("imp.png"));
			impButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(impButton);

		basicButton = new JButton("Basic");
		basicButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("base.png"));
			basicButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(basicButton);

		toolBar.addSeparator();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("del.png"));
			deleteButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(deleteButton);

		undoButton = new JButton("Undo");
		undoButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("undo.png"));
			undoButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		undoButton.setMnemonic(KeyEvent.VK_Z);
		toolBar.add(undoButton);

		redoButton = new JButton("Redo");
		redoButton.addActionListener(this);
		try {
			img = ImageIO.read(getClass().getResource("redo.png"));
			redoButton.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		toolBar.add(redoButton);

	}

	/**
	 * Gets called when an action is performed
	 */
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if (src.equals(classButton)) {
			Controller.classButton(classButton);
		} else if (src.equals(selectorButton)) {
			Controller.selectorButton(selectorButton);
		} else if (src.equals(deleteButton)) {
			Controller.deleteButton(deleteButton);
		} else if (src.equals(aggButton)) {
			Controller.aggButton(aggButton);
		} else if (src.equals(compButton)) {
			Controller.compButton(compButton);
		} else if (src.equals(genButton)) {
			Controller.genButton(genButton);
		} else if (src.equals(assButton)) {
			Controller.assButton(assButton);
		} else if (src.equals(dependButton)) {
			Controller.dependButton(dependButton);
		} else if (src.equals(impButton)) {
			Controller.impButton(impButton);
		} else if (src.equals(basicButton)) {
			Controller.basicButton(basicButton);
		} else if (src.equals(undoButton)) {
			Controller.undo();
		} else if (src.equals(redoButton)) {
			Controller.redo();
		} else if (src.equals(itemExit)) {
			Controller.exit();
		} else if (src.equals(itemSaveAs)) {
			showFileSaver();
		} else if (src.equals(itemSave)) {

			Controller.save("");

		} else if (src.equals(itemOpen)) {

			showFileOpener();
		} else if (src.equals(itemExportImage)) {
			exportAsImage();
		} else if (src.equals(itemNew)) {
			Controller.newUML();
		}
	}

	DrawPanel drawPanel;

	/**
	 * Builder function for the draw panel. Parameters: None Return: Void
	 * Generates the draw panel.
	 */
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
		
		FileFilter filter = new SingleExtensionFilter(".uml", "UML diagram files");
		
		c.addChoosableFileFilter(filter);
		
		c.setFileFilter(filter);
		// Demonstrate "Save" dialog:
		int rVal = c.showSaveDialog(View.this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			Controller.save(c.getSelectedFile().toString());
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			// Do nothing! YAY!
		}

	}

	/**
	 * Show file opener
	 */
	public void showFileOpener() {

		JFileChooser c = new JFileChooser();
		
		FileFilter filter = new SingleExtensionFilter(".uml", "UML diagram files");
		
		c.addChoosableFileFilter(filter);
		
		c.setFileFilter(filter);
		
		int rVal = c.showOpenDialog(View.this);
		if (rVal == JFileChooser.APPROVE_OPTION) {

			Controller.load(c.getSelectedFile().toString());
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			// Do nothing! Yay!
		}

	}

	/**
	 * Exports the draw area as a .png file
	 */
	public void exportAsImage() {
		JFileChooser c = new JFileChooser();
		FileFilter filter = new SingleExtensionFilter(".png", ".png image files");
		c.setFileFilter(filter);
		c.addChoosableFileFilter(filter);
		
		int rVal = c.showSaveDialog(View.this);
		
		if (rVal == JFileChooser.APPROVE_OPTION) {
			BufferedImage bi = new BufferedImage(drawPanel.getSize().width, drawPanel.getSize().height, BufferedImage.TYPE_INT_ARGB); 
			Graphics g = bi.createGraphics();
			drawPanel.paint(g);  //this == JComponent
			g.dispose();
			File imageFile = c.getSelectedFile();
			if (!(imageFile.toString().endsWith(".png")) ){
				String newFileName = imageFile.getPath();
				newFileName += ".png";
				imageFile = new File(newFileName);
			}
			try{ImageIO.write(bi,"png",imageFile);}catch (Exception e) {}
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			// Do nothing! YAY!
		}
		

	}

	/**
	 * Sets the draw panel information and sends command to draw them
	 * 
	 * @param nodeInfo
	 *            Arraylist of all the NodeInfos
	 * @param relInfo
	 *            ArrayList of all the RelInfos
	 * @param halfRel
	 *            NodeInfo if there is a relationship in the process of having
	 *            an end node selected
	 */
	public void drawObjects(ArrayList<NodeInfo> nodeInfo,
			ArrayList<RelInfo> relInfo, NodeInfo halfRel) {

		drawPanel.setNodeInfo(nodeInfo);
		drawPanel.setRelInfo(relInfo);
		drawPanel.halfRel = halfRel;
		undoButton.setEnabled(Controller.hasUndo());
		redoButton.setEnabled(Controller.hasRedo());
		drawPanel.repaint();

	}

	/**
	 * Creates and shows window asking for the name, attributes, and methods
	 * then separates each line into a string that is stored into an arrayList
	 * which is then saved to the node
	 * 
	 * @param n
	 *            Node to show and edit current information
	 */
	public void showCurInfo(Node n) {
		JTextField newName = new JTextField(n.getName());
		JTextArea newAttributes = new JTextArea(5, 1);
		for (int i = 0; i < n.getAttributes().size(); i++) {
			newAttributes.append(n.getAttributes().get(i) + "\n");
		}
		JTextArea newMethods = new JTextArea(5, 1);
		for (int i = 0; i < n.getMethods().size(); i++) {
			newMethods.append(n.getMethods().get(i) + "\n");
		}
		JScrollPane attrScroll = new JScrollPane(newAttributes);
		JScrollPane methScroll = new JScrollPane(newMethods);

		Object[] message = { "Name:", newName, "Attributes:", attrScroll,
				"Methods:", methScroll

		};

		int option = JOptionPane.showConfirmDialog(null, message, "Edit Class",
				JOptionPane.OK_CANCEL_OPTION);
		String[] attributesArray = (newAttributes.getText()).split("\\r?\\n");
		ArrayList<String> attrList = new ArrayList<String>(
				Arrays.asList(attributesArray));

		String[] methodsArray = (newMethods.getText()).split("\\r?\\n");
		ArrayList<String> methList = new ArrayList<String>(
				Arrays.asList(methodsArray));

		if (option == JOptionPane.OK_OPTION) {
			if(n.getName().equals(newName.getText()) && n.getAttributes().equals(trimArrayList(attrList)) && n.getMethods().equals(trimArrayList(methList))) {
				Controller.history.undoPop();
				return;
			}
			n.setName(newName.getText());
			n.setAttributes(attrList);
			n.setMethods(methList);
		} else {
			System.out.println("canceled");
			Controller.history.undoPop();
		}

	}
	
	/**
	 * Creates and shows window asking for the name, attributes, and methods
	 * then separates each line into a string that is stored into an arrayList
	 * which is then saved to the node
	 * 
	 * @param n
	 *            Node to show and edit current information
	 */
	public void showRelInfo(Node n) {
		
		JComboBox<Relationship> relComboBox = new JComboBox<Relationship>();
		
		Iterator<Relationship> relIter = Controller.rels.iterator();
		//ArrayList<Relationship> relList = new ArrayList<Relationship>();
		while (relIter.hasNext()) {
			Relationship curRel = relIter.next();
			if (curRel.involvesNode(n)) {
				//relList.add(curRel);
				relComboBox.addItem(curRel);
			}
		}
		
		
		
		JTextField startDetail = new JTextField();
		JTextField endDetail = new JTextField();
		

		Object[] message = { "Relationships", relComboBox, "Relationship Start Detail:", startDetail,  "Relationship End Detail:", endDetail

		};

		int option = JOptionPane.showConfirmDialog(null, message, "Edit Relationship",
				JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			Relationship r = (Relationship)relComboBox.getSelectedItem();
			r.setStartDetail(startDetail.getText());
			r.setEndDetail(endDetail.getText());
			r.printRelationship();
		} else {
			System.out.println("canceled");
		}

	}
	

	/**
	 * Pops up a confirmation window and returns true if the user clicks yes
	 * @param title
	 * 		The title of the window.
	 * @param message
	 * 		The message in the body of the window.
	 * @return
	 * 		True if the user clicked yes in the window
	 */
	public boolean confirmMessage(String title, String message) {

		// If the function returns 0, it means the user selected "yes"
		return (JOptionPane.showConfirmDialog(this, message, title,
				JOptionPane.YES_NO_OPTION) == 0);

	}
	
	/**
	 * Returns a copy of the arraylist passed in with empty strings removed from it
	 * @param in
	 * 		The list to be trimmed
	 * @return
	 * 		The trimmed list
	 */
	private ArrayList<String> trimArrayList(ArrayList<String> in) {
		@SuppressWarnings("unchecked")
		ArrayList<String> out = (ArrayList<String>) in.clone();
		
		for (int i = 0; i < out.size(); i++) {
			if (out.get(i).isEmpty()) {
				out.remove(i);
			}
		}
		
		return out;
	}
	
	/**
	 * Filters for files of the given extension with the given description
	 * @author Josh
	 *
	 */
	private class SingleExtensionFilter extends FileFilter {
		private String ext;
		private String desc;
		
		/**
		 * Creates a filter with the given extension and description
		 * @param ext
		 * 		The extension of the files to filter for.
		 * @param desc
		 * 		The description of the file filter
		 */
		public SingleExtensionFilter(String ext, String desc) {
			this.ext = ext;
			this.desc = desc;
		}

		@Override
		public boolean accept(File f) {
			return f.getName().endsWith(ext) || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return desc;
		}
	}

}
