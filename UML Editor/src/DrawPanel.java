import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

class DrawPanel extends JPanel implements MouseListener, MouseMotionListener { 
		
		private ArrayList<NodeInfo> nodeInfo;
		private ArrayList<RelInfo> relInfo;
		public NodeInfo halfRel;
		private int mouseX;
		private int mouseY;
		
		
		
		public DrawPanel() {
			
			super();
			
			addMouseListener(this);
			addMouseMotionListener(this);
			setPreferredSize(new Dimension(200, 250));
			
			
		}
		
		public void setNodeInfo(ArrayList<NodeInfo> nodeInfo) {
			
			this.nodeInfo = nodeInfo;
			
		}
		
		public void setRelInfo(ArrayList<RelInfo> relInfo) {
			
			this.relInfo = relInfo;
		}
		
		
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Iterator<NodeInfo>	nodeItr = nodeInfo.iterator();
			Iterator<RelInfo> relItr = relInfo.iterator();
			
			// Draw Background
			this.setBackground(Color.WHITE);
			
			// Draw Grid
			g.setColor(new Color(225, 236, 242));
			Dimension size = this.getSize();
			// Scroll Bar
			
			
			
			int panelWidth = (int)size.getWidth();
			int panelHeight = (int)size.getHeight();
			for(int i = 0; i < panelWidth; i+=10) {	
				g.drawLine(i, 0, i, panelHeight);
			}
			for(int i = 0; i < panelHeight; i+=10) {
				g.drawLine(0, i, panelWidth, i);
			}
			
			Graphics2D g2d = (Graphics2D) g;
			BasicStroke stroke = new BasicStroke(1.0f);
			g2d.setStroke(stroke);
			
