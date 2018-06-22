package model;

import java.util.ArrayList;

public class GroupFriends {
	private ArrayList<InfoFiguresFriends> listFriends;
	
	public GroupFriends(ArrayList<InfoFiguresFriends> listFriends) {
		this.listFriends = listFriends;
	}
	
	public ArrayList<InfoFiguresFriends> getListFriends(){
		return listFriends;
	}
	
	public void addFriends(InfoFiguresFriends friend){
		this.listFriends.add(friend);
	}
	
	public void addFriendList(InfoFiguresFriends figureFriend){
		this.listFriends.add(figureFriend);
	}

	public void setListFriends(ArrayList<InfoFiguresFriends> listFriends) {
		this.listFriends = listFriends;
	}
	

}
