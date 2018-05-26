package model;

public class Boss extends Enemy{
	private static final long serialVersionUID = 1L;

	public Boss(int x, int y, int size) {
		super(x, y, size);
		this.setHealth((short) 200);
		this.setStep(3);
	}
}
