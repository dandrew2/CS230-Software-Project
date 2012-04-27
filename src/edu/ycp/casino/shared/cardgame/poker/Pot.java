package edu.ycp.casino.shared.cardgame.poker;

public class Pot {
	int amount;
	public Pot(){
		amount=0;
	}
	public void add(int given){
		this.amount+=given;
	}
	public int takeAll(){
		int temp=this.amount;
		this.amount=0;
		return temp;
	}
	public int getAmount(){
		return this.amount;
	}
}
