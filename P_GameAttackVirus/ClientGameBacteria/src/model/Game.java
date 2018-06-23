package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Game extends Thread{
	private Hero hero;
	private String cronometer;
	private LocalDateTime cronometerGame;
	private int [] areaGame;
	private Boss boss;
	private short timeSave;
	private GroupFriends groupFriends;
	
	public Game(Hero hero,int [] areaGame,ArrayList<InfoFiguresFriends> lisFriends) {
		this.hero = hero;
		this.areaGame = areaGame;
		this.groupFriends = new GroupFriends(lisFriends);
	}
	

	@Override
	public void run() {
		//Mover disparos
		while(true){
		try {
			Thread.sleep(10);
			if(hero.getGroupBullet().getListBullets().size() > 0){
			for (Iterator<?> it = getListBullet().iterator(); it.hasNext();) {
				Bullet bullet = (Bullet) it.next();
				if(bullet.getX() > areaGame[0] || bullet.getY() > areaGame[1]){
					it.remove();
				}else{
					bullet.move(5);
				}
			  }
			}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public String getNameHero(){
		return hero.getNameHero();
	}
	
	public void addFriendList(InfoFiguresFriends figureFriend){
		this.groupFriends.addFriendList(figureFriend);
	}
	
	public int getXHero(){
		return this.hero.getxHero();
	}
	
	public int getYHero(){
		return this.hero.getyHero();
	}
	
	public int getHeroSize(){
		return this.hero.getSize();
	}
	
	public ArrayList<InfoFiguresFriends> getListFriends(){
		return groupFriends.getListFriends();
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

	public LocalDateTime getCronometerGame() {
		return cronometerGame;
	}

	public void setCronometerGame(LocalDateTime cronometerGame) {
		this.cronometerGame = cronometerGame;
	}

	public int[] getAreaGame() {
		return areaGame;
	}

	public void setAreaGame(int[] areaGame) {
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