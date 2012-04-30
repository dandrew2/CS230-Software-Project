package edu.ycp.casino.shared.cardgame.poker;

import java.util.ArrayList;
import java.util.Collections;

import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.cardgame.Card;
import edu.ycp.casino.shared.cardgame.Hand;

public class HandComperator {
	public HandComperator(){}

	public Player getWinner(Hand community,ArrayList<Player> players){
		ArrayList<Hand> playerHands=new ArrayList<Hand>();
		//Look at all players
		for(Player player : players){
			//Get each player's best hand
			playerHands.add(getBestPossible(community,player));
		}
		//find best hand
		Hand bestHand=getBestHand(playerHands);
		//Tell the player what they have
		players.get(bestHand.getOwner()).setHandType(bestHand.parseHandType());
		return players.get(bestHand.getOwner());
	}
	
	private Hand makePossibleHand(ArrayList<Card> community,Player player,int pos1,int pos2){
		//Set up the possible hand.
		Hand hand=new Hand(community,player.getSeatNum());
		//Inject the player's cards at their position. (If -1, the card isn't used.)
		if(pos1>=0){
			hand.getCards().remove(pos1);
			hand.getCards().add(pos1,player.getHand().getCard(0));
		}
		if(pos2>=0){
			hand.getCards().remove(pos2);
			hand.getCards().add(pos2,player.getHand().getCard(1));
		}
		return hand;
	}
	
	//Find all combinations of the community cards and the player's hand.
	private Hand getBestPossible(Hand community,Player player){
		//This will be replaced with better possibilities and returned at the end.
		Hand bestHand = new Hand(community.getCards());
		//Try all possible positions of the first card in player's hand.
		for(int pos1=-1; pos1<community.getNumCards(); pos1++){
			//try all possible positions of the second card in player's hand.
			for(int pos2=-1; pos2<community.getNumCards(); pos2++){
				Hand hand=makePossibleHand(community.getCards(),player,pos1,pos2);
				//See if this possibility is better than the previous best.
				//System.out.println("---Player "+player.getSeatNum()+" has possible "+hand.parseHandType()+": "+hand.toString());
				if(bestHand.compareTo(hand)<0)
					bestHand=hand;
			}
		}
		return bestHand;
	}
	
	//Get the best hand out of a list
	private Hand getBestHand(ArrayList<Hand> hands){
		return Collections.max(hands);
	}
}