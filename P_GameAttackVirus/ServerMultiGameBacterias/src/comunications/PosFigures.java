package comunications;

public class PosFigures {
	private int x;
	private int y;
	private byte idPlayer;
	
	public PosFigures(int x, int y, byte idPlayer) {
		this.idPlayer = idPlayer;
		this.x = x;
		this.y = y;
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

	public byte getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(byte idPlayer) {
		this.idPlayer = idPlayer;
	}
	
}
