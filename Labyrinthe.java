package pacman;

import java.util.ArrayList;
import java.util.List;

public class Labyrinthe {
	private Jeu jeu;
	private int [][] laby;
	
	static final int INVISIBLE_PG=3;
	static final int SUPER_PG=4;
	static final int LABY_PG=5;
	
	public Labyrinthe (Jeu jeu) {
		this.jeu=jeu;
		int [][] lab={{0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0},
				      {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
				      {0,0,1,1,1,0,1,1,1,1,1,1,1,1,0,0,0,0,0},
				      {0,0,3,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0},
				      {0,1,1,1,0,1,0,4,1,0,0,0,0,0,0,0,0,0,0},
				      {0,1,0,1,1,1,0,0,0,0,0,0,1,0,1,0,1,0,1},
				      {0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,0,0,0,0},
				      {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0},
				      {0,0,1,1,1,0,1,0,0,0,0,0,0,0,0,1,3,0,0},
				      {0,0,0,1,0,0,1,0,0,0,0,1,0,0,1,1,0,1,0},
				      {0,0,0,1,0,0,1,1,0,0,0,1,0,0,1,0,0,0,0},
				      {0,1,1,1,1,0,1,1,0,0,0,1,1,0,1,1,1,0,0},
				      {0,0,0,4,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0},
				      {0,0,0,1,0,0,1,1,1,0,0,0,1,1,1,1,1,0,0},
				      {0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
				      {0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0},
				      {0,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0},
				      {0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0,0},
				      {0,3,1,0,0,0,1,0,5,0,0,1,0,0,4,0,0,0,0},
			};
		
		laby=lab;
		laby[14][13]=2;
	}
	
	public void modifLaby() {
		int [][] lab2= {{0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0},
						{0,1,0,1,0,0,1,1,1,1,1,0,1,1,0,0,1,0,0},
						{0,1,0,1,0,0,0,0,0,0,1,0,0,1,1,0,0,0,1},
						{0,1,0,1,1,1,1,1,1,0,0,0,0,0,1,1,1,0,0},
						{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
						{1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0},
						{0,0,0,0,1,1,1,0,1,0,0,0,0,0,1,0,0,0,0},
						{0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,1,0,0,1},
						{0,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0},
						{0,0,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0},
						{0,1,1,1,1,0,0,1,0,0,0,0,1,0,1,0,0,1,0},
						{0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0},
						{0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,0,0},
						{0,1,1,1,0,1,0,1,0,0,0,1,1,0,0,0,0,0,0},
						{0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,0,1,1,1},
						{1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,0,1,0,0},
						{0,0,0,0,0,1,0,1,0,0,0,0,1,1,0,0,1,0,0},
						{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1} };
		this.laby=lab2;
		verifPlacementPG();
	}
	
	public void verifPlacementPG() {
		for(int i=0;i<jeu.getPacgommes().size();i++) {
			if(isMur(jeu.getPacgommes().get(i).getCoordPG())) 
				replacePG(jeu.getPacgommes().get(i));
		}
	}
	
	public void replacePG(Pacgomme pg) {
		int i=0;
		int j=-1;
		while(isPG(pg.getCoordPG())==true || isMur(pg.getCoordPG())==true) {
			if(i==getDimLaby(1)-1) 
				break;
			else {
				if(j==getDimLaby(2)-1) {
					j=0;
					i++;
				}
				else {
					j++;
				}
				pg.getCoordPG().setXY(i,j);
			}
		}
	}
	
	public boolean isPG(Coordinate c) {
		int compteurPG=0;
		for(int i=0;i<jeu.getPacgommes().size();i++) {
			if(jeu.getPacgommes().get(i).getCoordPG().getX()==c.getX() && jeu.getPacgommes().get(i).getCoordPG().getY()==c.getY() )
				compteurPG++;
		}
		if(compteurPG==1)
			return false;
		return true;
	}
	
	public Coordinate getCoordStartPacman() {
		return new Coordinate(14,13);
	}
	
	public List<Coordinate> getCoordStartFantomes(){
		List<Coordinate> coords=new ArrayList<>(4);
		for(int i=0;i<4;i++) {
			coords.add(new Coordinate(8+i,9));
			laby[8+i][9]=2;
		}
		return coords;
	}
	
	public int[][] getLaby() {
		return laby;
	}
	
	public int getDimLaby (int n) {
		if(n==1)
			return laby.length;
		return laby[0].length;
	}
	
	public boolean isMur(Coordinate c) {
		if(laby[c.getX()][c.getY()]==1) 
			return true;
		return false;
	}
	
	public boolean respectDimLaby(Coordinate c) {
		if(c.getX()<0 || c.getY()<0 || c.getX()>=getDimLaby(1) || c.getY()>=getDimLaby(2))
			return false;
		return true;
	}
	
}
