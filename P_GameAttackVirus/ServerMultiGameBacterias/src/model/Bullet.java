package model;


public class Bullet{

	private double direction;
	private int x;
	private int y;
	private int size = 20;
	private short damage = 20;
	
	public Bullet(double direction,int x,int y) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void move(int step) {
		double x = this.x, y = this.y;
		this.x = ((int) (x + step * Math.cos(direction)));
		this.y = ((int) (y + step * Math.sin(direction)));
	}
	
	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public short getDamage() {
		return damage;
	}

	public void setDamage(short damage) {
		this.damage = damage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
