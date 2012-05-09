package edu.ycp.casino.client;

import com.google.gwt.user.client.ui.Composite;

import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.TextBox;

import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Observer;
import edu.ycp.casino.shared.blackjack.Blackjack;
import edu.ycp.casino.shared.blackjack.BlackjackController;
import edu.ycp.casino.shared.cardgame.Card;
import edu.ycp.casino.shared.cardgame.Rank;
import edu.ycp.casino.shared.cardgame.Suit;

import com.google.gwt.user.client.ui.Image;

public class BlackjackViewGWT extends Composite implements Observer{
	private Blackjack model;
	private BlackjackController controller;
	//text box
	private TextBox enterBet;
	private TextBox handvalue;
	private TextBox wallet;
	private TextBox dealerValue;
	private TextBox playstats;
	private TextBox rewards;
	//buttons 
	private Button btnStart;
	private Button buttonHit;
	private Button buttonStay;
	//card images 
	private Image image_c1;
	private Image image_c2;
	private Image image_c3;
	private Image image_c4;
	private Image image_c5;
	private Image image_c6;
	private Image image_d1;
	private Image image_d2;
	private Image image_d3;
	private Image image_d4;
	private Image image_d5;

	public BlackjackViewGWT() {

		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		layoutPanel.setSize("800px", "600px");

		Image Background = new Image("feltBg.jpg");
		layoutPanel.add(Background);
		Background.setSize("800", "600");
		layoutPanel.setWidgetLeftWidth(Background, 0.0, Unit.PX, 800.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(Background, 0.0, Unit.PX, 600.0, Unit.PX);

		TextBox txtbxEnterBet = new TextBox();
		txtbxEnterBet.setReadOnly(true);
		txtbxEnterBet.setText("Enter Bet :");
		layoutPanel.add(txtbxEnterBet);
		layoutPanel.setWidgetLeftWidth(txtbxEnterBet, 11.0, Unit.PX, 92.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(txtbxEnterBet, 468.0, Unit.PX, 34.0, Unit.PX);

		//hit button
		buttonHit = new Button("Hit");
		buttonHit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				hit();
			}
		});
		layoutPanel.add(buttonHit);
		buttonHit.setSize("100", "45");
		layoutPanel.setWidgetLeftWidth(buttonHit, 256.0, Unit.PX, 106.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(buttonHit, 520.0, Unit.PX, 46.0, Unit.PX);

		//button stay
		buttonStay = new Button("Stay/Endturn");
		buttonStay.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				stay();
			}
		});
		layoutPanel.add(buttonStay);
		buttonStay.setSize("100", "45");
		layoutPanel.setWidgetLeftWidth(buttonStay, 384.0, Unit.PX, 106.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(buttonStay, 520.0, Unit.PX, 46.0, Unit.PX);

		TextBox txtbxHandValue = new TextBox();
		txtbxHandValue.setReadOnly(true);
		txtbxHandValue.setText("Player Value :");
		layoutPanel.add(txtbxHandValue);
		layoutPanel.setWidgetLeftWidth(txtbxHandValue, 284.0, Unit.PX, 92.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(txtbxHandValue, 468.0, Unit.PX, 34.0, Unit.PX);

		//start button
		btnStart = new Button ("Start");
		btnStart.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				start();
			}
		});
		layoutPanel.add(btnStart);
		btnStart.setSize("120", "80");
		layoutPanel.setWidgetLeftWidth(btnStart, 10.0, Unit.PX, 129.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnStart, 518.0, Unit.PX, 69.0, Unit.PX);

		//
		TextBox txtbxDealerValue = new TextBox();
		txtbxDealerValue.setReadOnly(true);
		txtbxDealerValue.setText("Dealer Value :");
		layoutPanel.add(txtbxDealerValue);
		layoutPanel.setWidgetLeftWidth(txtbxDealerValue, 270.0, Unit.PX, 106.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(txtbxDealerValue, 14.0, Unit.PX, 34.0, Unit.PX);

		//value of dealers hand
		dealerValue = new TextBox();
		dealerValue.setReadOnly(true);
		layoutPanel.add(dealerValue);
		dealerValue.setSize("75", "34");
		layoutPanel.setWidgetLeftWidth(dealerValue, 371.0, Unit.PX, 75.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(dealerValue, 14.0, Unit.PX, 34.0, Unit.PX);

		TextBox txtbxWallet = new TextBox();
		txtbxWallet.setReadOnly(true);
		txtbxWallet.setText("Wallet :");
		layoutPanel.add(txtbxWallet);
		layoutPanel.setWidgetLeftWidth(txtbxWallet, 11.0, Unit.PX, 92.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(txtbxWallet, 428.0, Unit.PX, 34.0, Unit.PX);

		//current money left
		wallet = new TextBox();
		wallet.setReadOnly(true);
		wallet.setText("1000");
		layoutPanel.add(wallet);
		wallet.setSize("75", "35");
		layoutPanel.setWidgetLeftWidth(wallet, 89.0, Unit.PX, 50.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(wallet, 428.0, Unit.PX, 34.0, Unit.PX);

		//input bet box
		setEnterBet(new TextBox());
		getEnterBet().setText("10");
		layoutPanel.add(getEnterBet());
		getEnterBet().setSize("75", "35");
		layoutPanel.setWidgetLeftWidth(getEnterBet(), 89.0, Unit.PX, 50.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(getEnterBet(), 468.0, Unit.PX, 34.0, Unit.PX);

		//value of player hands
		handvalue = new TextBox();
		handvalue.setReadOnly(true);
		layoutPanel.add(handvalue);
		handvalue.setSize("75", "34");
		layoutPanel.setWidgetLeftWidth(handvalue, 369.0, Unit.PX, 50.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(handvalue, 468.0, Unit.PX, 34.0, Unit.PX);

		image_c1 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_c1);

		image_c1.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_c1, 240.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_c1, 366.0, Unit.PX, 120.0, Unit.PX);

		image_c2 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_c2);
		image_c2.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_c2, 290.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_c2, 366.0, Unit.PX, 96.0, Unit.PX);

		image_c3 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_c3);
		image_c3.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_c3, 335.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_c3, 366.0, Unit.PX, 96.0, Unit.PX);

		image_c4 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_c4);
		image_c4.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_c4, 375.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_c4, 366.0, Unit.PX, 96.0, Unit.PX);

		image_c5 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_c5);
		image_c5.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_c5, 410.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_c5, 366.0, Unit.PX, 96.0, Unit.PX);

		image_c6 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_c6);
		image_c6.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_c6, 440.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_c6, 366.0, Unit.PX, 96.0, Unit.PX);

		image_d1 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_d1);
		image_d1.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_d1, 250.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_d1, 54.0, Unit.PX, 96.0, Unit.PX);

		image_d2 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_d2);
		image_d2.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_d2, 300.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_d2, 54.0, Unit.PX, 96.0, Unit.PX);

		image_d3 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_d3);
		image_d3.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_d3, 350.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_d3, 54.0, Unit.PX, 96.0, Unit.PX);

		image_d4 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_d4);
		image_d4.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_d4, 400.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_d4, 54.0, Unit.PX, 96.0, Unit.PX);

		image_d5 = new Image("Deck/black_slot.gif");
		layoutPanel.add(image_d5);
		image_d5.setSize("75", "100");
		layoutPanel.setWidgetLeftWidth(image_d5, 450.0, Unit.PX, 71.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(image_d5, 54.0, Unit.PX, 96.0, Unit.PX);

		playstats = new TextBox();
		playstats.setReadOnly(true);
		playstats.setText("Enter Bet and Play");
		layoutPanel.add(playstats);
		layoutPanel.setWidgetLeftWidth(playstats, 273.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(playstats, 237.0, Unit.PX, 34.0, Unit.PX);

		//show how much wins or losses
		rewards = new TextBox();
		rewards.setReadOnly(true);
		layoutPanel.add(rewards);
		layoutPanel.setWidgetLeftWidth(rewards, 11.0, Unit.PX, 129.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(rewards, 388.0, Unit.PX, 34.0, Unit.PX);
		rewards.setVisible(false);
		
		//inital show of hands
		backImage(image_c1);
		backImage(image_c2);
		backImage(image_d1);
		backImage(image_d2);
		resetImage(image_c3);
		resetImage(image_c4);
		resetImage(image_c5);
		resetImage(image_c6);
		resetImage(image_d3);
		resetImage(image_d4);
		resetImage(image_d5);



	}
	public void setController(BlackjackController controller){
		this.controller = controller;
	}
	//start button and bet
	public void start(){
		controller.reset();
		controller.assignPot(Integer.parseInt(getEnterBet().getText()));
		controller.start();
		
		rewards.setVisible(false);
		//check for tie or blackjack
		if (model.checkBJ(model.getPlayer())==true){
			controller.stay();
			if (model.checkTie(model.getPlayer(), model.getDealer())==true){
				playstats.setText("Push!!too bad");
				setDealerImage();
			}
			else {
				playstats.setText("Blackjack!!");
				rewards.setVisible(true);
				rewards.setText("  You Win :  "+model.checkOut(model.getPlayer(), model.getDealer(), controller.getBet()));
				setDealerImage();
			}
		}
		else{
			playstats.setText("Hit or Stay");
		}
		//reset hand
		resetImage(image_c3);
		resetImage(image_c4);
		resetImage(image_c5);
		resetImage(image_c6);
		resetImage(image_d3);
		resetImage(image_d4);
		resetImage(image_d5);
		//first two card
		setImage(image_c1, model.getPlayer().getHand().getCard(0));
		setImage(image_c2, model.getPlayer().getHand().getCard(1));
		//dealer hand
		setImage(image_d1, model.getDealer().getHand().getCard(0));
		backImage(image_d2);
		//dealer inital hand
		if (model.getDealer().getHand().getCard(0).getRank()==Rank.ACE){
			dealerValue.setText("11?");
		}
		else{
			dealerValue.setText(model.getDealer().getHand().getBJCardValue(model.getDealer().getHand().getCard(0))+"?");
		}
		
	}
	//hit and stay
	public void hit(){
	//if bust
		controller.hit();
		if (model.checkBust(model.getPlayer())==true){
				controller.stay();
				playstats.setText("You Bust!!");
				rewards.setVisible(true);
				rewards.setText("  You lose:  "+model.checkOut(model.getPlayer(), model.getDealer(), controller.getBet()));
				setDealerImage();
				stay();
		}
		
		//images for next few cards
		if (controller.hits()==1){
			image_c3.setVisible(true);
			setImage(image_c3, model.getPlayer().getHand().getCard(2));
		}
		else if (controller.hits()==2){
			image_c4.setVisible(true);
			setImage(image_c4, model.getPlayer().getHand().getCard(3));
		}
		else if (controller.hits()==3){
			image_c5.setVisible(true);
			setImage(image_c5, model.getPlayer().getHand().getCard(4));
		}
		else if (controller.hits()==4){
			image_c6.setVisible(true);
			setImage(image_c6, model.getPlayer().getHand().getCard(5));
		}
	
		handvalue.setText(""+model.getPlayer().getHand().getBJHandValue());
	}
	public void stay(){
		controller.stay();
		rewards.setVisible(true);
		//check tie
		//if bust
		if (model.checkBust(model.getPlayer())==true){
			controller.stay();
			playstats.setText("You Bust!!");
			rewards.setText("  You lose:  "+model.checkOut(model.getPlayer(), model.getDealer(), controller.getBet()));
			setDealerImage();
		}
		else if (model.checkTie(model.getPlayer(), model.getDealer())==true){
			playstats.setText("Push!!");
		}
		//check win or lost
		else if (model.checkWin(model.getPlayer(), model.getDealer())==true){
			playstats.setText("You Win!!");
			rewards.setText("  You win :  "+model.checkOut(model.getPlayer(), model.getDealer(), controller.getBet()));
		}
		else if(model.checkWin(model.getPlayer(), model.getDealer())==false){
			playstats.setText("You Lose!!");
			rewards.setText("  You lose:  "+model.checkOut(model.getPlayer(), model.getDealer(), controller.getBet()));
		}

		//images for the dealers hands
		setDealerImage();
	}

	public void setModel(Blackjack model) {
		// TODO Auto-generated method stub
		this.model = model;
		model.addObserver(this);
	}
	public void setDealerImage(){
		//images for the dealers hands
		for (int i = 0; i < model.getDealer().getHand().getNumCards();i++){
			if (i == 0){
				image_d1.setVisible(true);
				setImage(image_d1, model.getDealer().getHand().getCard(i));
			}
			else if (i == 1){
				image_d2.setVisible(true);
				setImage(image_d2, model.getDealer().getHand().getCard(i));
			}
			else if (i == 2){
				image_d3.setVisible(true);
				setImage(image_d3, model.getDealer().getHand().getCard(i));
			}
			else if (i == 3){
				image_d4.setVisible(true);
				setImage(image_d4, model.getDealer().getHand().getCard(i));
			}
			else if (i == 4){
				image_d5.setVisible(true);
				setImage(image_d5, model.getDealer().getHand().getCard(i));
			}
		}
		dealerValue.setText(""+model.getDealer().getHand().getBJHandValue());
	}
	@Override
	public void update(Observable obj, Object hint) {
		// TODO Auto-generated method stub
		handvalue.setText(""+model.getPlayer().getHand().getBJHandValue());
		//dealerValue.setText(""+model.getDealer().getHand().getBJHandValue());
		//update wallet
		wallet.setText(""+controller.currentWallet(Integer.parseInt(wallet.getText())));
	}
	public void resetImage(Image image){
		image.setVisible(false);
		//image.setUrl("Deck/black_slot.gif");
	}
	//back of card
	public void backImage(Image image){
		image.setVisible(true);
		image.setUrl("Deck/b1fv.gif");
	}
	//images for card
	public void setImage(Image image,Card c){
		String suit = "";
		//suit
		if (c.getSuit() == Suit.CLUBS){
			suit = "Deck/club";
		}
		else if (c.getSuit() == Suit.DIAMONDS){
			suit = "Deck/dimonad";
		}
		else if (c.getSuit() == Suit.HEARTS){
			suit = "Deck/hearts";
		}
		else if (c.getSuit() == Suit.SPADES){
			suit = "Deck/spade";
		}
		//rank
		if (c.getRank() == Rank.ACE){
			image.setUrl(suit+"/01.gif");
		}
		else if (c.getRank() == Rank.TWO){
			image.setUrl(suit+"/02.gif");
		}
		else if (c.getRank() == Rank.THREE){
			image.setUrl(suit+"/03.gif");
		}
		else if (c.getRank() == Rank.FOUR){
			image.setUrl(suit+"/04.gif");
		}
		else if (c.getRank() == Rank.FIVE){
			image.setUrl(suit+"/05.gif");
		}
		else if (c.getRank() == Rank.SIX){
			image.setUrl(suit+"/06.gif");
		}
		else if (c.getRank() == Rank.SEVEN){
			image.setUrl(suit+"/07.gif");
		}
		else if (c.getRank() == Rank.EIGHT){
			image.setUrl(suit+"/08.gif");
		}
		else if (c.getRank() == Rank.NINE){
			image.setUrl(suit+"/09.gif");
		}
		else if (c.getRank() == Rank.TEN){
			image.setUrl(suit+"/10.gif");
		}
		else if (c.getRank() == Rank.JACK){
			image.setUrl(suit+"/11.gif");
		}
		else if (c.getRank() == Rank.QUEEN){
			image.setUrl(suit+"/12.gif");
		}
		else if (c.getRank() == Rank.KING){
			image.setUrl(suit+"/13.gif");
		}
	}
	public TextBox getEnterBet() {
		return enterBet;
	}
	public void setEnterBet(TextBox enterBet) {
		this.enterBet = enterBet;
	}
}
