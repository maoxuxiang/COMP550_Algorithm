package Assignment1;

public class POINT {
	
	private int x;
	private int y;
	
	public POINT(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
 	public int direction(POINT p2, POINT p3) {

 		return   (p3.getX() - x)*(p2.getY() - y) - (p2.getX() - x)*(p3.getY() - y);
 	}
	

}
