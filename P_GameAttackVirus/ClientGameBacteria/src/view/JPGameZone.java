package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
	private ImageIcon imgFriend =  new ImageIcon(getClass().getResource("/friend.png"));
	private ImageIcon fondo =  new ImageIcon(getClass().getResource("/fonfo.JPG"));
	private final byte CONSTANT_BALANCE = 25;
	private final byte CONSTANT_ALIGNMENT = 10;
	private Font fontNamePlayers = new Font("Arial", Font.BOLD, 16);
	/**
	 * Constructor del panel de la zona de juego
	 * @param controller Se hace nec esario tener una instancia del constructor para poner los escuchadores del mouse
	 * @param game Le ingresa un objeto Game ya que esta posee todos los datos del juego necesarios para pintarlos en la vista
	 * @param valuesInit Este vector hace referencia a los valores generales del juego, como tamaño de la ventana
	 */
	public JPGameZone(Controller controller,Game game,int [] valuesInit) {
		this.setLayout(new BorderLayout());
		this.valuesInit = valuesInit;
		this.game = game;
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
	}

	/*
	  * Se hace uso del metodo paint, sobreescribiendolo para asi poder mostrar graficamente datos relacionados con el juego
	  * @see javax.swing.JComponent#paint(java.awt.Graphics)
	  */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(fondo.getImage(), 0, 0, null);
		//Disparos de mi jugador y amigos
		for (Iterator<?> iterator = game.getListBullet().iterator(); iterator.hasNext();){
			Bullet bullet = (Bullet) iterator.next();
			g.drawImage(this.bullet.getImage(),bullet.getX() + CONSTANT_BALANCE, bullet.getY(), null);
		}
		//Mi figura
		g.drawImage(blobuloWhite.getImage(), valuesInit[2], valuesInit[3] - CONSTANT_BALANCE,null);
		g.setFont(fontNamePlayers);
		g.setColor(Color.decode("#91DC5A"));
		g.setFont(fontNamePlayers);
		g.drawString("YOU " + game.getNameHero(), valuesInit[2] + CONSTANT_ALIGNMENT, valuesInit[3] - CONSTANT_BALANCE);
		//Amigos
		for (Iterator<?> iterator = game.getListFriends().iterator(); iterator.hasNext();) {
			InfoFiguresFriends figure = (InfoFiguresFriends) iterator.next();
			g.drawImage(imgFriend.getImage(), figure.getX() - CONSTANT_BALANCE, figure.getY() - CONSTANT_BALANCE, null);
			g.setColor(Color.BLUE);
			g.drawString(figure.getNameFriend(), figure.getX() + CONSTANT_ALIGNMENT, figure.getY() - CONSTANT_ALIGNMENT);
		}
		
//		//Enemys
//		for (Iterator<?> iterator = game.getListEnemys().iterator(); iterator.hasNext();) {
//			Enemy enemy = (Enemy) iterator.next();
//			g.fillRect(enemy.getPosX(), enemy.getPosY(), 50, 50);
//		}
	}
		
}
