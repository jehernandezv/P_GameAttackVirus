package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Game;
import controller.Controller;

public class JPGameZone extends JPanel{
	private static final long serialVersionUID = 1L;
	private Game game;
	private int [] valuesInit;
	private ImageIcon blobuloWhite =  new ImageIcon(getClass().getResource("/globuloblanco.png"));
	private ImageIcon bullet =  new ImageIcon(getClass().getResource("/bullet.png"));
	private final byte CONSTAN_BALANCE = 25;
	
	public JPGameZone(Controller controller,Game game,int [] valuesInit) {
		this.valuesInit = valuesInit;
		this.game = game;
		this.addMouseListener(controller);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.decode("#5D92FF"));
		//Disparos de mi jugador
		for (int i = 0; i < game.getListBullet().size(); i++) {
			g.drawImage(bullet.getImage(), game.getListBullet().get(i).getX() + CONSTAN_BALANCE, game.getListBullet().get(i).getY(), null);
		}
		//Mi figura
		g.drawImage(blobuloWhite.getImage(), valuesInit[2], valuesInit[3], null);
		//Amigos
		g.setColor(Color.BLACK);
		for (int i = 0; i < game.getListFriends().size(); i++) {
			g.fillRect(game.getListFriends().get(i).getX(), game.getListFriends().get(i).getY(),
					game.getListFriends().get(i).getSize(),game.getListFriends().get(i).getSize());
		}
	}
		
}
