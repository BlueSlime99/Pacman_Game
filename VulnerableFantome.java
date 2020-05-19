package pacman;

public class VulnerableFantome implements EtatFantome{
	private Fantome fantome;
	private boolean bouger;
	
	public VulnerableFantome(Fantome fantome) {
		this.fantome=fantome;
	}
	
	public void setBouger() {
		if(bouger)
			bouger=false;
		else
			bouger=true;
	}
	
	public boolean getBouger() {
		return bouger;
	}

	@Override
	public void moveFantome() {
		if(fantome.getRetour()) 
			this.fantome.setEtat();
		else {
			setBouger();
			if(getBouger()) 
				fantome.moveAleatoire();
		}
	}

	@Override
	public State getState() {
		return State.Vulnerable;
	}


}
