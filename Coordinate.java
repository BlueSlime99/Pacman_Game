package pacman;


public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(Coordinate c, Direction d) {
		setXY(c,d);
	}
	
	public void setXY(Coordinate c,Direction d) {
		setXY(c.x+d.getX(),c.y+d.getY());
	}

	public void setXY(Direction d) {
		setXY(x+d.getX(),y+d.getY());
	}
	
	public void setXY(int x,int y) {
		setX(x);
		setY(y);
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public boolean equals(Coordinate c) {
		return (x==c.x && y==c.y);
	}
	
}
