package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MyComponent extends JComponent {

	private Labyrinthe laby;
	private Pacman pacman;
	private List<Fantome> fantomes; 
	private List<Pacgomme> pacgommes;
	private final int SCALE=18;
	private static Map<EtatPacman.State, Color> colorMapPacman = new HashMap<EtatPacman.State, Color>();
	private static Map<EtatFantome.State, Color> colorMapFantomes = new HashMap<EtatFantome.State, Color>();
	private static Map<Pacgomme.Type, Color> colorMapPG = new HashMap<Pacgomme.Type, Color>();
	
	static {
		colorMapPacman.put(EtatPacman.State.Normal, Color.YELLOW);
		colorMapPacman.put(EtatPacman.State.Invisible, new Color(242,254,138));
		colorMapPacman.put(EtatPacman.State.Super, Color.ORANGE);
		colorMapFantomes.put(EtatFantome.State.Normal, new Color(255,43,50));
		colorMapFantomes.put(EtatFantome.State.Vulnerable, new Color(0,0,255,210));
		colorMapFantomes.put(EtatFantome.State.Retour, new Color(255,255,255,220));
		colorMapPG.put(Pacgomme.Type.Normal,Color.cyan);
		colorMapPG.put(Pacgomme.Type.Invisible,new Color(153,0,204));
		colorMapPG.put(Pacgomme.Type.Super,Color.ORANGE);
		colorMapPG.put(Pacgomme.Type.Laby,Color.green);
	}
	
	
	public MyComponent(Jeu jeu) {
		super();
		this.laby=jeu.getLaby();
		this.pacman=jeu.getPacman();
		this.fantomes=jeu.getFantomes();
		this.pacgommes=jeu.getPacgommes();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		drawLaby(g);
		drawPacman(g);
		drawWrap(g);
		drawPG(g);
		drawFantomes(g);
	}
	
	public void drawLaby(Graphics g) {
		for(int i=0; i<laby.getDimLaby(1);i++) {
			for(int j=0;j<laby.getDimLaby(2);j++) {
				if(laby.getLaby()[i][j]==1)  
					drawMur(i,j,g);
			}
		}
	}
	
	public void drawMur(int i,int j, Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(i*SCALE, j*SCALE, SCALE, SCALE);
		g.setColor(new Color(150,150,150));
		g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
	}
	
	public void drawPacman(Graphics g) {
		Coordinate c=pacman.getPos();
		g.setColor(colorMapPacman.get(pacman.getEtatPacman().getState()));
		g.fillOval(c.getX()*(SCALE), c.getY()*(SCALE), SCALE, SCALE);
	}
	
	public void drawWrap(Graphics g) {
		g.setColor(new Color(255,51,153,140));
		for(int i=0;i<=laby.getDimLaby(1);i+=laby.getDimLaby(1)/2) {
			for(int j=0;j<=laby.getDimLaby(1);j+=laby.getDimLaby(1)/2) 
				drawWrap(i,j,g);
		}
	}
	
	public void drawWrap(int i,int j, Graphics g) {
		if(isPosWrap(i,j)) {
			g.fillRect(i*SCALE+SCALE/8, j*SCALE+SCALE/8, SCALE-2, SCALE-2);
			removePGatPosIJ(i,j);
		}
	}
	
	public boolean isPosWrap(int i, int j) {
		if((i==0 || i==laby.getDimLaby(1)-1) && j==laby.getDimLaby(1)/2)
			return true;
		if(i==laby.getDimLaby(1)/2 && (j==0 || j==laby.getDimLaby(1)-1))
			return true;
		return false;
	}
	
	public void removePGatPosIJ(int i,int j) {
		for(int h=0;h<pacgommes.size();h++) {
			if(pacgommes.get(h).getCoordPG().equals(new Coordinate(i,j))) {
				pacgommes.remove(h);
				break;
			}
		}
	}
	
	public void drawPG(Graphics g) {
		for(Pacgomme pg : pacgommes) {
			g.setColor(colorMapPG.get(pg.getType()));
			g.fillOval(pg.getCoordPG().getX()*SCALE+SCALE/4, pg.getCoordPG().getY()*SCALE+SCALE/4, SCALE-7, SCALE-7);
		}
	}
	
	public void drawFantomes(Graphics g) {
		for(Fantome f : fantomes) {
			g.setColor(colorMapFantomes.get(f.getEtat().getState()));
			g.fillRect(f.getPos().getX()*SCALE+SCALE/8, f.getPos().getY()*SCALE+SCALE/8, SCALE-2, SCALE-2);
		}
	}
}
