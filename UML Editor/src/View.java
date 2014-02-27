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
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
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
	}
	
	/* Builder function for the menu bar.
	 * Parameters: None
	 * Return: Void
	 * Generates the menu bar, menus, and menu items.
	 */
	private void buildMenuBar() {
		JMenuBar menuBar;
		JMenu menu;
		JMenu subMenu;
		JMenuItem item;
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuBar.add(menu);
		item = new JMenuItem("New");
		item.setMnemonic(KeyEvent.VK_N);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menu.add(item);
		item = new JMenuItem("Open...");
		item.setMnemonic(KeyEvent.VK_O);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("Save");
		item.setMnemonic(KeyEvent.VK_S);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menu.add(item);
		item = new JMenuItem("Save As...");
		item.setMnemonic(KeyEvent.VK_A);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("Exit");
		item.setIcon(new ImageIcon("exit.png"));
		menu.add(item);
		menu = new JMenu("Tools");
		menuBar.add(menu);
		subMenu = new JMenu("Add Node");
		subMenu.setMnemonic(KeyEvent.VK_N);
		menu.add(subMenu);
		item = new JMenuItem("Class");
		item.setMnemonic(KeyEvent.VK_C);
		subMenu.add(item);
		subMenu = new JMenu("Add Association");
		subMenu.setMnemonic(KeyEvent.VK_A);
		menu.add(subMenu);
		item = new JMenuItem("Aggregation");
		item.setMnemonic(KeyEvent.VK_G);
		subMenu.add(item);
		item = new JMenuItem("Composition");
		item.setMnemonic(KeyEvent.VK_C);
		subMenu.add(item);
		menu.addSeparator();
		item = new JMenuItem("Delete");
		item.setMnemonic(KeyEvent.VK_D);
		menu.add(item);
		this.setJMenuBar(menuBar);
	}
	
	/* Builder function for the tool bar.
	 * Parameters: None
	 * Return: Void
	 * Generates the tool bar and buttons.
	 */
	private JButton classButton;
	private JButton deleteButton;
	private JButton aggButton;
	private JButton compButton;
	
	private void buildToolBar() {
		JToolBar toolBar;
		
		toolBar = new JToolBar(JToolBar.VERTICAL);
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.LINE_START);
		
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
		
		toolBar.addSeparator();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		toolBar.add(deleteButton);
	}
	
	int numclicks = 0;
	public void actionPerformed(ActionEvent e) { 
		Object src = e.getSource();
		if(src.equals(classButton)){
			Controller.classButton();
		} else if (src.equals(deleteButton)) {
			Controller.deleteButton();
		} else if (src.equals(aggButton)) {
			Controller.aggButton();
		} else if (src.equals(compButton)) {
			Controller.compButton();
		}
	}
	
	public void setText(String text){
		classButton.setText(text);
	}
	/* Builder function for the tool bar.
	 * Parameters: None
	 * Return: Void
	 * Generates the draw panel.
	 */
	DrawPanel drawPanel;
	
	private void buildDrawPanel() {
		drawPanel = new DrawPanel();
		this.add(drawPanel);
		//add(drawPanel, BorderLayout.CENTER);
		//drawPanel.repaint();
		//drawPanel.setBackground(new Color(255, 255, 255)); //not working unsure why
		//System.out.println("bloooo");
	}
	
	public void drawObjects(ArrayList<NodeInfo> nodeInfo, ArrayList<AssocInfo> assocInfo){
		
		drawPanel.setNodeInfo(nodeInfo);
		drawPanel.setAssocInfo(assocInfo);
		drawPanel.repaint();
		
		
	}
	
	public boolean confirmMessage(String title, String message) {
		//If the function returns 0, it means the user selected "yes"
		return (JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION) == 0);
	}
	
	private class DrawPanel extends JPanel implements MouseListener, MouseMotionListener { 
		
		private ArrayList<NodeInfo> nodeInfo;
		private ArrayList<AssocInfo> assocInfo;
		
		public DrawPanel(){
			super();
			
			addMouseListener(this);
			addMouseMotionListener(this);
			
		}
		
		public void setNodeInfo(ArrayList<NodeInfo> nodeInfo){
			
			this.nodeInfo = nodeInfo;
			
		}
		
		public void setAssocInfo(ArrayList<AssocInfo> assocInfo) {
			this.assocInfo = assocInfo;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Iterator<NodeInfo>	nodeItr = nodeInfo.iterator();
			Iterator<AssocInfo> assItr = assocInfo.iterator();
			
			while(nodeItr.hasNext()){
				NodeInfo curInfo = nodeItr.next();
				
				g.fillRect(curInfo.getxCoor(), curInfo.getyCoor(), curInfo.getWidth(), curInfo.getHeight() );
				
			}
			
			while(assItr.hasNext()) {
				AssocInfo curInfo = assItr.next();
				
				g.drawLine(curInfo.getStartX(), curInfo.getStartY(), curInfo.getEndX(), curInfo.getEndY());
			}
			
			
			
		}
		
		@Override
		public void mouseClicked(MouseEvent mouse) {
			Controller.mouseClick(mouse.getX(), mouse.getY());
			System.out.print("Component");
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
		
		@Override
		public void mouseDragged(MouseEvent mouse) {
			// TODO Auto-generated method stub
			
			Controller.moveNode(mouse.getX(), mouse.getY());
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		
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