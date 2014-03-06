import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class DrawPanel extends JPanel implements MouseListener, MouseMotionListener { 
		
		private ArrayList<NodeInfo> nodeInfo;
		private ArrayList<AssocInfo> assocInfo;
		public NodeInfo halfAss;
		private int mouseX;
		private int mouseY;
		
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
			Dimension size = this.getSize();
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
					
					g.drawRect(curInfo.getStartX() - 20, curInfo.getStartY() - 20, 40, 40);
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
			
			
			if(halfAss != null ){
				g.setColor(Color.RED);
				g.drawLine(halfAss.getxCoor() + (halfAss.getWidth() / 2), halfAss.getyCoor() + (halfAss.getHeight() / 2), mouseX, mouseY);
				
				
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
		public void mouseMoved(MouseEvent mouse) {
			// TODO Auto-generated method stub
			
			mouseX = mouse.getX();
			mouseY = mouse.getY();
			
			repaint();
			
		}
		
		
	}