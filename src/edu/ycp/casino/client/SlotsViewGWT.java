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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Image;

public class SlotsViewGWT extends Composite implements Observer, GameViewCallback {
	private Slots model;
	private SlotsController controller;
	
	private Label lblCurrentMoneyDisplay;
	
	private DialogBox bustedBox;
	
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
		
		Image imageBackground = new Image("SlotFace.jpg");
		layoutPanel.add(imageBackground);
		layoutPanel.setWidgetLeftWidth(imageBackground, -24.0, Unit.PX, 724.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageBackground, 0.0, Unit.PX, 500.0, Unit.PX);
		
		bustedBox = new DialogBox();
		layoutPanel.add(bustedBox);
		layoutPanel.setWidgetLeftWidth(bustedBox, 19.0, Unit.PX, 157.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(bustedBox, 39.0, Unit.PX, 65.0, Unit.PX);
		bustedBox.setVisible(false);
		bustedBox.setText("You have gone broke.");
		
		btnSpin = new Button("Spin");
		btnSpin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runClick();
			}
		});
		layoutPanel.add(btnSpin);
		layoutPanel.setWidgetLeftWidth(btnSpin, 163.0, Unit.PX, 129.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnSpin, 340.0, Unit.PX, 30.0, Unit.PX);
		
		Label lblCurrentBet = new Label("Current Bet");
		layoutPanel.add(lblCurrentBet);
		layoutPanel.setWidgetLeftWidth(lblCurrentBet, 387.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentBet, 352.0, Unit.PX, 18.0, Unit.PX);
		
		textBoxBetText = new TextBox();
		textBoxBetText.setText("10");
		layoutPanel.add(textBoxBetText);
		layoutPanel.setWidgetLeftWidth(textBoxBetText, 387.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxBetText, 381.0, Unit.PX, 30.0, Unit.PX);
		
		Label lblCurrentMoney = new Label("Current Money");
		layoutPanel.add(lblCurrentMoney);
		layoutPanel.setWidgetLeftWidth(lblCurrentMoney, 225.0, Unit.PX, 97.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentMoney, 86.0, Unit.PX, 18.0, Unit.PX);
		
		lblCurrentMoneyDisplay = new Label("");
		layoutPanel.add(lblCurrentMoneyDisplay);
		layoutPanel.setWidgetLeftWidth(lblCurrentMoneyDisplay, 334.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentMoneyDisplay, 86.0, Unit.PX, 18.0, Unit.PX);
		
		imageSlot1 = new Image();
		imageSlot1.setUrl("Orange.png");
		layoutPanel.add(imageSlot1);
		imageSlot1.setSize("50", "50");
		layoutPanel.setWidgetLeftWidth(imageSlot1, 182.0, Unit.PX, 66.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot1, 204.0, Unit.PX, 68.0, Unit.PX);
		
		imageSlot2 = new Image();
		imageSlot2.setUrl("Grapes.png");
		layoutPanel.add(imageSlot2);
		layoutPanel.setWidgetLeftWidth(imageSlot2, 291.0, Unit.PX, 68.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot2, 204.0, Unit.PX, 78.0, Unit.PX);
		
		imageSlot3 = new Image();
		imageSlot3.setUrl("Cherry.png");
		layoutPanel.add(imageSlot3);
		layoutPanel.setWidgetLeftWidth(imageSlot3, 406.0, Unit.PX, 62.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot3, 204.0, Unit.PX, 50.0, Unit.PX);
		
		btnBackToMain = new Button("Back to Main Menu");
		btnBackToMain.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runMainMenuClick();
			}
			
		});
		
		layoutPanel.add(btnBackToMain);
		layoutPanel.setWidgetLeftWidth(btnBackToMain, 163.0, Unit.PX, 129.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnBackToMain, 392.0, Unit.PX, 30.0, Unit.PX);
		
		
	}
	
	public void setCallback(GameViewCallback callback) {
		this.callback = callback;
	}
	
	
	public void update(Observable obj, Object hint) {	
		
		setImage(imageSlot1,model.getSlot()[0]);
		setImage(imageSlot2,model.getSlot()[1]);
		setImage(imageSlot3,model.getSlot()[2]);	
		
		if(model.getPlayer().getWallet().getBalance()<=0 )
		{
			bustedBox.setVisible(true);
			bustedBox.center();
		}
		
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
			System.out.print("Run main menu click");
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
