
public class NodeInfo {
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
<<<<<<< HEAD
	private String name;
	private boolean high;
	
	public NodeInfo(int x, int y, int w, int h, String n) {
=======
	
	public NodeInfo(int x, int y, int w, int h) {
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
		xCoor = x;
		yCoor = y;
		width = w;
		height = h;
<<<<<<< HEAD
		name = n;
		setHigh(false);
=======
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
		
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
<<<<<<< HEAD
	
	public String getName(){
		
		return name;
		
	}


	public boolean isHigh() {
		return high;
	}


	public void setHigh(boolean high) {
		this.high = high;
	}
=======
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970

}
