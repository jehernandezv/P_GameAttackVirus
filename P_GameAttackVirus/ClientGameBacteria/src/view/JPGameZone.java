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
		g.setColor(Color.RED);
		for (int i = 0; i < game.getListBullet().size(); i++) {
			g.fillRect(game.getListBullet().get(i).x , game.getListBullet().get(i).y , game.getListBullet().get(i).width, game.getListBullet().get(i).height);
		}
		g.setColor(Color.GREEN);
		g.fillRect(game.getHero().x, game.getHero().y, game.getHero().width, game.getHero().height);
		}
}
