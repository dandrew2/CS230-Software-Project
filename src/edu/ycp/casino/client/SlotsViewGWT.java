package edu.ycp.casino.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.user.client.ui.Image;

public class SlotsViewGWT extends Composite implements Observer {
	private Slots model;
	private SlotsController controller;
	private Label labelDisplaySlot1;
	private Label labelDisplaySlot2;
	private Label labelDisplaySlot3;
	private Label lblCurrentMoneyDisplay;
	private TextBox textBoxBetText;
	private Button btnSpin;
	private Image imageSlot1;
	private Image imageSlot2;
	private Image imageSlot3;
	
	private GameViewCallabck callback;

	public SlotsViewGWT() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		
		initWidget(layoutPanel);
		layoutPanel.setSize("613px", "462px");
		
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
		layoutPanel.setWidgetLeftWidth(btnSpin, 58.0, Unit.PX, 81.0, Unit.PX);
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
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot1, 35.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot1, 194.0, Unit.PX, 18.0, Unit.PX);
		
		labelDisplaySlot2 = new Label("");
		layoutPanel.add(labelDisplaySlot2);
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot2, 131.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot2, 194.0, Unit.PX, 18.0, Unit.PX);
		
		labelDisplaySlot3 = new Label("");
		layoutPanel.add(labelDisplaySlot3);
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot3, 260.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot3, 194.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblCurrentBet = new Label("Current Bet");
		layoutPanel.add(lblCurrentBet);
		layoutPanel.setWidgetLeftWidth(lblCurrentBet, 285.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentBet, 345.0, Unit.PX, 18.0, Unit.PX);
		
		textBoxBetText = new TextBox();
		layoutPanel.add(textBoxBetText);
		layoutPanel.setWidgetLeftWidth(textBoxBetText, 285.0, Unit.PX, 75.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxBetText, 377.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblCurrentMoney = new Label("Current Money");
		layoutPanel.add(lblCurrentMoney);
		layoutPanel.setWidgetLeftWidth(lblCurrentMoney, 285.0, Unit.PX, 97.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentMoney, 297.0, Unit.PX, 18.0, Unit.PX);
		
		lblCurrentMoneyDisplay = new Label("");
		layoutPanel.add(lblCurrentMoneyDisplay);
		layoutPanel.setWidgetLeftWidth(lblCurrentMoneyDisplay, 285.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentMoneyDisplay, 321.0, Unit.PX, 18.0, Unit.PX);
		
		imageSlot1 = new Image();
		layoutPanel.add(imageSlot1);
		imageSlot1.setSize("50", "50");
		layoutPanel.setWidgetLeftWidth(imageSlot1, 47.0, Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot1, 139.0, Unit.PX, 100.0, Unit.PX);
		
		imageSlot2 = new Image();
		layoutPanel.add(imageSlot2);
		imageSlot2.setSize("50", "50");
		layoutPanel.setWidgetLeftWidth(imageSlot2, 150.0, Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot2, 138.0, Unit.PX, 100.0, Unit.PX);
		
		imageSlot3 = new Image();
		layoutPanel.add(imageSlot3);
		imageSlot3.setSize("50", "50");
		layoutPanel.setWidgetLeftWidth(imageSlot3, 260.0, Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageSlot3, 139.0, Unit.PX, 100.0, Unit.PX);
	}
	
	public void setCallback(GameViewCallabck callback) {
		this.callback = callback;
	}
	
	@Override
	public void update(Observable obj, Object hint) {
		// TODO Auto-generated method stub
		labelDisplaySlot1.setText(model.getSlot()[0].toString());
		labelDisplaySlot2.setText(model.getSlot()[1].toString());
		labelDisplaySlot3.setText(model.getSlot()[2].toString());
		
		//setImage(imageSlot1,model.getSlot()[0]);
		//setImage(imageSlot2,model.getSlot()[1]);
		//setImage(imageSlot3,model.getSlot()[2]);
			
		lblCurrentMoneyDisplay.setText(Integer.toString(model.getMoney()));
	}
	
	public void runClick()
	{
		controller.assignPot(Integer.parseInt(textBoxBetText.getText()));
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
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Seven.png");
		}
		
		if(symbol == SlotsSymbols.CHERRY)
		{
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Cherry.png");
		}
		
		if(symbol == SlotsSymbols.BAR)
		{
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Cherry.png");
		}
		
		if(symbol == SlotsSymbols.PLUM)
		{
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Plum.png");
		}
		
		if(symbol == SlotsSymbols.ORANGE)
		{
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Cherry.png");
		}
		
		if(symbol == SlotsSymbols.BELL)
		{
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Cherry.png");
		}
		
		if(symbol == SlotsSymbols.GRAPES)
		{
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Cherry.png");
		}
		
		if(symbol == SlotsSymbols.LIME)
		{
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Cherry.png");
		}
		
		if(symbol == SlotsSymbols.WATERMELON)
		{
			image.setUrl("WEB-INF/classes/edu/ycp/casino/SlotsImages/Cherry.png");
		}
	}
}
