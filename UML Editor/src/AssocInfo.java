
public class AssocInfo {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
<<<<<<< HEAD
	private boolean selfAss;
	private String assType;
	
	public AssocInfo(int startX, int startY, int endX, int endY, boolean self, String type) {
=======
	
	public AssocInfo(int startX, int startY, int endX, int endY) {
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
<<<<<<< HEAD
		this.setSelfAss(self);
		this.assType = type;
=======
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
	}
	
	public int getStartX() {
		return startX;
	}
	
	public void setStartX(int startX) {
		this.startX = startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public void setStartY(int startY) {
		this.startY = startY;
	}
	
	public int getEndX() {
		return endX;
	}
	
	public void setEndX(int endX) {
		this.endX = endX;
	}
	
	public int getEndY() {
		return endY;
	}
	
	public void setEndY(int endY) {
		this.endY = endY;
	}
<<<<<<< HEAD

	public boolean isSelfAss() {
		return selfAss;
	}

	public void setSelfAss(boolean selfAss) {
		this.selfAss = selfAss;
	}
	
	
	
=======
>>>>>>> db3e4c72a361bc49f49ce588b120949ae19cb970
}
