package edu.ycp.casino.shared;

import edu.ycp.casino.shared.cardgame.Hand;

public class Player extends User{
	private Wallet wallet;
	private Hand hand;
	private int seatNum;

	public Player(int funds,Hand hand,int seat){
		this.wallet=new Wallet(funds);
		this.hand=hand;
		this.seatNum=seat;
	}
	public Player(int funds,int seat){
		this.wallet=new Wallet(funds);
		this.hand=new Hand();
		this.seatNum=seat;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
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
		return wallet.take(anti);
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
}