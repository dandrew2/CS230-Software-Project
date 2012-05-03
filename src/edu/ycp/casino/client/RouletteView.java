package edu.ycp.casino.client;

import java.awt.geom.Ellipse2D;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.canvas.dom.client.FillStrokeStyle;
import com.google.gwt.core.client.GWT;
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

import edu.ycp.casino.shared.BetType;
import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Observer;
import edu.ycp.casino.shared.Roulette;
import com.google.gwt.user.client.ui.TextBoxBase;

public class RouletteView extends Composite implements Observer{

	private Canvas canvas;
	private Context2d context;
	private RouletteController controller;
	private Roulette model; 
	private TextBox wheelNum;
	private TextBox betAmount;
	private TextBox walletText;
	private TextBox betNumText;
	private TextBox betTypeText;
	private Button spinWheel;
	private Image image;
	private Animation wheel; 


	public RouletteView() {



		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("817px", "552px");



		betAmount = new TextBox();
		absolutePanel.add(betAmount, 22, 90);
		betAmount.setSize("107px", "18px");

		InlineLabel BetAmountLbl = new InlineLabel("Enter Bet Amount:");
		absolutePanel.add(BetAmountLbl, 22, 57);

		InlineLabel wheelNumLbl = new InlineLabel("The Wheel Landed On:");
		absolutePanel.add(wheelNumLbl, 629, 57);

		wheelNum = new TextBox();
		wheelNum.setReadOnly(true);
		absolutePanel.add(wheelNum, 629, 90);
		wheelNum.setSize("68px", "18px");

		InlineLabel winningsMessage = new InlineLabel("");
		absolutePanel.add(winningsMessage, 246, 162);
		winningsMessage.setSize("213px", "18px");

		spinWheel = new Button("Spin Wheel");
		spinWheel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) { 
				runClick();
			}
		});
		absolutePanel.add(spinWheel, 54, 408);
		spinWheel.setSize("133px", "38px");
		canvas = Canvas.createIfSupported();
		canvas.setSize("279px", "467px");
		absolutePanel.add(canvas, 256, 10);

		InlineLabel betNumberLbl = new InlineLabel("Bet Number Is:");
		absolutePanel.add(betNumberLbl, 49, 259);

		betTypeText = new TextBox();
		betTypeText.setReadOnly(true);
		absolutePanel.add(betTypeText, 143, 310);
		betTypeText.setSize("69px", "16px");

		image = new Image("roulette.png");
		image.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int x = event.getX();
				int y = event.getY(); 

				controller.betTypeHandler(getBetType(x,y));
			}
		});
		absolutePanel.add(image, 256, 10);
		image.setSize("296px", "484px");

		InlineLabel nlnlblBetTypeIs = new InlineLabel("Bet Type Is:");
		absolutePanel.add(nlnlblBetTypeIs, 65, 320);

		betNumText = new TextBox();
		betNumText.setReadOnly(true);
		absolutePanel.add(betNumText, 143, 249);
		betNumText.setSize("69px", "16px");

		InlineLabel congratsLbl = new InlineLabel("");
		absolutePanel.add(congratsLbl, 629, 162);

		InlineLabel walletAmountLbl = new InlineLabel("Amount in Wallet:");
		absolutePanel.add(walletAmountLbl, 22, 138);

		walletText = new TextBox();
		absolutePanel.add(walletText, 22, 166);
		walletText.setSize("103px", "18px");

		context = canvas.getContext2d();


	}

	public void runClick(){
		controller.spinHandler(); 
	}
	public void setModel(Roulette model) {
		this.model = model; 
		model.addObserver(this); 
	}

	public void setController(RouletteController c){
		this.controller = c; 
	}


	public void update(Observable o, Object hint){
		System.out.println("Updated!"); 
		wheelNum.setText("" + controller.getModel().getWheelVal());
		BetType b = controller.getModel().getBetType(); 

		if(b != null){
			betTypeText.setText(b.toString());
		}


	}

	public void drawOnCanvas() {

	}



	public BetType getBetType(int x, int y){
		BetType b = null; 

		if(x > 68 && x < 120){
			if( y > 56 && y < 184){
				b = BetType.FIRST_TWELVE; 
			}

			if(y > 185 && y < 315){
				b = BetType.MIDDLE_TWELVE; 
			}

			if(y > 319 && y < 444){
				b = BetType.LAST_TWELVE; 
			}
		}

		if(x > 12 && x < 66){
			if( y > 56 && y < 121){
				b = BetType.FIRST_HALF;
			}

			if( y > 121 && y < 184){
				b = BetType.EVEN; 
			}

			if(y > 184 && y < 250){
				b = BetType.RED; 
			}

			if(y > 250 && y < 315){
				b = BetType.BLACK; 
			}
			if(y > 315 && y < 382){
				b = BetType.ODD; 
			}
			if(y > 382 && y < 446){
				b = BetType.LAST_HALF; 
			}
		}

		return b; 
	}
}


