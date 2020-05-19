package pacman;

import java.util.ArrayList;
import java.util.List;

public class Pacman extends Personnage{
	private int vies;
	private EtatPacman etat;
	private List<PacmanObserver> observers;	 
	
	public Pacman(Jeu jeu, Coordinate start){
		super(jeu, start, Direction.Immobile);
		vies=3;
		this.setNormalEtat();
		observers=new ArrayList<>();
	}
	
	public void register(PacmanObserver po) {
		observers.add(po);
	}
	
	public void unregister(PacmanObserver po) {
		observers.remove(po);
	}
	
	public List<PacmanObserver> getPacmanObservers(){
		return observers;
	}
	
	private void notifyObserver() {
		for (PacmanObserver po : observers) 
			po.notify(super.getPos(),super.getPosPrec()); 
	}
	
	public void move() {
		Coordinate newPos=newPos();
		wrap(newPos);
		setNewPos(newPos);
		incCompteurEffet();
		notifyObserver();
	}
	
	public void setNewPos(Coordinate newPos) {
		if(super.getJeu().respectDimLaby(newPos)){
			if(super.getJeu().isMur(newPos)) 
				setDir(Direction.Immobile);
			else {
				setPositions(newPos);
				mangePG();
			}
		}
	}
	
	public void mangePG() {
		super.getJeu().mangePG();
	}
	
	public void incCompteurEffet() {
		super.addValToCompteurEffet(1);
		if(super.getCompteurEffet()==30) {
			this.setNormalEtat();
			super.getJeu().setEtatFantomes();
		}
	}
	
	public void wrap(Coordinate newPos) {
		if(passageGauche(newPos)) 
			newPos.setX(super.getJeu().getDimLaby1()-1);
		else if(passageDroit(newPos)) 
			newPos.setX(0);
		else if(passageHaut(newPos)) 
			newPos.setY(super.getJeu().getDimLaby1()-1);
		else if(passageBas(newPos)) 
			newPos.setY(0);
	}
	
	public boolean passageGauche(Coordinate newPos) {
		if(newPos.getX()==-1 && newPos.getY()==super.getJeu().getDimLaby2()/2)
			return true;
		return false;
	}
	
	public boolean passageDroit(Coordinate newPos) {
		if(newPos.getX()==super.getJeu().getDimLaby1() && newPos.getY()==super.getJeu().getDimLaby2()/2)
			return true;
		return false;
	}
	
	public boolean passageHaut(Coordinate newPos) {
		if(newPos.getX()==(super.getJeu().getDimLaby1()/2) && newPos.getY()==-1)
			return true;
		return false;
	}
	
	public boolean passageBas(Coordinate newPos) {
		if(newPos.getX()==(super.getJeu().getDimLaby1()/2) && newPos.getY()==super.getJeu().getDimLaby1())
			return true;
		return false;
	}
	
	public void toucheFantome(Fantome fantome) {
		etat.toucheFantome(fantome);
	}
	
	public int getVies() {
		return vies;
	}
	
	public void incVies() {
		vies++;
	}
	
	public void decVies() {
		vies--;
		testMort();
	}
	
	public void testMort() {
		if(vies==0)
			super.getJeu().Terminee();
	}
	
	public EtatPacman getEtatPacman() {
		return etat;
	}

	public void setNormalEtat() {
		this.etat=new NormalPacman(this);
	}
	
	public void setEtatPacman(EtatPacman etat) {
		super.resetCompteurEffet();
		if(!(isSuper() && etat.getState()==EtatPacman.State.Invisible))
			this.etat=etat;
	}
	
	public void hurt() {
		this.decVies();
		this.returnAtStart();
	}
	
	public void returnAtStart() {
		setPositions(super.getStart());
	}
	
	public boolean isSuper() {
		return this.etat.getState()==EtatPacman.State.Super;
	}
}
