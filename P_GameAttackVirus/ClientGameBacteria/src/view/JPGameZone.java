package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.Game;
import controller.Controller;

public class JPGameZone extends JPanel{
	private static final long serialVersionUID = 1L;
	private Game game;
	
	public JPGameZone(Controller controller,Game game) {
		this.game = game;
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
		g.fillRect(game.getHero().getxHero(), game.getHero().getyHero(), game.getHero().getSize(), game.getHero().getSize());
		
		//Amigos
		g.setColor(Color.BLACK);
		for (int i = 0; i < game.getListFriends().size(); i++) {
			g.fillRect(game.getListFriends().get(i).getX(), game.getListFriends().get(i).getY(),
					game.getListFriends().get(i).getSize(),game.getListFriends().get(i).getSize());
		}
	}
		
}
