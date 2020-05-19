package pacman;

public interface PacmanObserver {
	public void notify(Coordinate pacmanPos,Coordinate pacmanPosPrec);
}
