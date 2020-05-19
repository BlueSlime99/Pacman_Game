package pacman;

public class SuperPG extends Pacgomme {	
	
	public SuperPG(Jeu jeu, Coordinate coordPG) {
		super(jeu, coordPG);
	}

	@Override
	public void mange() {
		jeu.getPacman().setEtatPacman(new SuperPacman(jeu.getPacman()));
		jeu.setEtatFantomes();
	}

	@Override
	public int getPoints() {
		return 500;
	}

	@Override
	public Type getType() {
		return Type.Super;
	}

}
