package model;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.Timer;

public class Game{
	private Hero hero;
	private Timer timerCronometer;
	private Timer timerBullet;
	private String cronometer;
	private LocalDateTime cronometerGame;
	private Rectangle areaGame;
	private Boss boss;
	private short timeSave;
	
	public Game(Hero hero,Rectangle areaGame) {
		this.hero = hero;
		this.areaGame = areaGame;
	}
	
	public void stop(){
		timerCronometer.stop();
	}
	
	public void initGame(){
		cromometer();
		bulletUpdate();
	}
	
	public void cromometer(){
		timerCronometer = new Timer(1000, new ActionListener() {
			byte second = (byte) ((cronometerGame != null)? cronometerGame.getSecond():0);
			byte minute = (byte) ((cronometerGame != null)? cronometerGame.getMinute():0);
			byte hour = (byte) ((cronometerGame != null)? cronometerGame.getHour():0);
			public void actionPerformed(ActionEvent e) {
				second ++;
				if(second == 60){
					minute ++;
					second = 0;
				}
				if(minute == 60){
					hour++;
					minute = 0;
				}
				cronometer = "Hour : " + hour + " Minutes : " + minute + " Second : " + second;
			}
		
		});
		timerCronometer.start();
	}
	
	public void bulletUpdate(){
		this.timerBullet = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								//Mover disparos
							try {
								for (int i = 0; i < hero.getGroupBullet().getListBullets().size(); i++) {
									hero.getGroupBullet().getListBullets().get(i).move(5);
								    }
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
				});
				timerBullet.start();
		}
	
	public void addBullet(Bullet bullet){
		this.hero.addBullet(bullet);
	}
	
	public ArrayList<Bullet> getListBullet(){
		return hero.getListBullet();
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public String getCronometer() {
		return cronometer;
	}


	public Timer getTimerCronometer() {
		return timerCronometer;
	}

	public LocalDateTime getCronometerGame() {
		return cronometerGame;
	}

	public void setCronometerGame(LocalDateTime cronometerGame) {
		this.cronometerGame = cronometerGame;
	}

	public Rectangle getAreaGame() {
		return areaGame;
	}

	public void setAreaGame(Rectangle areaGame) {
		this.areaGame = areaGame;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public short getTimeSave() {
		return timeSave;
	}

	public void setTimeSave(short timeSave) {
		this.timeSave = timeSave;
	}
	
}
