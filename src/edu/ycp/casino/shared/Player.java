package edu.ycp.casino.shared;

import edu.ycp.casino.shared.cardgame.Hand;

public class Player extends User{
	private int balance;
	//TODO: Make a wallet
	private Hand hand;
	
	public int getBalance() {
		return balance;
	}
	public void addBalance(int balance) {
		this.balance += balance;
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	public int getBet() {
		// TODO impliment a bet getter
		return 0;
	}
	public int getAnti(int anti) {
		balance-=anti;
		return anti;
	}

}
