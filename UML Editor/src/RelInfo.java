
public class RelInfo {
	private int startX;
	private int startY;
	private int endX;
	private int endY;

	private boolean selfRel;
	private String relType;
	
	/**
	 * RelInfo constructor
	 * @param startX
	 * starting x-coordinate
	 * @param startY
	 * starting y-coordinate
	 * @param endX
	 * ending x-coordinate
	 * @param endY
	 * ending y-coordinate
	 * @param self
	 * if it has a relationship with itself
	 * @param type
	 * type of the relationship
	 */
	public RelInfo(int startX, int startY, int endX, int endY, boolean self, String type) {

		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.setSelfRel(self);
		this.setRelType(type);

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

	public boolean isSelfRel() {
		
		return selfRel;
		
	}

	public void setSelfRel(boolean selfRel) {
		
		this.selfRel = selfRel;
		
	}

	public String getRelType() {
		
		return relType;
		
	}

	public void setRelType(String relType) {
		
		this.relType = relType;
		
	}
	
}