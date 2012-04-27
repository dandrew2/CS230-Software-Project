package edu.ycp.casino.shared.cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards=new ArrayList<Card>();

    public Deck() {
    	for(Rank r : Rank.values()){
    		for(Suit s : Suit.values()){
    			cards.add(new Card(s,r));
    		}
    	}
    }

    public int getNumCards() {
    	return this.cards.size();
    }

    public Card getCard(int i) {
            return this.cards.get(i);
    }
    public void addCard(Card c){
    		this.cards.add(c);
    }

    public Card drawCard() {
            Card temp=this.cards.get(this.cards.size()-1);
            this.cards.remove(this.cards.size()-1);
            return temp;
    }

    public void shuffle() {
            Collections.shuffle(this.cards);
    }
}