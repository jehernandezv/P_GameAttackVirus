package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Timer;

import comunications.Defender;
import model.Bullet;
import model.Game;
import model.Hero;
import view.JDInitGame;
import view.JFrameMainWindow;

public class Controller implements MouseListener,ActionListener{
	private JFrameMainWindow jFrameMainWindow;
	private Defender defender;
	private JDInitGame jdInitGame;
	private Game game;
	private Timer timerRefresh;

	
	public Controller() throws IOException {
		jdInitGame = new JDInitGame(this);
		jdInitGame.showJDInit();
	}

	public void actionPerformed(ActionEvent e) {
		switch (EAction.valueOf(e.getActionCommand())) {
		case INIIGAME:
			jdInitGame.disableJDInit();
			try {
				this.defender = new Defender("localhost", 9000);
				defender.requestInitGame();
				if(defender.isValuesInit()){
				int [] areaGame = {defender.getValuesInit()[0],defender.getValuesInit()[1]};
				//System.out.println("with: " + defender.getValuesInit()[0] + " heith: " + defender.getValuesInit()[1]);
				game = new Game(new Hero(defender.getValuesInit()[2],defender.getValuesInit()[3], 50),areaGame);
				this.jFrameMainWindow = new JFrameMainWindow(this,game);
				this.jFrameMainWindow.initGame(this);
				this.game.start();
				this.refresh();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
		}
			break;
			
		default:
			break;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		this.game.addBullet(new Bullet(0, (short) 50, game.getHero().getxHero(), game.getHero().getyHero()));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void refresh(){
		this.timerRefresh = new Timer(10, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jFrameMainWindow.repaint();
				jFrameMainWindow.revalidate();
			}
		});
		timerRefresh.start();
	}


}
