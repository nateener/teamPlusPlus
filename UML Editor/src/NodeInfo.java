
public class NodeInfo {
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
	private String name;
	private boolean high;
	
	public NodeInfo(int x, int y, int w, int h, String n) {

		xCoor = x;
		yCoor = y;
		width = w;
		height = h;
		name = n;
		setHigh(false);

	}
	
	public int getxCoor() {
		
		return xCoor;
		
	}
	
	public void setxCoor(int xCoor) {
		
		this.xCoor = xCoor;
		
	}
	
	public int getyCoor() {
		
		return yCoor;
		
	}
	
	public void setyCoor(int yCoor) {
		
		this.yCoor = yCoor;
		
	}
	
	public int getWidth() {
		
		return width;
		
	}
	
	public void setWidth(int width) {
		
		this.width = width;
		
	}
	
	public int getHeight() {
		
		return height;
		
	}
	
	public void setHeight(int height) {
		
		this.height = height;
		
	}
	
	public String getName() {
		
		return name;
		
	}

	public boolean isHigh() {
		
		return high;
		
	}

	public void setHigh(boolean high) {
		
		this.high = high;
		
	}

}