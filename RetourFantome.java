package pacman;

import java.util.List;

public class RetourFantome implements EtatFantome{
	private Fantome fantome;
	private List<Noeud> plusCourtChemin;
	
	public RetourFantome(Fantome fantome) {
		this.plusCourtChemin=fantome.getJeu().createChemin(fantome.getPos(),fantome.getStart());
		this.fantome=fantome;
		this.fantome.resetCompteurEffet();
		this.fantome.addValToCompteurEffet(plusCourtChemin.size());
	}
	
	public void retour() {
		if(plusCourtChemin.size()>0) 
			majPosFantome();
		if(verifRetour())
			fantome.setEtat();
	}
	
	public void majPosFantome() {
		fantome.setPositions(plusCourtChemin.get(0));
		plusCourtChemin.remove(0);
		this.fantome.decCompteurEffet();
	}
	
	public boolean verifRetour() {
		if(this.fantome.getCompteurEffet()==0)
			return true;
		return false;
	}

	@Override
	public void moveFantome() {
		retour();
	}

	@Override
	public State getState() {
		return State.Retour;
	}

}
