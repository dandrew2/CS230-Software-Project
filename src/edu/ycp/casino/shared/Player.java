package edu.ycp.casino.shared;

public class Player extends User{
	private int balance; 
	
	public Player(int startingBalance) {
		this.balance = startingBalance;
	}

	public int getBalance(){
		return balance; 
	}
	
	public void setBalance(int newBalance)
	{
		this.balance = newBalance;
	}
}
