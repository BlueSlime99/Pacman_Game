package pacman;

public interface EtatPacman {
	public static enum State{Normal, Super, Invisible};
	public State getState();
	public void toucheFantome(Fantome fantome);
}
