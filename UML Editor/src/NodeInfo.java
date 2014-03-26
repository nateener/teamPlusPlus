
public class NodeInfo {
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
	private String name;
	private String attributes;
	private String methods;
	private boolean high;
	
	public NodeInfo(int x, int y, int w, int h, String n, String a, String m) {

		xCoor = x;
		yCoor = y;
		width = w;
		height = h;
		name = n;
		attributes = a;
		methods = m;
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

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

}