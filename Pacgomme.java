package pacman;

public abstract class Pacgomme {
	
	protected Coordinate coordPG;
	protected Jeu jeu;
	
	enum Type{
		Normal,
		Invisible,
		Super,
		Laby
	}
	
	public Pacgomme(Jeu jeu,Coordinate coordPG) {
		this.jeu=jeu;
		this.coordPG=coordPG;
	}
	
	public Coordinate getCoordPG() {
		return coordPG;
	}
	
	public abstract void mange();
		
	public abstract int getPoints();
	
	public abstract Type getType();
	
}
