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
<<<<<<< HEAD
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
=======
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	
<<<<<<< HEAD
	
	
=======
	int numclicks = 0;
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
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
<<<<<<< HEAD
		} else if (src.equals(itemExit)) {
			Controller.exit();
		} else if (src.equals(itemSaveAs)) {
			 showFileSaver();
		} else if (src.equals(itemSave))	{
	
			Controller.save("");
		
		} else if (src.equals(itemOpen)) {
			
			showFileOpener();
		} else if (src.equals(itemNew)){
			Controller.newUML();
		}
	}
	
	
=======
		}
	}
	
	public void setText(String text){
		classButton.setText(text);
	}
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
	/* Builder function for the tool bar.
	 * Parameters: None
	 * Return: Void
	 * Generates the draw panel.
	 */
	DrawPanel drawPanel;
	
	private void buildDrawPanel() {
		drawPanel = new DrawPanel();
		this.add(drawPanel);
<<<<<<< HEAD
		
	}
	
	
	// Controller/View Methods
	
	public void showFileSaver(){
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
	
	public void showFileOpener(){
		
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
	
	public void drawObjects(ArrayList<NodeInfo> nodeInfo, ArrayList<AssocInfo> assocInfo){
		
		drawPanel.setNodeInfo(nodeInfo);
		drawPanel.setAssocInfo(assocInfo);
		drawPanel.repaint();
		
		
	}
	
	public String showCurInfo(String name, String attributes, String methods){
		// Currently only deals with name!
		String newName = JOptionPane.showInputDialog(name);
		
		if(newName != null){
			
			return newName;
			
		} 
		
		return " ";
		
		
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
			
			// Draw Background
			this.setBackground(Color.WHITE);
			
			// Draw Grid
			g.setColor(new Color(225, 236, 242));
			Dimension size = drawPanel.getSize();
			int panelWidth = (int)size.getWidth();
			int panelHeight = (int)size.getHeight();
			for(int i = 0; i < panelWidth; i+=10){	
				g.drawLine(i, 0, i, panelHeight);
			}
			for(int i = 0; i < panelHeight; i+=10){
				g.drawLine(0, i, panelWidth, i);
			}
			
			
			// Draw associations
			g.setColor(Color.BLACK);
			while(assItr.hasNext()) {
				AssocInfo curInfo = assItr.next();
				if(curInfo.isSelfAss()){
					
					g.drawRect(curInfo.getStartX(), curInfo.getStartY(), 40, 40);
				} else {
				
					//g.drawLine(curInfo.getStartX(), curInfo.getStartY(), curInfo.getEndX(), curInfo.getEndY());
					
					int midpoint = curInfo.getStartY() + ((curInfo.getEndY() - curInfo.getStartY()) / 2);
					
					g.drawLine(curInfo.getStartX(), curInfo.getStartY(), curInfo.getStartX(), midpoint);
					g.drawLine(curInfo.getEndX(), curInfo.getEndY(), curInfo.getEndX(), midpoint);
					g.drawLine(curInfo.getStartX(), midpoint, curInfo.getEndX(), midpoint);
					
					//if(curInfo)
					//int xPoly[] = {curInfo.getEndX(), curInfo.getEndX()+5, curInfo.getEndX(), curInfo.getEndX()-5};
					//int yPoly[] = {curInfo.getEndY(), curInfo.getEndY()+5, curInfo.getEndY()+10, curInfo.getEndY()+5};
					// Draw triangles on ENDPOINTS!
					//g.fillPolygon(xPoly , yPoly, 4);
					
					
					
				}
			
			}
			
			// Draw nodes
			while(nodeItr.hasNext()){
				NodeInfo curInfo = nodeItr.next();
				//System.out.println(curInfo.getName());
				g.setColor(Color.WHITE);
				g.fillRect(curInfo.getxCoor(), curInfo.getyCoor(), curInfo.getWidth(), curInfo.getHeight() );
				
				g.setColor(Color.BLACK);
				if(curInfo.isHigh()){
					g.setColor(Color.RED);
				}
				
				g.drawRect(curInfo.getxCoor(), curInfo.getyCoor(), curInfo.getWidth(), curInfo.getHeight());
				
				// Draw Text
				g.setColor(Color.BLACK);
				g.setFont(new Font("Monospaced", Font.PLAIN, 12));
				
				String name;
				if(curInfo.getName().length() > 25){		
					name = curInfo.getName().substring(0, 22) + "...";
				} else {
					name = curInfo.getName();
				}
					
				g.drawString(name, curInfo.getxCoor()+10, curInfo.getyCoor()+10); // Must be drawn last!
			}
			}
			
			
		
		
		@Override
		public void mouseClicked(MouseEvent mouse) {
			
			if(SwingUtilities.isRightMouseButton(mouse)){
		        
				Controller.mouseClickRight(mouse.getX(), mouse.getY());
				
		    } else {
			
			Controller.mouseClick(mouse.getX(), mouse.getY());
			
		    }
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
=======
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
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
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



<<<<<<< HEAD
=======

>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970

}
