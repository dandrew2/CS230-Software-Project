package edu.ycp.casino.shared;

import edu.ycp.casino.shared.cardgame.Hand;
import edu.ycp.casino.shared.cardgame.HandType;

public class Player{
	private Wallet w; 
	private Hand hand;
	private int seatNum;
	private int holdingBet;
	private HandType handType;
	private boolean folded;
	
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
	public int getAnti(int anti) {
		w.takeBet(anti); 
		return anti;
	}
	
	public Wallet getWallet(){
		return w;
	}
	public void setHoldingBetPercent(double percent,int minBet){
		if(w.getBalance()>=minBet)
			holdingBet=(int)(percent*(w.getBalance()-minBet))+minBet;
		else
			holdingBet=0;
	}
	public void setHoldingBet(int bet,int minBet){
		if (bet>=minBet){
			if(w.getBalance()>=bet)
				holdingBet=bet;
			else
				holdingBet=0;
		}
	}
	public int getHoldingBet(){
		return holdingBet;
	}
	
	public boolean takeHoldingBet(){
		return w.takeBet(holdingBet);
	}
	
	public int getBet() {
		// TODO impliment a UI bet getter
		return 5;
	}

	public HandType getHandType() {
		return handType;
	}
	
	public void setHandType(HandType handType) {
		this.handType = handType;
	}

	public boolean isFolded() {
		return folded;
	}
	public void fold() {
		this.folded = true;
	}
	public void unfold(){
		this.folded = false;
	}
	
}

