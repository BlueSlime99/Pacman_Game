package pacman;

public interface EtatFantome {
	public static enum State{Normal, Vulnerable, Retour};
	public State getState();
	public void moveFantome();
}
