package edu.ycp.casino.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.canvas.dom.client.FillStrokeStyle;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;

public class RouletteView extends Composite{
	
	private Canvas canvas;
	private Context2d context;
	
	private GameViewCallback callback;
	
	public RouletteView() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("603px", "441px");
		
		Button spinWheel = new Button("Spin Wheel");
		absolutePanel.add(spinWheel, 498, 376);
		
		TextBox betAmount = new TextBox();
		absolutePanel.add(betAmount, 368, 376);
		betAmount.setSize("107px", "18px");
		
		InlineLabel BetAmountLbl = new InlineLabel("Enter Bet Amount:");
		absolutePanel.add(BetAmountLbl, 254, 388);
		
		InlineLabel wheelNumLbl = new InlineLabel("The Wheel Landed On:");
		absolutePanel.add(wheelNumLbl, 37, 162);
		
		TextBox wheelNum = new TextBox();
		wheelNum.setReadOnly(true);
		absolutePanel.add(wheelNum, 196, 162);
		wheelNum.setSize("68px", "18px");
		
		InlineLabel winningsMessage = new InlineLabel("");
		absolutePanel.add(winningsMessage, 246, 162);
		winningsMessage.setSize("213px", "18px");
		
		ListBox betType = new ListBox();
		betType.addItem("Red");
		betType.addItem("Black");
		betType.addItem("Number");
		betType.addItem("First 12");
		betType.addItem("Middle 12");
		betType.addItem("Last 12");
		absolutePanel.add(betType, 41, 99);
		betType.setSize("129px", "22px");
		
		InlineLabel BetTypeLbl = new InlineLabel("Bet Type");
		absolutePanel.add(BetTypeLbl, 41, 75);
		
		canvas = Canvas.createIfSupported();
		canvas.setWidth("200px");
		canvas.setHeight("400px");
		absolutePanel.add(canvas, 300, 10);
		
		context = canvas.getContext2d();
	}
	
	public void setCallback(GameViewCallback callback) {
		this.callback = callback;
	}
	
	public void drawOnCanvas() {
		CssColor green = CssColor.make("#0F0");
		context.setFillStyle(green);
		context.fillRect(10, 10, 60, 60);
	}
}
