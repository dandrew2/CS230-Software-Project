package edu.ycp.casino;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	// fields
	private ArrayList<Card> deck; 
	private int index;
	public Deck() {
		// generate a full deck (52 cards)
		deck = new ArrayList<Card>(52);
	}
	public int getNumCards() {
		// return number of cards in the deck
		return deck.size();
	}
	public Card getCard(int i) {
		// return the i'th card in the deck
		return deck.get(i);
	}

	public Card drawCard() {
		// draw a Card from the top of the deck
		// and return a reference to it
		deck.remove(deck.size()-1);
		return deck.get(deck.size()-1);
	}

	public void shuffle() {
		// randomly shuffle the deck
		// use the Collections.shuffle() static method!
		Collections.shuffle(deck);
	}
	//main deck
	public void fillDeck(){
		// use allSuits[j] and allRanks[i] to create a Card
		CardSuits[] allSuits = CardSuits.values();
		CardValues[] allRanks = CardValues.values();
		for (int j = 0; j < allSuits.length;j++){
			for (int i = 0; i<allRanks.length; i++){
				deck.add(new Card(allSuits[j],allRanks[i]));
			}
		}
	}
	//add card
	public void add(Card card) {
		deck.add(card);
	}
	//get top card
	public Card getTopCard() {
		return deck.get(deck.size()-1);
	}
	
	public void setExposeIndex(int index){
		this.index = index;
	}
	//get index
	public int getExposeIndex() {
		return index;
	}
	//get the top index card
	public int indexOfTopCard() {
		return deck.size()-1;
	}
	//remove from deck/pile
	public ArrayList<Card> removeCards(int index) {
		deck.remove(index);
		return deck;
	}
	//place cards to new pile
	public void placeCards(ArrayList<Card> cardsToPlace){
		for(int i=0; i< cardsToPlace.size();i++){
			deck.add(cardsToPlace.get(i));
		}
	}
}
