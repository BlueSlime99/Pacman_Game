package pacman;


public enum Direction {
	Up(0, -1), Down(0, 1), Left(-1, 0), Right(1, 0), Immobile(0,0);

	private int x;
	private int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
	    return x;
	}

	public int getY() {
	    return y;
	}
	
	public Coordinate getCoordDir() {
		return new Coordinate(x,y);
	}
	
	public boolean equals(Direction d) {
		return (x==d.x && y==d.y);
	}
	
}
