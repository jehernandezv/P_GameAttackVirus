package model;

public class InfoFiguresFriends {
	private String nameFriend;
	private int x;
	private int y;
	private int size;
	
	
	public InfoFiguresFriends(int x, int y, int size,String nameFriends) {
		this.nameFriend = nameFriends;
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public String getNameFriend() {
		return nameFriend;
	}

}
