package model;

import java.util.ArrayList;


public class Hero{
	private GroupBullet groupBullet;
	private String nameHero;
	private short health = 100;
	private Gun gun;
	private int xHero;
	private int yHero;
	private int size;

	public Hero(int x, int y ,int size,String nameHero) {
		this.nameHero = nameHero;
		this.xHero = x;
		this.yHero = x;
		this.size = size;
		groupBullet = new GroupBullet();
		this.gun = new Gun();
	}
	
	public void changePositionGun(int x,int y){
		this.gun.setX(x);
		this.gun.setY(y);
	}
	
	public double getXGun(){
		return gun.getX();
	}
	
	public double getYGun(){
		return gun.getY();
	}
	
	public void decreaseHealth(){
		this.health -= 20;
	}

	public GroupBullet getGroupBullet() {
		return groupBullet;
	}

	public short getHealth() {
		return health;
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

	public int getyHero() {
		return yHero;
	}

	public int getSize() {
		return size;
	}

	public String getNameHero() {
		return nameHero;
	}
}
