package pacman;

public class LabyPG extends Pacgomme {
	
	public LabyPG(Jeu jeu, Coordinate coordPG) {
		super(jeu, coordPG);
	}

	@Override
	public void mange() {
		jeu.modifyLaby();
	}

	@Override
	public int getPoints() {
		return 1000;
	}

	@Override
	public Type getType() {
		return Type.Laby;
	}

}
