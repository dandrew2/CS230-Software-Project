package edu.ycp.casino.shared.cardgame;

public enum Suit {
	CLUBS,DIAMONDS,HEARTS,SPADES;
	public String toString(){
		if(this==CLUBS)
			return "CLB";
		else if(this==DIAMONDS)
			return "DIM";
		else if(this==HEARTS)
			return "HRT";
		else
			return "SPD";
	}
}
