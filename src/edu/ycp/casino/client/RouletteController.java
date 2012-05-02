package edu.ycp.casino.client;

import edu.ycp.casino.shared.BetType;
import edu.ycp.casino.shared.Roulette;

public class RouletteController {
	private Roulette model; 
	private RouletteView view; 
	
	public RouletteController(){
		this.model = new Roulette(); 
		this.view = new RouletteView(); 
	}
	
	public void setModel(Roulette model){
		this.model = model; 
	}
	
	public void setView(RouletteView view){
		this.view = view; 
	}
	
	public int spinHandler(){
		int val = model.spinWheel(); 
		return val; 
	}
	
	public void placeBetHandler(int amt, BetType b){
		model.placeBet(amt, b); 
	}
}
