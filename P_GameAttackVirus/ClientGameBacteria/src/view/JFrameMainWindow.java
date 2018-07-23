package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import model.Game;
import controller.Controller;

public class JFrameMainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPGameZone jpGameZone;
	private Game game;
	private ImageIcon icon = new ImageIcon(getClass().getResource("/user.png"));
	public static final String NAME_APP = "GAME VIRUS ATTACK";
	private JButton jButtonExitGame;
		
	public JFrameMainWindow(Controller controller,Game game,int [] valuesInit) {
		this.setIconImage(icon.getImage());
		this.setTitle(NAME_APP);
		this.game = game;
		this.setSize(game.getAreaGame()[0], game.getAreaGame()[1]);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.jpGameZone = new JPGameZone(controller,this.game,valuesInit);
		this.jButtonExitGame = new JButton("Exit Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initGame();
		this.setVisible(true);
	}
	
	private void initGame(){
		this.add(this.getContentPane().add(jButtonExitGame), BorderLayout.NORTH);
		this.add(jpGameZone,BorderLayout.CENTER);
	}

	public JPGameZone getJpGameZone() {
		return jpGameZone;
	}
}
