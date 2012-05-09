package edu.ycp.casino.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;

import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Observer;
import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.cardgame.Card;
import edu.ycp.casino.shared.cardgame.poker.Table;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.NumberLabel;

public class PokerViewGWT extends Composite implements Observer{

	private boolean allIn;
	private boolean gameOverShown;
	private boolean showNewGameAlert;
	private Table table;
	private Label lblMessage;
	private com.google.gwt.user.client.ui.Image com1;
	private com.google.gwt.user.client.ui.Image com2;
	private com.google.gwt.user.client.ui.Image com3;
	private com.google.gwt.user.client.ui.Image com4;
	private com.google.gwt.user.client.ui.Image com5;
	private com.google.gwt.user.client.ui.Image hand1;
	private com.google.gwt.user.client.ui.Image hand2;
	private NumberLabel<Integer> lblMin;
	private NumberLabel<Integer> lblMax;
	private IntegerBox betBox;
	private PokerController controller;
	private GameViewCallback callback;
	
	// TODO: add fields to store state
	
	// constructor
	public PokerViewGWT(Table _table) {
		this.table = _table;
		LayoutPanel layout=new LayoutPanel();
		initWidget(layout);
		layout.setSize("489px", "461px");
		
		ClickHandler betHandler=new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//is bet button
				controller.bet();
			}
		};
		ClickHandler checkHandler=new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//is check button
				controller.check();
			}
		};
		ClickHandler foldHandler=new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//is fold button
				controller.fold();
			}
		};
		
		Button btnBet = new Button("Bet");
		btnBet.addClickHandler(betHandler);
		layout.add(btnBet);
		layout.setWidgetLeftWidth(btnBet, 144.0, Unit.PX, 81.0, Unit.PX);
		layout.setWidgetTopHeight(btnBet, 226.0, Unit.PX, 30.0, Unit.PX);
		
		Button btnCheck = new Button("Check");
		btnCheck.addClickHandler(checkHandler);
		layout.add(btnCheck);
		layout.setWidgetLeftWidth(btnCheck, 144.0, Unit.PX, 81.0, Unit.PX);
		layout.setWidgetTopHeight(btnCheck, 190.0, Unit.PX, 30.0, Unit.PX);
		
		Button btnFold = new Button("Fold");
		btnFold.addClickHandler(foldHandler);
		layout.add(btnFold);
		layout.setWidgetLeftWidth(btnFold, 57.0, Unit.PX, 81.0, Unit.PX);
		layout.setWidgetTopHeight(btnFold, 226.0, Unit.PX, 30.0, Unit.PX);
		
		com1 = new com.google.gwt.user.client.ui.Image("cards/back.png");
		layout.add(com1);
		layout.setWidgetLeftWidth(com1, 0.0, Unit.PX, 126.0, Unit.PX);
		layout.setWidgetTopHeight(com1, 58.0, Unit.PX, 156.0, Unit.PX);
		
		Label lblBetAmount = new Label("Bet Amount");
		lblBetAmount.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layout.add(lblBetAmount);
		layout.setWidgetLeftWidth(lblBetAmount, 0.0, Unit.PX, 138.0, Unit.PX);
		layout.setWidgetTopHeight(lblBetAmount, 170.0, Unit.PX, 18.0, Unit.PX);
		
		com2 = new com.google.gwt.user.client.ui.Image("cards/back.png");
		layout.add(com2);
		layout.setWidgetLeftWidth(com2, 77.0, Unit.PX, 71.0, Unit.PX);
		layout.setWidgetTopHeight(com2, 58.0, Unit.PX, 96.0, Unit.PX);
		
		com3 = new com.google.gwt.user.client.ui.Image("cards/back.png");
		layout.add(com3);
		layout.setWidgetLeftWidth(com3, 154.0, Unit.PX, 71.0, Unit.PX);
		layout.setWidgetTopHeight(com3, 58.0, Unit.PX, 96.0, Unit.PX);
		
		com4 = new com.google.gwt.user.client.ui.Image("cards/back.png");
		layout.add(com4);
		layout.setWidgetLeftWidth(com4, 231.0, Unit.PX, 71.0, Unit.PX);
		layout.setWidgetTopHeight(com4, 58.0, Unit.PX, 96.0, Unit.PX);
		
		hand1 = new com.google.gwt.user.client.ui.Image("cards/"+table.getCurrentPlayer().getHand().getCard(1).toImageFileName()+".png");
		layout.add(hand1);
		layout.setWidgetLeftWidth(hand1, 231.0, Unit.PX, 71.0, Unit.PX);
		layout.setWidgetTopHeight(hand1, 160.0, Unit.PX, 96.0, Unit.PX);
		
		hand2 = new com.google.gwt.user.client.ui.Image("cards/"+table.getCurrentPlayer().getHand().getCard(1).toImageFileName()+".png");
		layout.add(hand2);
		layout.setWidgetLeftWidth(hand2, 308.0, Unit.PX, 71.0, Unit.PX);
		layout.setWidgetTopHeight(hand2, 160.0, Unit.PX, 96.0, Unit.PX);
		
		com5 = new com.google.gwt.user.client.ui.Image("cards/back.png");
		layout.add(com5);
		layout.setWidgetLeftWidth(com5, 308.0, Unit.PX, 71.0, Unit.PX);
		layout.setWidgetTopHeight(com5, 58.0, Unit.PX, 96.0, Unit.PX);
		
		Label label = new Label("$");
		label.setWordWrap(false);
		layout.add(label);
		layout.setWidgetLeftWidth(label, 0.0, Unit.PX, 10.0, Unit.PX);
		layout.setWidgetTopHeight(label, 194.0, Unit.PX, 26.0, Unit.PX);
		
		lblMessage = new Label("Welcome to Poker!");
		lblMessage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		layout.add(lblMessage);
		layout.setWidgetLeftWidth(lblMessage, 0.0, Unit.PX, 379.0, Unit.PX);
		layout.setWidgetTopHeight(lblMessage, 0.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblMinimumBet = new Label("Minimum Bet:");
		layout.add(lblMinimumBet);
		layout.setWidgetLeftWidth(lblMinimumBet, 231.0, Unit.PX, 88.0, Unit.PX);
		layout.setWidgetTopHeight(lblMinimumBet, 18.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblMaximumBet = new Label("Maximum Bet:");
		layout.add(lblMaximumBet);
		layout.setWidgetLeftWidth(lblMaximumBet, 231.0, Unit.PX, 88.0, Unit.PX);
		layout.setWidgetTopHeight(lblMaximumBet, 34.0, Unit.PX, 18.0, Unit.PX);
		
		lblMin = new NumberLabel<Integer>();
		layout.add(lblMin);
		layout.setWidgetLeftWidth(lblMin, 325.0, Unit.PX, 62.0, Unit.PX);
		layout.setWidgetTopHeight(lblMin, 18.0, Unit.PX, 18.0, Unit.PX);
		
		lblMax = new NumberLabel<Integer>();
		layout.add(lblMax);
		layout.setWidgetLeftWidth(lblMax, 325.0, Unit.PX, 62.0, Unit.PX);
		layout.setWidgetTopHeight(lblMax, 34.0, Unit.PX, 18.0, Unit.PX);
		
		betBox = new IntegerBox();
		layout.add(betBox);
		layout.setWidgetLeftWidth(betBox, 10.0, Unit.PX, 128.0, Unit.PX);
		layout.setWidgetTopHeight(betBox, 194.0, Unit.PX, 26.0, Unit.PX);
		
//		com.google.gwt.user.client.ui.Image image_7 = new com.google.gwt.user.client.ui.Image((String) null);
//		layout.add(image_7);
//		layout.setWidgetLeftWidth(image_7, 122.0, Unit.PX, 100.0, Unit.PX);
//		layout.setWidgetTopHeight(image_7, 318.0, Unit.PX, 100.0, Unit.PX);
		
	}
	
	
	
	public void newGame(){
		allIn=false;
		gameOverShown=false;
		lblMessage.setText("Welcome to Poker!");
		clearBet();
		showNewGameAlert=true;
	}
	private void clearBet(){
		betBox.setValue(0);
	}
	private void drawCommunity(){
		drawCard(table.getCommunity().getCard(0), com1);
		drawCard(table.getCommunity().getCard(1), com2);
		drawCard(table.getCommunity().getCard(2), com3);
		drawCard(table.getCommunity().getCard(3), com4);
		drawCard(table.getCommunity().getCard(4), com5);
	}
	private void drawPlayerHand(){
		drawCard(table.getCurrentPlayer().getHand().getCard(0), hand1);
		drawCard(table.getCurrentPlayer().getHand().getCard(1), hand2);
	}
	private void drawCard(Card card,com.google.gwt.user.client.ui.Image img){
		img.setUrl("cards/"+card.toImageFileName()+".png");
	}
	public void reDraw(){
		clearBet();
		drawPlayerHand();
		drawCommunity();
	}
	
	
	
	
	
	@Override
	public void update(Observable obj, Object hint) {
		// TODO Auto-generated method stub
		
	}
	public PokerController getController() {
		return controller;
	}
	public void setController(PokerController controller) {
		this.controller = controller;
	}
	public void setCallback(GameViewCallback callback) {
		this.callback = callback;
	}
	public Label getLblMessage() {
		return lblMessage;
	}
	public void setLblMessage(Label lblMessage) {
		this.lblMessage = lblMessage;
	}
	public NumberLabel<Integer> getLblMin() {
		return lblMin;
	}
	public void setLblMin(NumberLabel<Integer> lblMin) {
		this.lblMin = lblMin;
	}
	public NumberLabel<Integer> getLblMax() {
		return lblMax;
	}
	public void setLblMax(NumberLabel<Integer> lblMax) {
		this.lblMax = lblMax;
	}
	public IntegerBox getBetBox() {
		return betBox;
	}
	
}