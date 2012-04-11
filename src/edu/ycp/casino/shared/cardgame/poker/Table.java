package edu.ycp.casino.shared.cardgame.poker;

import java.util.ArrayList;

import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.cardgame.Dealer;
import edu.ycp.casino.shared.cardgame.Hand;

public class Table {
	
	final int ANTI=5;
	HandComperator comperator;
	ArrayList<Player> players;
	Dealer dealer;
	Hand community;
	Pot pot;
	
	public Table(ArrayList<Player> players){
		//Instantiate objects;
		comperator = new HandComperator();
		pot = new Pot();
		community = new Hand();
		dealer = new Dealer();
		players = new ArrayList<Player>();
		//Set up players
		for (Player player : players)
			this.players.add(player);
	}
	
	public void takeBets(){
		for (Player player : players){
			pot.add(player.getBet());
		}
	}
	
	public void takeAnti(){
		for (Player player : players){
			pot.add(player.getAnti(ANTI));
		}
	}
	
	public void play(){
		dealer.newDeck();
		takeAnti();
		dealer.deal(players);
		takeBets();
		dealer.dealFlop(this.community);
		takeBets();
		dealer.dealTurn(this.community);
		takeBets();
		dealer.dealRiver(this.community);
		takeBets();
		this.comperator.getWinner(this.community,this.players).addBalance(this.pot.takeAll());
	}
	

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Hand getCommunity() {
		return community;
	}

	public void setCommunity(Hand community) {
		this.community = community;
	}

	public void addPlayer(Player player){
		this.players.add(player);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
}
