package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.Game;
import controller.Controller;

public class JPGameZone extends JPanel{
	private static final long serialVersionUID = 1L;
	private Game game;
	private int [] valuesInit;
	
	public JPGameZone(Controller controller,Game game,int [] valuesInit) {
		this.valuesInit = valuesInit;
		this.game = game;
		System.out.println("x " + valuesInit[2] + " y " + valuesInit[3]);
		this.addMouseListener(controller);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.decode("#5D92FF"));
		//Disparos de mi jugador
		g.setColor(Color.RED);
		for (int i = 0; i < game.getListBullet().size(); i++) {
			g.fillRect(game.getListBullet().get(i).getX() , game.getListBullet().get(i).getY() , game.getListBullet().get(i).getSize(), game.getListBullet().get(i).getSize());
		}
		//Mi figura
		g.setColor(Color.GREEN);
		g.fillRect(valuesInit[2], valuesInit[3], 50, 50);
		
		//Amigos
		g.setColor(Color.BLACK);
		for (int i = 0; i < game.getListFriends().size(); i++) {
			g.fillRect(game.getListFriends().get(i).getX(), game.getListFriends().get(i).getY(),
					game.getListFriends().get(i).getSize(),game.getListFriends().get(i).getSize());
		}
	}
		
}
