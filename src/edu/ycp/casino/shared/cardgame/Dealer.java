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
		int x=0;
		for(Player player : players){
			for(int cardNum=0; cardNum<2; cardNum++){
				player.getHand().addCard(this.deck.drawCard());
			}
		System.out.println("Player "+x+" delt hand: "+player.getHand().toString());
		x++;
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
	//getting dealer to draw for blackjack
		public void dealTo(Player p){
			p.getHand().addCard(this.deck.drawCard());
		}
}
