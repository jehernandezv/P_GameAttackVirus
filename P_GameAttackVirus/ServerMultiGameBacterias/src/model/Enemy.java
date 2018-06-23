package model;

public class Enemy {
	private int step = 2;
	private short health = 40;
	private int posX;
	private int posY;
	private int size;
	
	public Enemy(int x,int y,int size) {
		this.posX = x;
		this.posY = y;
		this.size = size;
	}

	public void chase(int xHero, int yHero){
		if(this.posX >= xHero){
			this.moveLeft();
		}
		if(this.posX <= xHero){
			this.moveRigth();
		}
		if(this.posY > yHero){
			this.moveUP();
		}
		if(this.posY < yHero){
			this.moveDown();
		}
	}
	
	public void moveLeft(){
		this.posX -=step ;
	}
	
	public void moveRigth(){
		this.posX += step ;
	}
	
	public void moveUP(){
		this.posY -= step;
	}
	
	public void moveDown(){
		this.posY += step ;
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

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
