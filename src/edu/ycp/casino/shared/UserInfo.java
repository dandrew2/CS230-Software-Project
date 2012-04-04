package edu.ycp.casino.shared;

public class UserInfo {
	private String userName; 
	private String password; 
	private float balance; 
	private int roomNum; 
	private int userID;
	
	public void setUserName(String n){
		this.userName = n; 
	}
	
	public String getUserName(){
		return userName; 
	}
	
	public void setPassword(String pw){
		this.password = pw; 
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setBalance(float balance){
		this.balance = balance; 
	}
	
	public float getBalance(){
		return balance; 
	}
	
	public int getRoom(){
		return roomNum; 
	}
	
	public void setRoom(int room){
		this.roomNum = room; 
	}
	
	public void setUserID(int id){
		this.userID = id; 
	}
	
	public int getUserID(){
		return userID; 
	}
}
