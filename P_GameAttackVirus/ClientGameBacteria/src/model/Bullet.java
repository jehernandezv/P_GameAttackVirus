package model;

import java.awt.Dimension;
import java.awt.Rectangle;

public class Bullet extends Rectangle{

	private static final long serialVersionUID = 1L;
	private double direction;
	private short damage = 20;
	
	public Bullet(double direction, short size,int x,int y) {
		super(x,y,size,size);
		this.direction = direction;
	}
	
	public void move(int step) throws InterruptedException {
		double x = this.x, y = this.y;
		this.x = ((int) (x + step * Math.cos(this.getRadians())));
		this.y = ((int) (y + step * Math.sin(this.getRadians())));
	}
	
	public double getRadians() {
		return Math.toRadians(this.getDirection());
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	public Dimension getSize(){
		return new Dimension(this.width, this.height);
	}

	public short getDamage() {
		return damage;
	}

	public void setDamage(short damage) {
		this.damage = damage;
	}
}
