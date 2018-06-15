package model;

import java.util.ArrayList;

public class GroupFriends {
	private ArrayList<InfoFigures> listFriends;
	
	public GroupFriends() {
		this.listFriends = new ArrayList<InfoFigures>();
	}
	
	public ArrayList<InfoFigures> getListFriends(){
		return listFriends;
	}
	
	public void addFriends(InfoFigures friend){
		this.listFriends.add(friend);
	}

}
