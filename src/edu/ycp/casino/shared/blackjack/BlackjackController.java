package edu.ycp.casino.shared.blackjack;
import edu.ycp.casino.client.BlackjackViewGWT;

public class BlackjackController {
	private Blackjack model;
	private BlackjackViewGWT view;
	//prevent button abuse
	private boolean allow;//stop abuse on hit
	private boolean endgame;//stop abuse on stay
	//bet
	private int bet;
	private int hits;
	public BlackjackController() {
		allow = false;
		endgame = false;
		hits = 0;
	}
	//bet
	public void assignPot(int bet){

	}

	//wallet
	public int currentWallet(int wallet){
		bet = Integer.parseInt(view.getEnterBet().getText());
		if (endgame == true){
		wallet = wallet + model.checkOut(model.getPlayer(), model.getDealer(), bet);//get the reward good or bad;
		}
		
		return wallet;

	}
	//hand value of player
	public void showPlayerValue(int handvalue){
		handvalue = model.getPlayer().getHand().getBJHandValue();
		view.update(model, null);
	}
	//hand value of dealer
	public void showDealerValue(int handvalue){
		handvalue = model.getDealer().getHand().getBJHandValue();
		view.update(model, null);
	}
	//start the game
	public void start(){
		model.startGame(model.getPlayer());
		view.update(model, null);
		model.checkStay(false);
		model.dealerHand(model.getDealer());
		allow = true;
		endgame = false;
		hits = 0;
	}
	//hit or add cards
	public void hit(){
		if (allow == true && hits < 4){
			model.playerTurn(model.getPlayer());
			model.checkHit(true);
			hits++;
		}
	}
	public int hits(){
		return hits;
	}
	//stay or end turn
	public void stay(){
		endgame = true;
		if (allow == true){
			model.checkStay(true);
			model.dealerTurn(model.getDealer());
			allow = false;
			view.update(model, null);
			endgame = false;
		}
		else{
			//nothing happens
			endgame = false;
		}
	}
	//reset
	public void reset(){
		model.reset();
	}

	public void setModel(Blackjack model)
	{
		this.model = model;
	}
	public void setView(BlackjackViewGWT view)
	{
		this.view = view;
	}
}
