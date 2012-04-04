package edu.ycp.casino.shared;

import java.util.ArrayList;

public class Game {
	private int roomNumber; 
	private ArrayList<Player> players;
	private float pot; 
	
	public void addPlayer(Player p){
		players.add(p); 
	}
	
	public void removePlayer(Player p){
		players.remove(p); 
	}
	
	public int getNumPlayers(){
		return players.size(); 
	}
	
	public int getRoomNumber(){
		return roomNumber; 
	}
	
	public void setRoomNumber(int num){
		this.roomNumber = num; 
	}
	
	public void givePot(Player p){
		float balance = p.getInfo().getBalance(); 	
		p.getInfo().setBalance(balance + pot); 
	}
}
