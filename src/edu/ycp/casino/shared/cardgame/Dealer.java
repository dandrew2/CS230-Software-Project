package edu.ycp.casino.shared.cardgame;

import java.util.ArrayList;

import edu.ycp.casino.shared.Player;

public class Dealer {
	Deck deck;
	public Dealer(){
		newDeck();
	}
	
	public void newDeck(){
		this.deck=new Deck();
		this.deck.shuffle();
	}
	
	public void deal(ArrayList<Player> players){
		for(Player player : players){
			player.getHand().addCard(this.deck.drawCard());
		}
	}
	
	public void dealFlop(Hand tableHand){
		for(int x=0; x<3; x++){
			tableHand.addCard(this.deck.drawCard());
		}
	}
	
	public void dealTurn(Hand tableHand){
		tableHand.addCard(this.deck.drawCard());
	}
	
	public void dealRiver(Hand tableHand){
		tableHand.addCard(this.deck.drawCard());
	}
}
