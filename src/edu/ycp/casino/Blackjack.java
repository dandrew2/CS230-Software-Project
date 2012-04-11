package edu.ycp.casino;

import java.util.ArrayList;


public class Blackjack extends Game{
	private int hits;			//count number of hits in game
	private boolean endturn;	
	private int numCard;		//count number of cards in hand
	private boolean stay;		//check for stay
	private int handvalue;		//value of hand
	private ArrayList<Hand> hands;	

	public Blackjack(){
		hits = 0;
		hands = new ArrayList<Hand>();
		for (int i = 0; i < getNumPlayers(); i++){
			handvalue = hands.get(i).getBJHandValue();
		}
	}

	//when player hits or stays
	public void play(){
		for (int i = 0; i < getNumPlayers(); i++){
			while (!endturn || hands.get(i).getNumCards() <= 6 || hits < 6){
				//if black jack first turn
				if (hits == 0){
					hands.get(i).addToHand(hands.get(i).getCard(0));
					hands.get(i).addToHand(hands.get(i).getCard(1));
					numCard = 2;
					//get hand value of first two cards
					handvalue = hands.get(i).getBJHandValue();
				}
				//if hand reaches 21
				if (handvalue == 21){
					endturn = true;
				}
				//when player hits
				if (stay == false){
					hands.get(i).addToHand(hands.get(i).getCard(numCard));
					numCard++;
					hits++;
				}
				//when player stops
				if (stay == true){
					endturn = true;
				}
				//get total hand value
				handvalue = hands.get(i).getBJHandValue();
				//changing the value of ace depending on hand
				if (handvalue<11){
					for(Card card : hands.get(i).getCards()){
						if (card.getRank()==CardValues.ACE){
							handvalue+=10;
							break;
						}
					}
				}
				//if bust or over hand value
				if (handvalue > 21){
					endturn = true;
				}
			}
		}
		for (int i = 0; i < getNumPlayers();i++){
			checkWin(i);
		}
	}

	//check win
	public boolean checkWin(int player){

		//if player gets 21 and only two cards
		if (hands.get(player).getBJHandValue() == 21 && hits == 0){
			return true;
		}
		//if player bust
		if (hands.get(player).getBJHandValue() > 21){
			return false;
		}
		//compare hands if under 21
		else if (hands.get(player).getBJHandValue() < 21){
			for (int j = 0; j < getNumPlayers();j++){
				//if player and j are the same hand
				if (player == j){
					j++;
				}
				//if hand is less than other hand
				else {
					if (hands.get(player).compareBJ(hands.get(j)) == -1){
						return false;
					}
					//if hand greater than other hand
					else if (hands.get(player).compareBJ(hands.get(j)) == 1){
						return true;
					}
					//if handvalues are the same
					else {
						return true;
					}
				}
			}
		}
		//return false if nothing above works
		return false;
	}
}


