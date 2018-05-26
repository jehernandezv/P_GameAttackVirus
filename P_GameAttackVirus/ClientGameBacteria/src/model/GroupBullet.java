package model;

import java.util.ArrayList;

public class GroupBullet {
	private ArrayList<Bullet> listBullets;

	public GroupBullet() {
		this.listBullets = new ArrayList<Bullet>();
	}

	public ArrayList<Bullet> getListBullets() {
		return listBullets;
	}

	public void setListBullets(ArrayList<Bullet> listBullets) {
		this.listBullets = listBullets;
	}
	
}
