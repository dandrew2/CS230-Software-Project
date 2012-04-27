package edu.ycp.casino.shared.cardgame;

public class Card implements Comparable<Card>{
	
	//fields
	private Suit suit;
	private Rank rank;
	private boolean exposed;
	
	//Initializer
	public Card(Suit s, Rank r){
		this.suit=s;
		this.rank=r;
		this.exposed=true;
	}
	
	//getters
	public Suit getSuit(){
		return this.suit;
	}
	public Rank getRank(){
		return this.rank;
	}
	public boolean getExposed(){
		return this.exposed;
	}
	public void setExposed(boolean exposed){
		this.exposed=exposed;
	}
	public String toString(){
		if (this.exposed==true)
			return "["+this.rank.toString()+" "+this.suit.toString()+"]";
		else
			return "[XX]";
	}
	public Color getColor(){
		if (this.suit==Suit.SPADES || this.suit==Suit.CLUBS)
			return Color.BLACK;
		else
			return Color.RED;
	}

	public int compareTo(Card other) {
		if (this.getSuit().compareTo(other.getSuit())==0)
			return this.getRank().ordinal()-other.getRank().ordinal();
		else
			return this.getSuit().ordinal()-other.getSuit().ordinal();
			
	}
	public boolean equals(Card other){
		return this.compareTo(other)==0;
	}
}
