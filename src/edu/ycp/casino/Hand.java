package edu.ycp.casino;

import java.util.ArrayList;

public class Hand {
	// fields
	private ArrayList<Card> hand;



	public ArrayList<Card> getCards() {
		return hand;
	}
	public void setCards(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public Hand() {
		// generate a hand
		hand = new ArrayList<Card>(6);
	}
	public Card getCard(int i) {
		// return the i'th card in the hand
		return hand.get(i);
	}

	//get number of cards of hand
	public int getNumCards(){
		return hand.size();
	}

	//add card to player's hand
	public Card addToHand(Card c){
		hand.add(c);
		return c;
	}

	//get the value of the hand
	public int getBJHandValue (){
		int value = 0;
		for (int i = 0; i < this.getNumCards();i++){
			value = value + this.getBJValue(this.getCard(i));
		}
		return value;
	}
	//get card value and changing the ace value
	public int getBJValue(Card c){
		int cardNum=c.getRank().ordinal();
		if (cardNum>10)
			return 10;
		return cardNum;
	}
	//get highest hand
	public int compareBJ(Hand other){
		//if handvalue are the same
		if (other.getBJHandValue() == this.getBJHandValue()){
			//compare number of cards in hand
			if (other.getNumCards() == this.getNumCards()){
				return 0;
			}
			//if less cards
			else if(other.getNumCards() < this.getNumCards()){
				return 1;
			}
			//if more cards
			else {
				return -1;
			}
		}
		// if handvalue is less
		else if (other.getBJHandValue() < this.getBJHandValue()){
			return -1;
		}
		// if handvalue is more
		else {
			return -1;
		}

	}


}
