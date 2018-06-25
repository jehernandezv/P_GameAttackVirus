package model;

public class Enemy {
	private short health = 40;
	private int posX;
	private int posY;
	private int size;
	private double direccion;
	private byte step;
	
	public Enemy(int x,int y,int size,byte step) {
		this.step = step;
		this.posX = x;
		this.posY = y;
		this.size = size;
	}
	
	public void move(int step) {
		double x = this.getPosX(), y = this.getPosY();
		this.posX = ((int) (x + step * Math.cos(direccion)));
		this.posY = ((int) (y + step * Math.sin(direccion)));
	}

	public void decreaseHealth(short value){
		this.health -= value; 
	}

	public short getHealth() {
		return health;
	}

	public void setHealth(short health) {
		this.health = health;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public double getDireccion() {
		return direccion;
	}

	public void setDireccion(double direccion) {
		this.direccion = direccion;
	}

	public byte getStep() {
		return step;
	}

	public void setStep(byte step) {
		this.step = step;
	}
	
}
