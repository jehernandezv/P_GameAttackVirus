package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.Game;
import controller.Controller;

public class JFrameMainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPGameZone jpGameZone;
	private Game game;
		
	public JFrameMainWindow(Controller controller,Game game) {
		this.game = game;
		this.setSize(game.getAreaGame().width, game.getAreaGame().height);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void initGame(Controller controller){
		this.jpGameZone = new JPGameZone(controller,this.game);
		this.add(jpGameZone,BorderLayout.CENTER);
	}
	
}
