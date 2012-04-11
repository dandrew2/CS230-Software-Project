package edu.ycp.casino.shared.cardgame.poker;

import java.util.ArrayList;
import java.util.Collections;

import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.cardgame.Card;
import edu.ycp.casino.shared.cardgame.Hand;

public class HandComperator {
	public HandComperator(){
		
	}
	public Player getWinner(Hand community,ArrayList<Player> players){
		ArrayList<Hand> playerHands=new ArrayList<Hand>();
		//Look at all players
		for(Player player : players){
			//Get each player's best hand
			playerHands.add(getBestPossible(community,player));
		}
		//TODO:  Figure out how to return a player instead of a hand
		return getBestHand(playerHands);
	}
	private ArrayList<Hand> getAllPossible(Hand community,Player player){
		//Be ready to look at all hand possibilities
		ArrayList<Hand> allHands = new ArrayList<Hand>();
		//Look at each card in the player's hand
		for(Card playerCard : player.getHand().getCards()){
			//Look at every card in the community
			for(int posNum=0; posNum<community.getCards().size(); posNum++){
				//Set up the possible hand
				Hand hand=new Hand();
				hand.getCards().addAll(community.getCards());
				//Inject the player's hand at the position.
				hand.getCards().add(posNum,playerCard);
				allHands.add(hand);
			}
		}
		return allHands;
	}
	private Hand getBestPossible(Hand community,Player player){
		ArrayList<Hand> allPossible = getAllPossible(community,player);
		Collections.sort(allPossible);
		return allPossible.get(0);
	}
	private Hand getBestHand(ArrayList<Hand> hands){
		Hand bestHand=Collections.max(hands);
	}
	private
}