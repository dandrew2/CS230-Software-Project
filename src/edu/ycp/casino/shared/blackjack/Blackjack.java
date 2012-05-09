package edu.ycp.casino.shared.blackjack;

import edu.ycp.casino.shared.Game;
import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.cardgame.Dealer;

public class Blackjack extends Game{   
	private Player player;
	private Dealer dealer;
	private Player master;//master is dealer
	private int pot; //money reward
	//private int wallet;
	public Blackjack(){
		player = new Player();
		master = new Player();
		dealer = new Dealer();
	}

	//when player hits or stays
	public void play(){
		//get players bet
		startGame(player);
		//player should have two cards by now
		while (checkStay(false)){
			playerTurn(player);//just deal
		}
		//dealer turn
		dealerTurn(master);
		checkOut(player,master,pot);
		player.addBalance(pot);
	}
	//reset
	public void reset(){
		this.player = new Player();
		this.master = new Player();
		this.dealer = new Dealer();
	}
	//first part of the game to get bet and deal initial hand
	public void startGame(Player p){
		p.getBet();
		if (p.getHand().getNumCards()<2){
			dealer.dealTo(p);
			dealer.dealTo(p);
		}
		else{

		}
	}
	//get hit
	public boolean checkHit(boolean hit){
		return hit;
	}
	//get stay
	public boolean checkStay(boolean stay){
		return stay;
	}

	//get player hand
	public void playerTurn(Player p){
		//hit or stay phrase

		//if hand reaches 21
		if (p.getHand().getBJHandValue() == 21){
			checkStay(true);
		}
		//if bust or over hand value
		else if (p.getHand().getBJHandValue() > 21){
			checkStay(true);
		}
		//when player hits
		else if (checkHit(true)){
			//dealer deal to hitter
			dealer.dealTo(p);
		}
		checkHit(false);//reset the hit
	}

	//dealers hand
	public void dealerHand(Player p){
		dealer.dealTo(p);
		dealer.dealTo(p);
	}
	public void dealerTurn(Player p){
		//dealer stop drawing after reaching 17 or higher
		while (p.getHand().getBJHandValue()<17){
			dealer.dealTo(p);
		}
	}

	//check bust
	public boolean checkBust (Player p){
		if (p.getHand().getBJHandValue()>21){
			return true;
		}
		else {
			return false;
		}
	}
	//check tie (player Hand, dealer Hand)
	public boolean checkTie(Player p,Player d){
		//check if bust
		if (p.getHand().getBJHandValue()>21){
			return false;
		}
		//if player hands are the same
		else if (p.getHand().compareBJ(d.getHand())==0){
			return true;
		}
		return false;

	}

	//check for blackjack
	public boolean checkBJ(Player p){
		// if player gets blackjack on first turn
		if (p.getHand().getBJHandValue() == 21 && p.getHand().getNumCards() == 2){
			return true;
		}
		//if not 
		return false;
	}

	//check win	
	public boolean checkWin(Player p,Player d){ 
		//if player bust
		if (p.getHand().getBJHandValue() > 21){
			return false;
		}
		//if dealer bust
		else if (d.getHand().getBJHandValue() > 21){
			return true;
		}
		//if hand greater than other hand
		else if (p.getHand().compareBJ(d.getHand()) == 1){
			return true;
		}

		//if hand is less than other hand
		return false;
	}

	//check type of win and return bet won or lost
	public int checkOut(Player p,Player d,int bet){
		//if bust
		if (checkBust(p) == true){
			//player.addBalance(-pot);
			//System.out.print("Bust, you lose!");
			return -bet;
		}

		//check for tie 
		else if (checkTie(p,d) == true){
			//player wins nothing and loses nothing
			//player.addBalance(0);
			//System.out.print("Push!");
			return 0;
		}

		//check for blackjack
		else if (checkBJ(p)==true){
			//return player money with 2 x winning
			//pot = 2*pot;
			//player.addBalance(pot);
			//System.out.print("Blackjack, you win!!");
			return 2*bet;
		}

		//check for win
		else if (checkWin(p,d) == true){
			//return player money with 2 x winning
			//player.addBalance(pot);
			//System.out.print("You Win!");
			return bet;
		}
		else if (checkWin(p,d) == false){
			//player loses
			//player.addBalance(-pot);
			//System.out.print("You lose!");
			return -bet;
		}
		return 0;
	}
	//get player
	public Player getPlayer(){
		return player;
	}
	//get dealer as a player
	public Player getDealer(){
		return master;
	}
	
	public void setPlayer(Player p)
	{
		this.player = p;
	}

}
