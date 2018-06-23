package model;

public class Boss extends Enemy{

	public Boss(int x, int y, int size) {
		super(x, y, size);
		this.setHealth((short) 200);
		this.setStep(3);
	}
}
