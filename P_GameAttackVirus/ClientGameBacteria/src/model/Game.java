package model;

import java.util.ArrayList;

public class Game{
	private Hero hero;
	private int [] areaGame;
	private GroupFriends groupFriends;
	private ArrayList<Enemy> listEnemys;
	
	public Game(Hero hero,int [] areaGame,ArrayList<InfoFiguresFriends> lisFriends) {
		this.hero = hero;
		this.areaGame = areaGame;
		this.groupFriends = new GroupFriends(lisFriends);
		this.listEnemys = new ArrayList<Enemy>();
	}
	
	public double getXGun(){
		return hero.getXGun();
	}
	
	public double getYGun(){
		return hero.getYGun();
	}
	
	public void changePositionGun(int x,int y){
		hero.changePositionGun(x, y);
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

	public int[] getAreaGame() {
		return areaGame;
	}

	public void setAreaGame(int[] areaGame) {
		this.areaGame = areaGame;
	}


	public ArrayList<Enemy> getListEnemys() {
		return listEnemys;
	}


	public void setListEnemys(ArrayList<Enemy> listEnemys) {
		this.listEnemys = listEnemys;
	}

}