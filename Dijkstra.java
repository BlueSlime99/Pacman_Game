package pacman;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
	private Graphe graphe;
	private Noeud sommetInit;
	private Noeud sommetDest;
	
	private List<Noeud> chemin;
	private List<Noeud> sommets;
	
	
	public Dijkstra(Coordinate sommetInit,Coordinate sommetDest,Labyrinthe labyrinthe) {
		this.graphe=new Graphe(labyrinthe);
		initSommets(sommetInit,sommetDest);
		initChemin();		
	}
	
	public void initSommets(Coordinate sommetInit, Coordinate sommetDest) {
		sommets=graphe.getSommets();
		this.sommetInit=coordToNoeud(sommetInit);
		this.sommetDest=coordToNoeud(sommetDest);
	}
	
	public Noeud coordToNoeud(Coordinate c) {
		for(int i=0;i<sommets.size();i++) {
			if(c.equals(sommets.get(i)))
				return sommets.get(i);
		}
		return null;
	}
	
	public void initChemin() {
		chemin=new ArrayList<>();
		if(sommetInit!=null && sommetDest!=null)
			setChemin();
	}
	
	
	public void setChemin() {
		setSommets();
		remplirChemin();
	}
	
	public void setSommets() {
		sommetDest.setVal(0);
		while(nbNonMarques()>1) 
			marquerNoeud();
	}
	
	public int nbNonMarques() {
		int nb=0;
		for(int i=0;i<sommets.size();i++) {
			if(nonMarque(sommets.get(i)))
				nb++;
		}
		return nb;
	}

	public void marquerNoeud() {
		int val=1;
		Noeud n=ValMin();
		n.setMarque(true);
		marquerVoisins(n,val);
		val++;
	}
	
	public void marquerVoisins(Noeud n,int val) {
		for(Noeud j : graphe.getVoisins(n)) {
			if(nonMarque(j)  &&  j.getVal()>(n.getVal()+val)) {
					j.setVal(n.getVal()+val);
					j.setPrec(n);
			}
		}
	}	
	
	public Noeud ValMin() {
		Noeud n=null;
		int valMin=10000;
		for(int i=1;i<sommets.size();i++) {
			if(nonMarque(sommets.get(i)) && sommets.get(i).getVal()<valMin) {
					valMin=sommets.get(i).getVal();
					n=sommets.get(i);
			}
		}
		return n;
	}
	
	public boolean nonMarque(Noeud s) {
		return (!s.getMarque());
	}
	
	public void remplirChemin() {
		Noeud p=sommetInit;
		while(p.getPrec()!=null) {
			p=p.getPrec();
			chemin.add(p);
		}
		if(!chemin.contains(sommetDest)) 
			chemin.add(sommetDest);
	}

	public List<Noeud> getChemin(){
		return chemin;
	}
}
