package edu.ycp.casino.client;



import edu.ycp.casino.shared.BetType;
import edu.ycp.casino.shared.Roulette;

public class RouletteController {
	private Roulette model; 
	private RouletteView view;

	
	public RouletteController(){
		
	}
	
	public void setModel(Roulette model){
		this.model = model; 
	}
	
	public void setView(RouletteView view){
		this.view = view; 
	}
	
	public void spinHandler(){
		
		model.spinWheel();
		int bet = model.getPot();
		 
		if(model.checkWin() == true){
			model.getPlayer().addBalance(model.getPayout(bet)); 
			
		}
		view.update(model, null); 
		model.clearPot(); 
		bet = 0; 
	}
	
	public void addMoney(int m){
		model.getPlayer().addBalance(m); 
		view.update(model, null); 
	}
	public void betTypeHandler(BetType b){
		model.setBetType(b);
		view.update(model, null); 
	}
	public void placeBetHandler(int amt){
		model.placeBet(amt); 
	}
	
	public int getWheelVal(){
		return model.getWheelVal(); 
	}
	
	public Roulette getModel(){
		return model; 
	}
	
	public void setBetVal(int val){
		model.setBetVal(val); 
		view.update(model, null); 
	}
}
