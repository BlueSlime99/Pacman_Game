package pacman;

public abstract class Personnage {
	private Jeu jeu;
	private Coordinate start;
	private Coordinate pos;
	private Coordinate posPrec;
	private Direction dir;
	private int compteurEffet;
	
	public abstract void move();
	
	public Personnage(Jeu jeu, Coordinate start, Direction dir) {
		initCoordinates(start);
		this.jeu=jeu;
		this.compteurEffet=0;
		this.dir=dir;
	}
	
	public void initCoordinates(Coordinate start) {
		this.start=start;
		pos=start;
		posPrec=start;
	}
	
	public Jeu getJeu() {
		return jeu;
	}
	
	public Coordinate getStart() {
		return this.start;
	}
	
	public void addValToCompteurEffet(int val) {
		this.compteurEffet+=val;
	}
	
	public int getCompteurEffet() {
		return this.compteurEffet;
	}
	
	public void resetCompteurEffet() {
		this.compteurEffet=0;
	}
	
	public Coordinate getPos() {
		return pos;
	}
	
	public void setPos(Coordinate pos) {
		this.pos=pos;
	}
	
	public Coordinate getPosPrec() {
		return posPrec;
	}
	
	public void setPosPrec(Coordinate posPrec) {
		this.posPrec=posPrec;
	}
	
	public Direction getDir() {
		return dir;
	}
	
	public void setDir(Direction dir) {
		this.dir=dir;
	}
	
	public Coordinate newPos() {
		return new Coordinate(pos,dir);
	}
	
	public void setPositions(Coordinate newPos) {
		this.posPrec=pos;
		this.pos=newPos;
	}
	
}
