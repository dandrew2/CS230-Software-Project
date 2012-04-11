package edu.ycp.casino.shared.cardgame;

public enum Rank {
	ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING;
	public String toString(){
		if(this==ACE)
			return "A";
		else if(this==TWO)
			return "2";
		else if(this==THREE)
			return "3";
		else if(this==FOUR)
			return "4";
		else if(this==FIVE)
			return "5";
		else if(this==SIX)
			return "6";
		else if(this==SEVEN)
			return "7";
		else if(this==EIGHT)
			return "8";
		else if(this==NINE)
			return "9";
		else if(this==TEN)
			return "10";
		else if(this==JACK)
			return "J";
		else if(this==QUEEN)
			return "Q";
		else
			return "K";
	}
}
