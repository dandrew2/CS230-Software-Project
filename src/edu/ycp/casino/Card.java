package edu.ycp.casino;

public class Card implements Comparable <Card>{
	// TODO - add fields an methods
	private  CardSuits suit;
	private  CardValues rank;
	
	public Card(CardSuits s, CardValues r) {
		this.suit =s;
		this.rank =r;
	}
	
	public CardSuits getSuit(){
		return suit;
	}
	public CardValues getRank(){
		return rank;
	}
	@Override
	public int compareTo(Card o) {
		int compare;
		
		compare = this.suit.compareTo(o.suit);
		if (compare != 0) {
			return compare;
		}
		// use rank as a tie-breaker
		compare = this.rank.compareTo(o.rank);
		return compare;

	}
	//symbols
	public String toString() {
        return rank.toString() + " of " + suit.toString();
	}

}
