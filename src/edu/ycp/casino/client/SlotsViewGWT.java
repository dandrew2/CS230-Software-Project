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

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;

public class SlotsViewGWT extends Composite implements Observer {
	private Slots model;
	private SlotsController controller;
	private Label labelDisplaySlot1;
	private Label labelDisplaySlot2;
	private Label labelDisplaySlot3;
	private Label lblCurrentMoneyDisplay;
	private TextBox textBoxBetText;
	private Button btnSpin;

	public SlotsViewGWT() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		
		initWidget(layoutPanel);
		
		InlineLabel lblSlot1 = new InlineLabel("Slot 1:");
		layoutPanel.add(lblSlot1);
		layoutPanel.setWidgetLeftWidth(lblSlot1, 58.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblSlot1, 77.0, Unit.PX, 18.0, Unit.PX);
		
		btnSpin = new Button("Spin");
		btnSpin.addDoubleClickHandler(new DoubleClickHandler() {
			public void onDoubleClick(DoubleClickEvent event) {
			}
		});
		btnSpin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			}
		});
		layoutPanel.add(btnSpin);
		layoutPanel.setWidgetLeftWidth(btnSpin, 58.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(btnSpin, 227.0, Unit.PX, 30.0, Unit.PX);
		
		Label lblSlot2 = new Label("Slots 2:");
		layoutPanel.add(lblSlot2);
		layoutPanel.setWidgetLeftWidth(lblSlot2, 174.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblSlot2, 77.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblSlot3 = new Label("Slot 3:");
		layoutPanel.add(lblSlot3);
		layoutPanel.setWidgetLeftWidth(lblSlot3, 270.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblSlot3, 77.0, Unit.PX, 18.0, Unit.PX);
		
		labelDisplaySlot1 = new Label("");
		layoutPanel.add(labelDisplaySlot1);
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot1, 58.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot1, 105.0, Unit.PX, 18.0, Unit.PX);
		
		labelDisplaySlot2 = new Label("");
		layoutPanel.add(labelDisplaySlot2);
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot2, 174.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot2, 105.0, Unit.PX, 18.0, Unit.PX);
		
		labelDisplaySlot3 = new Label("");
		layoutPanel.add(labelDisplaySlot3);
		layoutPanel.setWidgetLeftWidth(labelDisplaySlot3, 270.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(labelDisplaySlot3, 105.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblCurrentBet = new Label("Current Bet");
		layoutPanel.add(lblCurrentBet);
		layoutPanel.setWidgetLeftWidth(lblCurrentBet, 304.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentBet, 215.0, Unit.PX, 18.0, Unit.PX);
		
		textBoxBetText = new TextBox();
		layoutPanel.add(textBoxBetText);
		layoutPanel.setWidgetLeftWidth(textBoxBetText, 304.0, Unit.PX, 75.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textBoxBetText, 239.0, Unit.PX, 18.0, Unit.PX);
		
		Label lblCurrentMoney = new Label("Current Money");
		layoutPanel.add(lblCurrentMoney);
		layoutPanel.setWidgetLeftWidth(lblCurrentMoney, 304.0, Unit.PX, 97.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentMoney, 155.0, Unit.PX, 18.0, Unit.PX);
		
		lblCurrentMoneyDisplay = new Label("");
		layoutPanel.add(lblCurrentMoneyDisplay);
		layoutPanel.setWidgetLeftWidth(lblCurrentMoneyDisplay, 304.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblCurrentMoneyDisplay, 179.0, Unit.PX, 18.0, Unit.PX);
	}
	
	@Override
	public void update(Observable obj, Object hint) {
		// TODO Auto-generated method stub
		labelDisplaySlot1.setText(model.getSlot()[0].toString());
		labelDisplaySlot2.setText(model.getSlot()[1].toString());
		labelDisplaySlot3.setText(model.getSlot()[2].toString());
		
		lblCurrentMoneyDisplay.setText(Integer.toString(model.getMoney()));
		System.out.println("Phase 3");
	}
	
	public void onClick(ClickEvent event)
	{
		System.out.println("Phase 1");
		controller.assignPot(Integer.parseInt(textBoxBetText.getText()));
		controller.spinHandler();
	}
	
	public void onDoubleClick(DoubleClickEvent event)
	{
		System.out.println("Phase 1");
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
}
