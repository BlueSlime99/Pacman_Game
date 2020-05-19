package pacman;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Jeu {
	private boolean partie;
	private int points;
	private int compteurPoints;
	private Pacman pacman;
	private Labyrinthe laby;
	private List<Fantome> fantomes;
	private List<Pacgomme> pacgommes;
	private List<Personnage> personnages;
	private Fenetre frame;
	
	public Jeu(Fenetre frame) {
		initPartie();
		initFrame(frame);
		initLaby(); 
		initPacman();
		initPG();
		initFantome();
		initPersonnages();
	}
	
	public void initPartie() {
		partie=true;
		points=0;
		compteurPoints=0;
	}
	
	public void initFrame(Fenetre frame) {
		this.frame=frame;
	}
	
	public void initLaby() {
		this.laby=new Labyrinthe(this);
	}
	
	public void initPacman() {
		pacgommes=new ArrayList<>();
		this.pacman=new Pacman(this,laby.getCoordStartPacman());
	}
	
	public void initPG() {
		for(int i=0;i<laby.getDimLaby(1);i++) {
			for(int j=0;j<laby.getDimLaby(2);j++)
				createPG(i,j);
		}
	}
	
	public void createPG(int i,int j) {
		if(laby.getLaby()[i][j]!=1 && laby.getLaby()[i][j]!=2) {
			pacgommes.add(creerTypePG(laby.getLaby()[i][j], new Coordinate(i,j)));
		}
	}
	
	public Pacgomme creerTypePG(int val, Coordinate c) {
		switch(val) {
		case Labyrinthe.INVISIBLE_PG :
			return new InvisiblePG(this,c);
		case Labyrinthe.SUPER_PG :
			return new SuperPG(this,c);
		case Labyrinthe.LABY_PG : 
			return new LabyPG(this,c);
		default :
			return new NormalPG(this,c);
		}
	}
		
	public void initFantome() {
		fantomes=new ArrayList<>(4);
		for(int i=0;i<4;i++) 
			createFantome(i);
	}
	
	public void createFantome(int i) {
		for(int j=0;j<pacgommes.size();j++) {
			if(pacgommes.get(j).getCoordPG().equals(laby.getCoordStartFantomes().get(i))) {
				pacgommes.remove(j);
				break;
			}
		}
		fantomes.add(new Fantome(this, laby.getCoordStartFantomes().get(i)));
	}
	
	
	
	public void initPersonnages() {
		personnages=new ArrayList<>();
		personnages.add(pacman);
		for(Fantome f:fantomes)
			personnages.add(f);
	}
	
	public void step() {
		for(Personnage p:personnages)
			p.move();
	}
	
	public Labyrinthe getLaby() {
		return laby;
	}
	
	public void modifyLaby() {
		this.laby.modifLaby();
	}
	
	public Pacman getPacman() {
		return pacman;
	}
	
	public void setEtatPacman(EtatPacman etatP) {
		pacman.setEtatPacman(etatP);
	}
	
	public List<Fantome> getFantomes(){
		return fantomes;
	}
	
	public List<Pacgomme> getPacgommes(){
		return pacgommes;
	}
	
	public void fin() {
		String msg;
		if(!verifVictoire())
			msg="Vous avez perdu \n REJOUER?";
		else 
			msg="Vous avez gagne \n REJOUER?";
		affichePopup(msg);
	}
	
	public void affichePopup(String msg) {
		int choix=JOptionPane.showConfirmDialog(this.frame, msg,"FIN",JOptionPane.YES_NO_OPTION);
		effectuerChoix(choix);
	}
	
	public void effectuerChoix(int choix) {
		frame.dispose();
		if(choix==JOptionPane.OK_OPTION) 
			Driver.main(null);
	}
	
	public boolean verifVictoire() {
		if(this.pacman.getVies()>0 && this.pacgommes.size()==0) 
			return true;
		return false;
	}
	
	public List<Noeud> createChemin(Coordinate pos, Coordinate destination) {
		return new Dijkstra(pos,destination,laby).getChemin();
	}
	
	public EtatPacman.State getStatePacman() {
		return pacman.getEtatPacman().getState();
	}
	
	public boolean isMur(Coordinate c) {
		return laby.isMur(c);
	}
	
	public boolean respectDimLaby(Coordinate c) {
		return laby.respectDimLaby(c);
	}
	
	public void registerToPacman(PacmanObserver po) {
		pacman.register(po);
	}
	
	public void toucheFantome(Fantome fantome) {
		pacman.toucheFantome(fantome);
	}
	
	public int getDimLaby1() {
		return laby.getDimLaby(1);
	}
	
	public int getDimLaby2() {
		return laby.getDimLaby(2);
	}
	
	public void Terminee() {
		partie=false;
	}
	
	public boolean getPartie() {
		return partie;
	}
	
	public void mangePG() {
		for(int i=0;i<pacgommes.size();i++) {
			if(pacgommes.get(i).getCoordPG().equals(pacman.getPos())) {
				pacgommes.get(i).mange();
				ajoutPoints(pacgommes.get(i).getPoints());
				pacgommes.remove(i);
				break;
			}
		}
		verifTerminee();
	}
	
	public void verifTerminee() {
		if(pacgommes.size()==0)
			Terminee();
	}
	
	public void ajoutPoints(int points) {
		this.points+=points;
		for(int i=1;i<=points;i++) {
			this.compteurPoints++;
			test5000();
		}
	}
	
	public void test5000() {
		if(compteurPoints==5000) {
			pacman.incVies();
			this.compteurPoints=0;
		}
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void setEtatFantomes() {
		for(int i=0;i<fantomes.size();i++) 
			this.fantomes.get(i).setEtat();
	}
	
	public boolean pacmanIsSuper() {
		return this.pacman.isSuper();
	}
	
}
