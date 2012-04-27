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
	
	private ArrayList<Player> makePlayers(int numPlayers){
		ArrayList<Player> players=new ArrayList<Player>();
		for(int pNum=0; pNum<numPlayers; pNum++){
			players.add(new Player(50,pNum));
		}
		return players;
	}
	
	public Table(){
		//Instantiate objects;
		this.comperator = new HandComperator();
		this.pot = new Pot();
		this.community = new Hand();
		this.dealer = new Dealer();
		this.players = makePlayers(5);
	}
	public Table(ArrayList<Player> _players){
		//Instantiate objects;
		this.comperator = new HandComperator();
		this.pot = new Pot();
		this.community = new Hand();
		this.dealer = new Dealer();
		this.players = _players;
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
		players=makePlayers(5);
		takeAnti();
		dealer.deal(players);
		takeBets();
		dealer.dealFlop(this.community);
		takeBets();
		dealer.dealTurn(this.community);
		takeBets();
		dealer.dealRiver(this.community);
		System.out.println("All cards delt.  Community cards: "+this.community.toString()+"\n");
		takeBets();
		getWinner().getWallet().addFunds(this.pot.takeAll());
	}
	
	public Player getWinner(){
		return this.comperator.getWinner(this.community,this.players);
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
