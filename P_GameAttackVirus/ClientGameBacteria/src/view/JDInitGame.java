package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

import controller.Controller;
import controller.EAction;

public class JDInitGame extends JDialog{
	private static final long serialVersionUID = 1L;
	private JButton jButtonInitGame;
	
	public JDInitGame(Controller controller) {
		this.setLayout(new BorderLayout());
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.jButtonInitGame = new JButton("initGame");
		this.jButtonInitGame.setActionCommand(EAction.INIIGAME.name());
		this.jButtonInitGame.addActionListener(controller);
		this.add(jButtonInitGame,BorderLayout.CENTER);
	}
	
	public void showJDInit(){
		this.setVisible(true);
	}
	
	public void disableJDInit(){
		this.dispose();
	}

}
