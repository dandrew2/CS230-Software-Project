package edu.ycp.casino.client;

import java.awt.geom.Ellipse2D;

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
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.NumberLabel;

import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Roulette;

public class RouletteView extends Composite{
	
	private Canvas canvas;
	private Context2d context;
	private RouletteController controller;
	private Roulette model; 
	
	public RouletteView() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("817px", "552px");
		
		
		
		TextBox betAmount = new TextBox();
		absolutePanel.add(betAmount, 54, 90);
		betAmount.setSize("107px", "18px");
		
		InlineLabel BetAmountLbl = new InlineLabel("Enter Bet Amount:");
		absolutePanel.add(BetAmountLbl, 54, 57);
		
		InlineLabel wheelNumLbl = new InlineLabel("The Wheel Landed On:");
		absolutePanel.add(wheelNumLbl, 629, 57);
		
		TextBox wheelNum = new TextBox();
		wheelNum.setReadOnly(true);
		absolutePanel.add(wheelNum, 629, 90);
		wheelNum.setSize("68px", "18px");
		
		InlineLabel winningsMessage = new InlineLabel("");
		absolutePanel.add(winningsMessage, 246, 162);
		winningsMessage.setSize("213px", "18px");
		
		Button spinWheel = new Button("Spin Wheel");
		spinWheel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				controller.setModel(model); 
				controller.spinHandler(); 
				//wheelNum.
			}
		});
		absolutePanel.add(spinWheel, 54, 283);
		canvas = Canvas.createIfSupported();
		canvas.setSize("279px", "467px");
		absolutePanel.add(canvas, 256, 10);
		
		InlineLabel betNumberLbl = new InlineLabel("Bet Number Is:");
		absolutePanel.add(betNumberLbl, 50, 162);
		
		TextBox betTypeText = new TextBox();
		betTypeText.setReadOnly(true);
		absolutePanel.add(betTypeText, 143, 195);
		betTypeText.setSize("69px", "16px");
		
		Image image = new Image("roulette.png");
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int x = event.getX();
				int y = event.getY(); 
			}
		});
		absolutePanel.add(image, 256, 10);
		image.setSize("296px", "484px");
		
		InlineLabel nlnlblBetTypeIs = new InlineLabel("Bet Type Is:");
		absolutePanel.add(nlnlblBetTypeIs, 67, 207);
		
		TextBox betNumText = new TextBox();
		betNumText.setReadOnly(true);
		absolutePanel.add(betNumText, 143, 151);
		betNumText.setSize("69px", "16px");
		
		InlineLabel congratsLbl = new InlineLabel("");
		absolutePanel.add(congratsLbl, 629, 162);
		
		context = canvas.getContext2d();
	}
	
	public void update(Observable o, Object hint){
		
	}
	
	public void drawOnCanvas() {
		CssColor red = CssColor.make("#FF0000");
		CssColor black = CssColor.make("#000000");
		CssColor green = CssColor.make("#0F0"); 
		CssColor white = CssColor.make("#FFFFFF"); 
	
		
		/*context.fillRect(0, 0, 250, 25); 
		
		context.setFillStyle(black); 
		context.fillRect(0, 35, 250, 25); 
		context.setFillStyle(green);
				context.fill(); 
				context.arc((x+62)/2, (y+10)/2, 62/10, Math.PI/2, Math.PI/2);
		context.setFillStyle(green); 
		context.fillRect(0, 70, 250, 25); */
		int count = 0;
		int temp = 0; 
		int x = 0; 
		int y = 0; 
		for(int i = 0; i < 12; i ++){
			for(int j = 0; j < 3; j++){
				context.setFillStyle(green); 
				context.fillRect(x, y, 62, 10);
				
				if(count == 0){
					drawEllipse(context, x, y, 62, 10, red);
					count++; 
				}
				else{
					drawEllipse(context, x, y, 62, 10, black);
					count = 0; 
				}
				
			}
			y = y + 10;
			x = 0;
		}
		
		for(int i = 1; i < 37; i++){
			
		}
	}
	
	public void drawEllipse(Context2d ctx, int x, int y, int w, int h, CssColor fill) {
		  double kappa = 0.5522848;
		      double xStart = (w / 2) * kappa; 
		      double yStart = (h / 2) * kappa; 
		      double xEnd = x + w;           
		      double yEnd = y + h;           
		      double xMiddle = x + w / 2;       
		      double yMiddle = y + h / 2;      
		  
		  ctx.setFillStyle(fill);
		  ctx.fill(); 
		  ctx.beginPath();		
		  ctx.moveTo(x, yMiddle);
		  ctx.bezierCurveTo(x, yMiddle - yStart, xMiddle - xStart, y, xMiddle, y);
		  ctx.bezierCurveTo(xMiddle + xStart, y, xEnd, yMiddle - yStart, xEnd, yMiddle);
		  ctx.bezierCurveTo(xEnd, yMiddle + yStart, xMiddle + xStart, yEnd, xMiddle, yEnd);
		  ctx.bezierCurveTo(xMiddle - xStart, yEnd, x, yMiddle + yStart, x, yMiddle);
		  ctx.closePath();
		  ctx.stroke();
		}
}
