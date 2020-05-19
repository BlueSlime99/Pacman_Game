package pacman;

public class NormalPG extends Pacgomme{
	
	public NormalPG(Jeu jeu, Coordinate coordPG) {
		super(jeu, coordPG);
	}

	@Override
	public void mange() {
	}

	@Override
	public int getPoints() {
		return 100;
	}

	@Override
	public Type getType() {
		return Type.Normal;
	}

}
