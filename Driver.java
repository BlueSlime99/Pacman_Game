package pacman;


public class Driver {
	static final int SPEED = 200;

	public static void main(String[] args) {
		
		Fenetre frame=new Fenetre();
		Jeu jeu=frame.getJeu();
		try {
			Thread.sleep(700);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (jeu.getPartie()) {
			jeu.step();
			try {
				Thread.sleep(SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		jeu.fin();
	}
}
