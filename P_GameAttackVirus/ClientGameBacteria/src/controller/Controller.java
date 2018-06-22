package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import comunications.Defender;
import model.Bullet;
import model.Game;
import model.Hero;
import model.InfoFiguresFriends;
import view.JDInitGame;
import view.JFrameMainWindow;

public class Controller implements MouseListener,ActionListener,IObserver{
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
			receivedValuesInit();
			break;
			
		default:
			break;
		}
	}
	
	@Override
	public void isSendValuesInit() {
		int[] areaGame = { defender.getValuesInit()[0],defender.getValuesInit()[1]};
		game = new Game(new Hero(defender.getValuesInit()[2],defender.getValuesInit()[3], 50), areaGame,
				addTotalFriends(defender.getValuesFriends()));
		this.jFrameMainWindow = new JFrameMainWindow(this, game,defender.getValuesInit());
		this.game.start();
		this.refresh();
	}
	
	public ArrayList<InfoFiguresFriends> addTotalFriends(int [] listFriends){
		ArrayList<InfoFiguresFriends> friends = new ArrayList<InfoFiguresFriends>();
		byte contX = 0;
		for (int i = 0; i < 4; i++) {
			friends.add(new InfoFiguresFriends(defender.getValuesFriends()[contX], defender.getValuesFriends()[contX + 1], 50));
			contX += 2;
		}
		return friends;
	}
	
	public void receivedValuesInit(){
		try {
			this.defender = new Defender("localhost", 9000,this);
			defender.requestInitGame();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		this.game.addBullet(new Bullet(270, (short) 50, defender.getValuesInit()[2], defender.getValuesInit()[3]));
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
		this.timerRefresh = new Timer(20, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jFrameMainWindow.repaint();
				jFrameMainWindow.revalidate();
			}
		});
		timerRefresh.start();
	}



}
