package pacman;

public class InvisiblePG extends Pacgomme {
	
	public InvisiblePG(Jeu jeu, Coordinate coordPG) {
		super(jeu, coordPG);
	}

	@Override
	public void mange() {
		super.jeu.setEtatPacman(new InvisiblePacman());
	}

	@Override
	public int getPoints() {
		return 300;
	}

	@Override
	public Type getType() {
		return Type.Invisible;
	}

}
