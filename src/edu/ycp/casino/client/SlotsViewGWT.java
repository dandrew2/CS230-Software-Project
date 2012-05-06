package edu.ycp.casino.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.InlineLabel;

import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Observer;
import edu.ycp.casino.shared.Slots;
import edu.ycp.casino.shared.SlotsController;
import edu.ycp.casino.shared.SlotsSymbols;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Image;

public class SlotsViewGWT extends Composite implements Observer, GameViewCallback {
	private Slots model;
	private SlotsController controller;
	private Label labelDisplaySlot1;
	private Label labelDisplaySlot2;
	private Label labelDisplaySlot3;
	private Label lblCurrentMoneyDisplay;
	private TextBox textBoxBetText;
	private Button btnSpin;
	
	private GameViewCallback callback;
	
	private Image imageSlot1;
	private Image imageSlot2;
	private Image imageSlot3;
	private Button btnBackToMain;

	public SlotsViewGWT() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		
		initWidget(layoutPanel);
		layoutPanel.setSize("700px", "500px");
		
		InlineLabel lblSlot1 = new InlineLabel("Slot 1:");
		layoutPanel.add(lblSlot1);
		layoutPanel.setWidgetLeftWidth(lblSlot1, 47.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblSlot1, 115.0, Unit.PX, 18.0, Unit.PX);
		
		btnSpin = new Button("Spin");
		btnSpin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runClick();
			}
		});
		layoutPanel.add(btnSpin);
		layoutPanel.setWidgetLeftWidth(btnSpin, 47.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnSpin, 360.0, Unit.PX, 30.0, Unit.PX);
		
		Label lblSlot2 = new Label("Slots 2:");
		layoutPanel.add(lblSlot2);
		layoutPanel.setWidgetLeftWidth(lblSlot2, 150.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblSlot2, 115.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblSlot3 = new Label("Slot 3:");
		layoutPanel.add(lblSlot3);
		layoutPanel.setWidgetLeftWidth(lblSlot3, 260.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblSlot3, 115.0, Unit.PX, 18.0, Unit.PX);
		
		labelDisplaySlot1 = new Label("");
		layoutPanel.add(labelDisplaySlot1);
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot1, 40.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot1, 224.0, Unit.PX, 18.0, Unit.PX);
		
		labelDisplaySlot2 = new Label("");
		layoutPanel.add(labelDisplaySlot2);
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot2, 137.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot2, 224.0, Unit.PX, 18.0, Unit.PX);
		
		labelDisplaySlot3 = new Label("");
		layoutPanel.add(labelDisplaySlot3);
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot3, 260.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot3, 224.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblCurrentBet = new Label("Current Bet");
		layoutPanel.add(lblCurrentBet);
		layoutPanel.setWidgetLeftWidth(lblCurrentBet, 285.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentBet, 345.0, Unit.PX, 18.0, Unit.PX);
		
		textBoxBetText = new TextBox();
		textBoxBetText.setText("10");
		layoutPanel.add(textBoxBetText);
		layoutPanel.setWidgetLeftWidth(textBoxBetText, 285.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxBetText, 377.0, Unit.PX, 30.0, Unit.PX);
		
		Label lblCurrentMoney = new Label("Current Money");
		layoutPanel.add(lblCurrentMoney);
		layoutPanel.setWidgetLeftWidth(lblCurrentMoney, 285.0, Unit.PX, 97.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentMoney, 297.0, Unit.PX, 18.0, Unit.PX);
		
		lblCurrentMoneyDisplay = new Label("");
		layoutPanel.add(lblCurrentMoneyDisplay);
		layoutPanel.setWidgetLeftWidth(lblCurrentMoneyDisplay, 285.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentMoneyDisplay, 321.0, Unit.PX, 18.0, Unit.PX);
		
		imageSlot1 = new Image();
		layoutPanel.add(imageSlot1);
		imageSlot1.setSize("50", "50");
		layoutPanel.setWidgetLeftWidth(imageSlot1, 40.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot1, 151.0, Unit.PX, 75.0, Unit.PX);
		
		imageSlot2 = new Image();
		layoutPanel.add(imageSlot2);
		layoutPanel.setWidgetLeftWidth(imageSlot2, 148.0, Unit.PX, 50.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot2, 151.0, Unit.PX, 50.0, Unit.PX);
		
		imageSlot3 = new Image();
		layoutPanel.add(imageSlot3);
		layoutPanel.setWidgetLeftWidth(imageSlot3, 260.0, Unit.PX, 61.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot3, 151.0, Unit.PX, 62.0, Unit.PX);
		
		btnBackToMain = new Button("Back to Main Menu");
		btnBackToMain.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runMainMenuClick();
			}
			
		});
		
		layoutPanel.add(btnBackToMain);
		layoutPanel.setWidgetLeftWidth(btnBackToMain, 48.0, Unit.PX, 129.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnBackToMain, 422.0, Unit.PX, 30.0, Unit.PX);
	}
	
	public void setCallback(GameViewCallback callback) {
		this.callback = callback;
	}
	
	
	public void update(Observable obj, Object hint) {


		
		
		
		labelDisplaySlot1.setText(model.getSlot()[0].toString());
		labelDisplaySlot2.setText(model.getSlot()[1].toString());
		labelDisplaySlot3.setText(model.getSlot()[2].toString());
		
		setImage(imageSlot1,model.getSlot()[0]);		
		setImage(imageSlot2,model.getSlot()[1]);
		setImage(imageSlot3,model.getSlot()[2]);	
		
		lblCurrentMoneyDisplay.setText(Integer.toString(model.getPlayer().getWallet().getBalance()));
	}
	
	public void runClick()
	{
		controller.spinHandler();
	}
	
	
	public void setController(SlotsController c)
	{
		this.controller = c;
	}

	public void setModel(Slots model) {
		this.model = model;
		model.addObserver(this);
	}
	
	public void setImage(Image image,SlotsSymbols symbol)
	{
		
		if(symbol == SlotsSymbols.SEVEN)
		{
			image.setUrl("Seven.png");
		}
		
		if(symbol == SlotsSymbols.CHERRY)
		{
			image.setUrl("Cherry.png");
		}
		
		if(symbol == SlotsSymbols.BAR)
		{
			image.setUrl("Bar.png");
		}
		
		if(symbol == SlotsSymbols.PLUM)
		{
			image.setUrl("Plum.png");
		}
		
		if(symbol == SlotsSymbols.ORANGE)
		{
			image.setUrl("Orange.png");
		}
		
		if(symbol == SlotsSymbols.BELL)
		{
			image.setUrl("Bell.png");
		}
		
		if(symbol == SlotsSymbols.GRAPES)
		{
			image.setUrl("Grapes.png");
		}
		
		if(symbol == SlotsSymbols.LIME)
		{
			image.setUrl("Lime.png");
		}
		
		if(symbol == SlotsSymbols.WATERMELON)
		{
			image.setUrl("Watermelon.png");
		}
	}

	
	public void runMainMenuClick()
	{
		if (callback != null) {
			callback.chooseMainMenu();
		}
	}

	@Override
	public void chooseMainMenu() {		
	}
	
	public TextBox getBettextBox()
	{
		return this.textBoxBetText;
	}

}
