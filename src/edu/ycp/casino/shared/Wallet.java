package edu.ycp.casino.shared;

public class Wallet {
	private int balance;
	
	public Wallet(int balance){
		this.balance=balance;
	}
	public int getBalance(){
		return balance;
	}
	public int take(int amount){
		if (balance<amount)
			return balance;
		else
			return amount;
	}
	public void add(int amount){
		balance+=amount;
	}
}
