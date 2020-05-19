package pacman;

public class NormalPacman implements EtatPacman {
	private Pacman pacman;	
	
	public NormalPacman(Pacman pacman) {
		this.pacman=pacman;
	}
	
	@Override
	public void toucheFantome(Fantome fantome) {
		pacman.hurt();
	}

	@Override
	public State getState() {
		return State.Normal;
	}
	
	

}
