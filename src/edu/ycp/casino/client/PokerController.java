package edu.ycp.casino.client;

import java.awt.event.MouseEvent;

import edu.ycp.casino.shared.cardgame.poker.Table;

public class PokerController {
	PokerViewGWT pokerView;
	Table table;
	public PokerController(Table _table,PokerViewGWT _pokerView){
		this.pokerView=_pokerView;
		this.table=_table;
	}
	public PokerViewGWT getPokerView() {
		return pokerView;
	}
	public void setPokerView(PokerViewGWT pokerView) {
		this.pokerView = pokerView;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public void bet(){
		//is bet button
		int bet=pokerView.getBetBox().getValue();
		table.getCurrentPlayer().setHoldingBet(bet,table.getPot().getMinBet());
		if(table.getCurrentPlayer().takeHoldingBet()){
			if (table.getPot().add(bet)){
				pokerView.getLblMessage().setText("Player "+table.getCurrentPlayerNum()+" added $"+bet+" to the pot");
				table.iterateCurrentPlayer();
			}
			else
				pokerView.getLblMessage().setText("Invalid bet.");
		}
		else
			pokerView.getLblMessage().setText("Not enough funds.");
		pokerView.reDraw();
	}
	public void check(){
		if (table.getPot().getMinBet()==0){
			table.getPot().add(0);
			table.getCurrentPlayer().setHoldingBet(0,0);
			pokerView.getLblMessage().setText("Player "+table.getCurrentPlayerNum()+" checks");
			table.iterateCurrentPlayer();
			pokerView.reDraw();
		}
		else
			pokerView.getLblMessage().setText("Invalid bet.");
	}
	public void fold(){
		table.getCurrentPlayer().fold();
		pokerView.getLblMessage().setText("Player "+table.getCurrentPlayerNum()+" folds.");
		if(table.nonFoldedPlayers()>0)
			table.iterateCurrentPlayer();
		else
			newGame();
	}
	public void newGame(){
		pokerView.newGame();
		table.newGame();
	}
}
