package model;

import java.awt.Rectangle;
import java.util.ArrayList;


public class Hero extends Rectangle{
	private static final long serialVersionUID = 1L;
	private GroupBullet groupBullet;
	private short health = 100;

	public Hero(int x, int y ,int size) {
		super(x,y,size,size);
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

}
