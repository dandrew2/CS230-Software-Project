package edu.ycp.casino.shared;

public class Wallet {
	private int balance;
	
	public Wallet(){
		this.balance = 1000; 
	}

	public Wallet(int balance){
		this.balance=balance;
	}
	
	public void addFunds(int x){
		balance += x; 
	}
	
	public int getBalance(){
		return balance;
	}
	
	public boolean takeBet(int x){
		if (balance>=x){
			balance -= x;
			return true;
		}
		else
			return false;
	}
	public void setBalance(int x){
		balance=x;
	}
}
