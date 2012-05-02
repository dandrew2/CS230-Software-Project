package edu.ycp.casino.shared;

import edu.ycp.casino.shared.cardgame.Hand;

public class Player extends User{
	private Wallet w; 
	private Hand hand;
	private int seatNum;
	
	public Player(){
		w = new Wallet(); 
		hand = new Hand(); 
	}
	
	public Player(int funds,Hand hand,int seat){
		this.w=new Wallet(funds);
		this.hand=hand;
		this.seatNum=seat;
	}
	
	public Player(int funds,int seat){
		this.w=new Wallet(funds);
		this.hand=new Hand();
		this.seatNum=seat;
	}

	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
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
		// TODO impliment a UI bet getter
		return 5;
	}
	public int getAnti(int anti) {
		w.takeBet(anti); 
		return anti;
	}
	
	public Wallet getWallet(){
		return w; 
	}

}