			// Draw relationships
			g.setColor(Color.BLACK);		
			while(relItr.hasNext()) {
				RelInfo curInfo = relItr.next();
				//System.out.println("Drawing relationship");
				//System.out.println(curInfo.getRelType());
				if(curInfo.isSelfRel()) {
					if (curInfo.getRelType().equals( "Implements") || curInfo.getRelType().equals("Depend") ) {
						Stroke drawingStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
						Rectangle2D square = new Rectangle2D.Double(curInfo.getStartX() - 20, curInfo.getStartY() - 20, 40, 40);
						g2d.setStroke(drawingStroke);
						g2d.draw(square);
						g2d.setStroke(stroke);
					}else{
						g.drawRect(curInfo.getStartX() - 20, curInfo.getStartY() - 20, 40, 40);
					}
				} else {
				
					//g.drawLine(curInfo.getStartX(), curInfo.getStartY(), curInfo.getEndX(), curInfo.getEndY());
					
					int midpoint = curInfo.getStartY() + ((curInfo.getEndY() - curInfo.getStartY()) / 2);
					
					if (curInfo.getRelType() .equals("Implements") || curInfo.getRelType()  .equals("Depend") ) {
						// Draw dotted line
						Stroke drawingStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
						Line2D line1 = new Line2D.Double(curInfo.getStartX(), curInfo.getStartY(), curInfo.getStartX(), midpoint);
						Line2D line2 = new Line2D.Double(curInfo.getEndX(), curInfo.getEndY(), curInfo.getEndX(), midpoint);
						Line2D line3 = new Line2D.Double(curInfo.getStartX(), midpoint, curInfo.getEndX(), midpoint);
						g2d.setStroke(drawingStroke);
						g2d.draw(line1);
						g2d.draw(line2);
						g2d.draw(line3);
						g2d.setStroke(stroke);
					} else {
						// Draw regular line
						g.drawLine(curInfo.getStartX(), curInfo.getStartY(), curInfo.getStartX(), midpoint);
						g.drawLine(curInfo.getEndX(), curInfo.getEndY(), curInfo.getEndX(), midpoint);
						g.drawLine(curInfo.getStartX(), midpoint, curInfo.getEndX(), midpoint);
					}
					// Cap with white diamond
					if (curInfo.getRelType() .equals("Aggregation") ) {
						g.setColor(Color.WHITE);
						int xPoly[] = {curInfo.getEndX(), curInfo.getEndX()+5, curInfo.getEndX(), curInfo.getEndX()-5};
						int yPoly[] = {curInfo.getEndY(), curInfo.getEndY()+5, curInfo.getEndY()+10, curInfo.getEndY()+5};
						//Draw triangles on ENDPOINTS!
						g.fillPolygon(xPoly , yPoly, 4);
						g.setColor(Color.BLACK);
						g.drawPolygon(xPoly , yPoly, 4);
					}
					// Cap with black diamond
					if (curInfo.getRelType() .equals( "Composition") ) {
						g.setColor(Color.BLACK);
						int xPoly[] = {curInfo.getEndX(), curInfo.getEndX()+5, curInfo.getEndX(), curInfo.getEndX()-5};
						int yPoly[] = {curInfo.getEndY(), curInfo.getEndY()+5, curInfo.getEndY()+10, curInfo.getEndY()+5};
						//Draw triangles on ENDPOINTS!
						g.fillPolygon(xPoly , yPoly, 4);
					}
					// Cap with white triangle
					if (curInfo.getRelType() .equals( "Generalization") || curInfo.getRelType() .equals( "Implements") ) {
						g.setColor(Color.WHITE);
						int xPoly[] = {curInfo.getEndX(), curInfo.getEndX()+5,  curInfo.getEndX()-5};
						int yPoly[] = {curInfo.getEndY(), curInfo.getEndY()+10,  curInfo.getEndY()+10};
						//Draw triangles on ENDPOINTS!
						g.fillPolygon(xPoly , yPoly, 3);
						g.setColor(Color.BLACK);
						g.drawPolygon(xPoly , yPoly, 3);
					}
					// Cap with black arrow
					if (curInfo.getRelType() .equals( "Association") || curInfo.getRelType() .equals( "Depend")) {
						g.setColor(Color.BLACK);
						g.drawLine(curInfo.getEndX(), curInfo.getEndY(), curInfo.getEndX() - 5, curInfo.getEndY() + 7);
						g.drawLine(curInfo.getEndX(), curInfo.getEndY(), curInfo.getEndX() + 5, curInfo.getEndY() + 7);
					}

				}
			
			}
			//Dimension dim = this.getPreferredSize();
			Dimension highDim = new Dimension(100, 100);
			// Draw nodes
			while(nodeItr.hasNext()) {
				NodeInfo curInfo = nodeItr.next();
				//System.out.println(curInfo.getName());
				g.setColor(Color.WHITE);
				g.fillRect(curInfo.getxCoor(), curInfo.getyCoor(), curInfo.getWidth(), curInfo.getHeight() );
				
				g.setColor(Color.BLACK);
				if(curInfo.isHigh()){
					g.setColor(Color.RED);
				}
				
				if(curInfo.getWidth() + curInfo.getxCoor() > highDim.width){
					highDim.width = curInfo.getWidth() + curInfo.getxCoor();
				}
				
				if(curInfo.getHeight() + curInfo.getyCoor() > highDim.height){
					highDim.height = curInfo.getHeight() + curInfo.getyCoor();
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
					
				g.drawString(name, curInfo.getxCoor()+6, curInfo.getyCoor()+10); // Must be drawn last!
				if(!curInfo.getAttributes().isEmpty() || !curInfo.getMethods().isEmpty()){
					g.drawLine(curInfo.getxCoor(), curInfo.getyCoor() + 13, curInfo.getxCoor() + curInfo.getWidth(), curInfo.getyCoor() + 13);
					for (int i = 0; i < curInfo.getAttributes().size(); i++){
						g.drawString(curInfo.getAttributes().get(i), curInfo.getxCoor()+10, curInfo.getyCoor()+24+(i*12));
					}
						g.drawLine(curInfo.getxCoor(), curInfo.getyCoor() + 27 +  (curInfo.getAttributes().size() * 12), curInfo.getxCoor() + curInfo.getWidth(), curInfo.getyCoor() + 27 +  (curInfo.getAttributes().size() * 12));
					for (int i = 0; i < curInfo.getMethods().size(); i++){
						g.drawString(curInfo.getMethods().get(i), curInfo.getxCoor()+10, curInfo.getyCoor()+38+(i*12) +  (curInfo.getAttributes().size() * 12));// Must be drawn last!
					}
				}
			}
			
			this.setPreferredSize(new Dimension(highDim.width, highDim.height));
			
			if(halfRel != null ) {
				
				g.setColor(Color.RED);
				g.drawLine(halfRel.getxCoor() + (halfRel.getWidth() / 2), halfRel.getyCoor() + (halfRel.getHeight() / 2), mouseX, mouseY);
				
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
			
			NodeController.moveNode(mouse.getX(), mouse.getY());
			
		}

		@Override
		public void mouseMoved(MouseEvent mouse) {
			// TODO Auto-generated method stub
			
			mouseX = mouse.getX();
			mouseY = mouse.getY();
			
			repaint();
			
		}
		
		
	}