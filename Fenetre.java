package pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements KeyListener,PacmanObserver {
	private MyComponent compJeu;
	private Box compInfos;
	private JPanel principal;
	private String infoVies;
	private String infoPoints;
	private JLabel labelVies;
	private JLabel labelPoints;
	private Jeu jeu;
	private final int WIDTH=360;
	private final int HEIGHT=405;
	
	public Fenetre() {
		super("Pacman");
		this.setSize(WIDTH,HEIGHT);
		initJeu();
		initPrincipal();
		resumeInitFrame();
	}
	
	public void initJeu() {
		jeu=new Jeu(this);
		this.jeu.registerToPacman(this);
	}
	
	public void initPrincipal() {
		principal=new JPanel(new BorderLayout());
		initComps();
		principal.add(compInfos, BorderLayout.PAGE_START);
		principal.add(compJeu, BorderLayout.CENTER);
		this.setContentPane(principal);
	}
	
	public void initComps() {
		compInfos=initCompInfos();
		compJeu=new MyComponent(jeu);
	}
	
	public void resumeInitFrame() {
		this.addKeyListener(this);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setInfos() {
		if(jeu.getPacman().getVies()>0)
			infoVies="VIES "+jeu.getPacman().getVies();
		else 
			infoVies="MORT";
		infoPoints="POINTS "+jeu.getPoints();
	}
	
	public void setLabels() {
		setInfos();
		setLabelVies();	
		setLabelPoints();
	}
	
	public void setLabelVies() {
		labelVies=new JLabel(infoVies);
		labelVies.setBounds(50, 50, 100, 30);
	    labelVies.setFont(new Font("Verdana", Font.BOLD, 18));
	    labelVies.setForeground(Color.white);
	}
	
	public void setLabelPoints() {
	    labelPoints=new JLabel(infoPoints);
		labelPoints.setBounds(50, 50, 100, 30);
	    labelPoints.setFont(new Font("Verdana", Font.BOLD, 18));
	    labelPoints.setForeground(Color.white);
	}
	
	public Box initCompInfos() {
		setLabels();
		Box box=Box.createHorizontalBox();
		initBox(box);
		return box;
	}
	
	public void initBox(Box box) {
		box.setOpaque(true);
		box.setBackground(Color.DARK_GRAY);
		addCompToBox(box);
	}
	
	public void addCompToBox(Box box) {
		box.add(labelVies);
		box.add(Box.createHorizontalGlue());
		box.add(Box.createHorizontalStrut(5));
		box.add(labelPoints);
	}
	
	public void repaintCompInfos() {
		setInfos();
		labelVies.setText(infoVies);
		labelPoints.setText(infoPoints);
	}
	
	public Jeu getJeu() {
		return jeu;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			jeu.getPacman().setDir(Direction.Down);
		if(e.getKeyCode()==KeyEvent.VK_UP)
			jeu.getPacman().setDir(Direction.Up);
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			jeu.getPacman().setDir(Direction.Right);
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			jeu.getPacman().setDir(Direction.Left);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notify(Coordinate pacmanPos,Coordinate pacmanPosPrec) {
		repaintCompInfos();
		principal.repaint();
	}

}
