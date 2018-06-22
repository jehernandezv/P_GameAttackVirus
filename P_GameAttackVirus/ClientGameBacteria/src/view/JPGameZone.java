package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Bullet;
import model.Game;
import model.InfoFiguresFriends;
import controller.Controller;

public class JPGameZone extends JPanel{
	private static final long serialVersionUID = 1L;
	private Game game;
	private int [] valuesInit;
	private ImageIcon blobuloWhite =  new ImageIcon(getClass().getResource("/globuloblanco.png"));
	private ImageIcon bullet =  new ImageIcon(getClass().getResource("/bullet.png"));
	private final byte CONSTANT_BALANCE = 25;
	
	public JPGameZone(Controller controller,Game game,int [] valuesInit) {
		this.valuesInit = valuesInit;
		this.game = game;
		this.addMouseListener(controller);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.decode("#5D92FF"));
		//Disparos de mi jugador
		for (Iterator<?> iterator = game.getListBullet().iterator(); iterator.hasNext();) {
			Bullet bullet = (Bullet) iterator.next();
			g.drawImage(this.bullet.getImage(),bullet.getX() + CONSTANT_BALANCE, bullet.getY(), null);
		}
		//Mi figura
		g.drawImage(blobuloWhite.getImage(), valuesInit[2], valuesInit[3], null);
		//Amigos
		g.setColor(Color.BLACK);
		for (Iterator<?> iterator = game.getListFriends().iterator(); iterator.hasNext();) {
			InfoFiguresFriends figure = (InfoFiguresFriends) iterator.next();
			g.fillRect(figure.getX(), figure.getY(), figure.getSize(), figure.getSize());
		}
	}
		
}
