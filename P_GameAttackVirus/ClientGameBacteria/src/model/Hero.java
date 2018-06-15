package model;

import java.util.ArrayList;


public class Hero{
	private GroupBullet groupBullet;
	private short health = 100;
	private int xHero;
	private int yHero;
	private int size;

	public Hero(int x, int y ,int size) {
		this.xHero = x;
		this.yHero = x;
		this.size = size;
		groupBullet = new GroupBullet();
	}
	
	public void decreaseHealth(){
		this.health -= 20;
	}

	public GroupBullet getGroupBullet() {
		return groupBullet;
	}

	public void setGroupBullet(GroupBullet groupBullet) {
		this.groupBullet = groupBullet;
	}

	public short getHealth() {
		return health;
	}

	public void setHealth(short health) {
		this.health = health;
	}
	
	public ArrayList<Bullet> getListBullet(){
		return groupBullet.getListBullets();
	}
	
	public void addBullet(Bullet bullet){
		this.groupBullet.getListBullets().add(bullet);
	}

	public int getxHero() {
		return xHero;
	}

	public void setxHero(int xHero) {
		this.xHero = xHero;
	}

	public int getyHero() {
		return yHero;
	}

	public void setyHero(int yHero) {
		this.yHero = yHero;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	

}
