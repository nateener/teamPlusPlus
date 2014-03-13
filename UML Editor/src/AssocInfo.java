
public class AssocInfo {
	private int startX;
	private int startY;
	private int endX;
	private int endY;

	private boolean selfAss;
	private String assType;
	
	public AssocInfo(int startX, int startY, int endX, int endY, boolean self, String type) {

		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.setSelfAss(self);
		this.setAssType(type);

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

	public boolean isSelfAss() {
		return selfAss;
	}

	public void setSelfAss(boolean selfAss) {
		this.selfAss = selfAss;
	}

	public String getAssType() {
		return assType;
	}

	public void setAssType(String assType) {
		this.assType = assType;
	}
	
	

}
