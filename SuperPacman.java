package pacman;

public class SuperPacman implements EtatPacman {
	private Pacman pacman;
	
	public SuperPacman(Pacman pacman) {
		this.pacman=pacman;
	}
	
	@Override
	public void toucheFantome(Fantome fantome) {
		if(fantome.getEtat().getState()==EtatFantome.State.Normal)
			pacman.hurt();
		else 
			fantome.setRetour(true);
	}

	@Override
	public State getState() {
		return State.Super;
	}


}
