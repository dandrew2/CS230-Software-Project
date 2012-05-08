package edu.ycp.casino.shared.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
		//Collections.shuffle(this.cards);
		shuffleCard(this.cards);
	}
	//swap method for shuffle
	public void shuffleswap(List<Card>cards, int x, int y){
		Card temp = cards.get(x);
		cards.set(x, cards.get(y));
		cards.set(y, temp);

	}
	public void shuffleCard(ArrayList<Card>cards){
		Random rand = new Random();
		for (int i = cards.size(); i>1 ;i--){
			shuffleswap(cards,i-1,rand.nextInt(i));
		}
	}
}