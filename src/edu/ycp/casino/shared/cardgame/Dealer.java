package edu.ycp.casino.shared.cardgame;

import java.util.ArrayList;

import edu.ycp.casino.shared.Player;

public class Dealer {
	Deck deck;
	int bettingRound;
	
	public Dealer(){
		newDeck();
		int bettingRound=0;
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
	public void dealNext(ArrayList<Player> players,Hand tableHand){
		System.out.println("betting Round: "+bettingRound);
		if(bettingRound==0)
			deal(players);
		else if(bettingRound==1)
			dealFlop(tableHand);
		else if(bettingRound==2)
			dealTurn(tableHand);
		else if(bettingRound==3)
			dealRiver(tableHand);
		else
			bettingRound=-2;
		bettingRound++;
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

	public int getBettingRound() {
		return bettingRound;
	}

	public void setBettingRound(int bettingRound) {
		this.bettingRound = bettingRound;
	}
	
}
