package edu.ycp.casino.shared;

public class Wallet {
	private int balance; 
	
	public Wallet(){
		this.balance = 0; 
	}
	public void addFunds(int x){
		balance += x; 
	}
	
	public int getBalance(){
		return balance;
	}
	
	public void takeBet(int x){
		balance -= x;
	}
}
