package edu.ycp.casino.shared.cardgame.poker;

public class Pot {
	int amount;
	int minBet;
	int maxBet;
	
	public Pot(){
		amount=0;
	}
	public boolean add(int given){
		if(given>=this.minBet){
			if((this.maxBet==0) || (given<=this.maxBet)){
				this.amount+=given;
				this.minBet=given;
				return true;
			}
			else
				return false;
		}
		else
			return false;
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
	public void resetMinBet() {
		this.minBet = 0;
	}
	public int getMaxBet() {
		return maxBet;
	}
	public void setMaxBet(int maxBet) {
		this.maxBet = maxBet;
	}
	public void resetMaxBet() {
		this.maxBet = 0;
	}
}