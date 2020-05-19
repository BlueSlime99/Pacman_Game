package pacman;

public class InvisiblePacman implements EtatPacman{	
	@Override
	public void toucheFantome(Fantome fantome) {
	}
	@Override
	public State getState() {
		return State.Invisible;
	}
	

}
