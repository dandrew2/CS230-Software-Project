package edu.ycp.casino;

public class User {
	private UserInfo info;
	
	public void enterGame(int id){
		info.setRoom(id); 
	}
	
	public void exitGame(int id){
		info.setRoom(0); 
	}
	
	public UserInfo getInfo(){
		return info; 
	}
}
