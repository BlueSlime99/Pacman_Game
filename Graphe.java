package pacman;

import java.util.ArrayList;
import java.util.List;

class Noeud extends Coordinate{
	private Noeud prec;
	private int val;
	private boolean marque;
	
	public Noeud(int x, int y) {
		super(x, y);
		this.prec=null;
		val=10000;
		marque=false;
	}
	
	public void setVal(int val) {
		this.val=val;
	}
	
	public int getVal() {
		return val;
	}
	
	public boolean getMarque() {
		return marque;
	}
	
	public void setMarque(boolean b) {
		marque=b;
	}
	
	public void setPrec(Noeud prec) {
		this.prec=prec;
	}
	
	public Noeud getPrec() {
		return prec;
	}

}

public class Graphe {
	
	private class Arete {
		private Noeud a;
		private Noeud b;
		
		public Arete(Noeud a,Noeud b) {
			this.a=a;
			this.b=b;
		}
		
		public Noeud getNoeudA() {
			return a;
		}
		public Noeud getNoeudB() {
			return b;
		}

	}

	List<Noeud> sommets;
	List<Arete> aretes;
	Labyrinthe labyrinthe;
	int[][] laby;
	int dim1;
	int dim2;
	
	public Graphe(Labyrinthe labyrinthe) {
		sommets=new ArrayList<Noeud>();
		aretes=new ArrayList<Arete>();
		initChampsLaby(labyrinthe);
		remplirGraphe();
	}
	
	public void initChampsLaby(Labyrinthe labyrinthe) {
		this.labyrinthe=labyrinthe;
		this.laby=labyrinthe.getLaby();
		dim1=labyrinthe.getDimLaby(1);
		dim2=labyrinthe.getDimLaby(2);
	}
	
	
	public void remplirGraphe() {
		for(int i=0;i<dim1;i++) {
			for(int j=0;j<dim2;j++) 
				initNoeud(i,j);
		}
	}
	
	public void initNoeud(int i,int j) {
		if(!labyrinthe.isMur(new Noeud(i,j))) {
			sommets.add(new Noeud(i,j));
			initAretes(i,j);
		}
	}
	
	public void initAretes(int i, int j) {
		initAretesIVarie(i,j);
		initAretesJVarie(i,j);
	}
	
	public void initAretesIVarie(int i,int j) {
		if(i!=0) 
			addAreteI(i,i-1,j);
		if(i!=dim1-1) 
			addAreteI(i,i+1,j);
	}		
	
	public void addAreteI(int i1,int i2, int j) {
		if(!labyrinthe.isMur(new Noeud(i2,j)))
			aretes.add(new Arete(new Noeud(i1,j),new Noeud(i2,j)));
	}
	
	public void initAretesJVarie(int i,int j) {
		if(j!=0) 
			addAreteJ(i,j,j-1);
		if(j!=dim2-1) 
			addAreteJ(i,j,j+1);
	}
	
	public void addAreteJ(int i,int j1,int j2) {
		if(!labyrinthe.isMur(new Noeud(i,j2)))
			aretes.add(new Arete(new Noeud(i,j1),new Noeud(i,j2)));
	}
	
	
	public List<Noeud> getSommets(){
		return sommets;
	}
	public List<Noeud> getVoisins(Noeud n){
		List<Noeud> voisins=new ArrayList<>();
		remplirVoisins(voisins,n);
		return voisins;
	}
	
	public void remplirVoisins(List<Noeud> voisins, Noeud n) {
		for(int i=0; i<sommets.size();i++) {
			if(isArete(n,sommets.get(i))) 
				voisins.add(sommets.get(i));
		}
	}
	
	public boolean isArete(Noeud a,Noeud b) {
		for(int i=0; i<aretes.size();i++) {
			if(aretes.get(i).getNoeudA().equals(a) && aretes.get(i).getNoeudB().equals(b)) 
				return true;
		}
		return false;
	}
	
}
