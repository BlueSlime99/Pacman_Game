package pacman;

public class NormalFantome implements EtatFantome {
	private Fantome fantome;
	
	public NormalFantome(Fantome fantome) {
		this.fantome=fantome;
	}
	
	@Override
	public void moveFantome() {
		fantome.moveAleatoire();
	}

	@Override
	public State getState() {
		return State.Normal;
	}

}
