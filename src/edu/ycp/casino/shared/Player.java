package edu.ycp.casino.shared;

import edu.ycp.casino.shared.cardgame.Hand;

public class Player extends User{
	private Wallet w; 
	private Hand hand;
	
	public Player(){
		w = new Wallet(); 
		hand = new Hand(); 
	}
	
	public int getBalance() {
		return w.getBalance(); 
	}
	public void addBalance(int balance) {
		w.addFunds(balance); 
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	public int getBet() {
		// TODO impliment a bet getter
		return 5;
	}
	public int getAnti(int anti) {
		w.takeBet(anti); 
		return anti;
	}

}
