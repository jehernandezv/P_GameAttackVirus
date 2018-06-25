package model;

import java.util.ArrayList;


public class Hero{
	private GroupBullet groupBullet;
	private byte idHero;
	private String nameHero;
	private short health = 100;
	private int xHero;
	private int yHero;
	private int size = 50;

	public Hero(int x, int y,String nameHero,byte idHero) {
		this.idHero = idHero;
		this.nameHero = nameHero;
		this.xHero = x;
		this.yHero = x;
		groupBullet = new GroupBullet();
	}
	
	public void decreaseHealth(){
		this.health -= 20;
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

	public byte getIdHero() {
		return idHero;
	}

	public void setIdHero(byte idHero) {
		this.idHero = idHero;
	}
	
	
}
