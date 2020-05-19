package pacman;

import java.util.Random;

public class Fantome extends Personnage implements PacmanObserver{
	private EtatFantome etat;
	private boolean retour;
	
	public Fantome(Jeu jeu, Coordinate start) {
		super(jeu, start, Direction.Down);
		getJeu().registerToPacman(this);
		etat=new NormalFantome(this);
		retour=false;
	}
	
	public void setRetour(boolean b) {
		retour=b;
	}
	
	public boolean getRetour() {
		return retour;
	}
	
	public void setEtat() {
		if(super.getCompteurEffet()==0) {
			if(getJeu().pacmanIsSuper()) 
				casSuperPacman();
			else 
				setEtatNormal();
		}
	}
	
	public void casSuperPacman() {
		if(retour)
			casRetour();
		else 
			setEtatVulnerable();
	}
	
	public void casRetour() {
		if(estVulnerable())
			setEtatRetour();
		else
			setEtatNormal();
	}
	
	public boolean estRetour() {
		return etat.getState()==EtatFantome.State.Retour;
	}

	public void setEtatNormal() {
		this.etat=new NormalFantome(this);
		setRetour(false);
	}
	
	public void setEtatVulnerable() {
		this.etat=new VulnerableFantome(this);
		setRetour(false);
	}
	
	public void setEtatRetour() {
		this.etat=new RetourFantome(this);
	}
	
	public boolean estVulnerable() {
		return etat.getState()==EtatFantome.State.Vulnerable;
	}
		
	public void decCompteurEffet() {
		super.addValToCompteurEffet(-1);;
	}

	public void move() {
		this.etat.moveFantome();
	}
	
	public void moveAleatoire() {
		Coordinate newPos=newPos();
		while(!newPosisOK(newPos))
				setNewPos(newPos,getDir());
		setPositions(newPos);
	}
	
	public void setNewPos(Coordinate newPos,Direction dir) {
		Direction newDir=createNewDir(dir);
		setDir(newDir);
		newPos.setXY(getPos(),newDir);
	}
	
	public Direction createNewDir(Direction dir) {
		Direction newDir=randomDirection();
		while(newDir.equals(dir))
			newDir=randomDirection();
		return newDir;
	}
	
	public boolean AutresDirections() {
		Direction [] directions=Direction.values();
		if (nbDirections(directions)<2)
			return false;
		return true;
	}
	
	public int nbDirections(Direction[] directions) {
		int rep=0;
		for(int i=0;i<directions.length-1;i++) {
			Coordinate c=new Coordinate(getPos(),directions[i]);
			if(getJeu().respectDimLaby(c) && !(getJeu().isMur(c)))
				rep++;
		}
		return rep;
	}
	
	public boolean newPosisOK(Coordinate newPos) {
		if(getJeu().respectDimLaby(newPos) && !getJeu().isMur(newPos)) {
			if(newPos.equals(getPosPrec()) && AutresDirections())
				return false;
			return true;
		}
		return false;
	}
	
	public Direction randomDirection() {
		Random random=new Random();
		Direction [] directions=Direction.values();
		int x=random.nextInt(directions.length-1);
		return directions[x];
	}
	
	public EtatFantome getEtat() {
		return this.etat;
	}
	
	public boolean coordInverses(Coordinate pacmanPos,Coordinate pacmanPosPrec ) {
		if(this.getPosPrec().equals(pacmanPos) && super.getPos().equals(pacmanPosPrec))
				return true;
		return false;
	}

	@Override
	public void notify(Coordinate pacmanPos,Coordinate pacmanPosPrec) {
		if(super.getPos().equals(pacmanPos)) 
			getJeu().toucheFantome(this);
		else if(coordInverses(pacmanPos, pacmanPosPrec))
			getJeu().toucheFantome(this);
	}
	
}
