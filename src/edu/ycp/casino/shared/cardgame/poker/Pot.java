package edu.ycp.casino.shared.cardgame.poker;

public class Pot {
	int amount;
	int minBet;
	
	public Pot(){
		amount=0;
	}
	public void add(int given){
		this.amount+=given;
		this.minBet=given;
	}
	public int takeAll(){
		int temp=this.amount;
		this.amount=0;
		return temp;
	}
	public int getAmount(){
		return this.amount;
	}
	public int getMinBet() {
		return minBet;
	}
	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}
	public void resetMinBet(int anti) {
		this.minBet = anti;
	}
}