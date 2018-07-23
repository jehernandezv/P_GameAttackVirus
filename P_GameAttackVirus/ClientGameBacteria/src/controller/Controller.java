package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import comunications.Defender;
import model.Bullet;
import model.Game;
import model.Hero;
import model.InfoFiguresFriends;
import view.JDInitClient;
import view.JFrameMainWindow;

public class Controller implements MouseListener,ActionListener,IObserver,MouseMotionListener{
	private JFrameMainWindow jFrameMainWindow;
	private Defender defender;
	private Game game;
	private Timer timerRefresh;
	private JDInitClient jdInitClient;

	public Controller() throws IOException {
		jdInitClient = new JDInitClient(this);
	}

	public void actionPerformed(ActionEvent e) {
		switch (EAction.valueOf(e.getActionCommand())) {
		case INIIGAME:
			jdInitClient.showJDWait();
			jdInitClient.dispose();
			try {
				receivedValuesInit();
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	public void receivedValuesInit() throws NumberFormatException, IOException{
		ArrayList<String> valuesInit = jdInitClient.getValuesInitClient();
		byte itemSelec = jdInitClient.getSelecRadioButton();
		if (valuesInit.get(2).equals(null) || itemSelec == 2) {
			this.defender = new Defender("localhost",Integer.parseInt(valuesInit.get(1)),this,valuesInit.get(0));
		} else {
			this.defender = new Defender(valuesInit.get(2),Integer.parseInt(valuesInit.get(1)),this,valuesInit.get(0));
		}
		this.defender.requestInitGame();
	}
	
	@Override
	public void isSendValuesInit() {
		jdInitClient.disableJDWait();
		int[] areaGame = { defender.getValuesInit()[0],defender.getValuesInit()[1]};
		game = new Game(new Hero(defender.getValuesInit()[2],defender.getValuesInit()[3], 50,defender.getNameClient()), areaGame,
				addTotalFriends(defender.getValuesFriends()));
		this.jFrameMainWindow = new JFrameMainWindow(this, game,defender.getValuesInit());
		this.refresh();
	}
	
	public ArrayList<InfoFiguresFriends> addTotalFriends(int [] listFriends){
		ArrayList<InfoFiguresFriends> friends = new ArrayList<InfoFiguresFriends>();
		byte contX = 0;
		for (int i = 0; i < defender.getNamesFriends().length; i++) {
			friends.add(new InfoFiguresFriends(defender.getValuesFriends()[contX], defender.getValuesFriends()[contX + 1], 50,
					defender.getNamesFriends()[i]));
			contX += 2;
		}
		return friends;
	}
	
	
	public void mouseClicked(MouseEvent e) {
		try {
			this.defender.sendBulletServer(e.getX(), e.getY());
			try {
				e.wait(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateBullets(String values) {
		String [] bullets = values.split("/");
		addModelBullets(bullets);
	}
	
	public void addModelBullets(String [] bullets){
		ArrayList<Bullet> listBullets = generatorBullets(bullets);
		game.getHero().getGroupBullet().setListBullets(listBullets);
	}
	
	public ArrayList<Bullet> generatorBullets(String [] listBullets){
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		for (int i = 0; i < listBullets.length; i++) {
			String [] bullet = listBullets[i].split(";");
			if(bullet.length > 0){
			for (int j = 0; j < bullet.length; j++) {
				bullets.add(new Bullet(Double.parseDouble(bullet[2]), (int)20, Integer.parseInt(bullet[0]), Integer.parseInt(bullet[1])));
			}
		  }
		}
		return bullets;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	private void refresh(){
		this.timerRefresh = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrameMainWindow.getJpGameZone().repaint();
				jFrameMainWindow.getJpGameZone().revalidate();
			}
		});
		timerRefresh.start();
	}

}

