package edu.ycp.casino.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import edu.ycp.casino.shared.BetType;
import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Observer;
import edu.ycp.casino.shared.Roulette;


public class RouletteView extends Composite implements Observer, GameViewCallback{

	private RouletteController controller;
	private Roulette model; 
	private TextBox wheelNum;
	private TextBox betAmount;
	private TextBox walletText;
	private TextBox betNumText;
	private TextBox betTypeText;
	private Button spinWheel;
	private Button ok; 
	private Image board;
	private DialogBox db = new DialogBox();
	private Image chip;
	private AbsolutePanel absolutePanel;
	private Button btnBackToMain;
	
	private GameViewCallback callback;

	public RouletteView() {
		
		//Initialize Absolute Panel
		absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("817px", "552px");
		
		//Set up background image
		Image bg = new Image("feltBg.jpg");
		absolutePanel.add(bg, 0, 0);
		bg.setSize("817px", "552px");

		//Initialize text box to enter bet amount
		betAmount = new TextBox();
		absolutePanel.add(betAmount, 22, 90);
		betAmount.setSize("107px", "18px");
		
		//Initialize betamount label
		InlineLabel BetAmountLbl = new InlineLabel("Enter Bet Amount:");
		absolutePanel.add(BetAmountLbl, 22, 57);
		
		//Initialize wheelNum label
		InlineLabel wheelNumLbl = new InlineLabel("The Wheel Landed On:");
		absolutePanel.add(wheelNumLbl, 629, 57);
		
		//Initialize the wheel Num text box (where number is actually displayed)
		wheelNum = new TextBox();
		wheelNum.setReadOnly(true);
		absolutePanel.add(wheelNum, 629, 90);
		wheelNum.setSize("68px", "18px");
		
		//Initialize spin wheel button. When clicked, the event handler calls the run click method. 
		spinWheel = new Button("Spin Wheel");
		spinWheel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) { 
				runClick();
			}
		});
		absolutePanel.add(spinWheel, 54, 408);
		spinWheel.setSize("133px", "38px");

		InlineLabel betNumberLbl = new InlineLabel("Bet Number Is:");
		absolutePanel.add(betNumberLbl, 43, 259);

		//Text box that displays the bet type
		betTypeText = new TextBox();
		betTypeText.setReadOnly(true);
		absolutePanel.add(betTypeText, 136, 310);
		betTypeText.setSize("87px", "16px");
		
		//Image of the board. Gets coordinates when clicked and set the bet type and bet val based off of coordinates
		board = new Image("board.png");
		board.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int x = event.getX();
				int y = event.getY(); 
				absolutePanel.setWidgetPosition(chip, x + 242, y - 4);		//Move chip image to where the user clicks. 
				controller.betTypeHandler(getBetType(x,y));
				if(getBetType(x,y) == BetType.NUM_MATCH){					//Set bet val if bet type is number match
					controller.setBetVal(getBetVal(x,y)); 
				}
			}
		});
		
		absolutePanel.add(board, 256, 10);
		board.setSize("296px", "484px");

		InlineLabel nlnlblBetTypeIs = new InlineLabel("Bet Type Is:");
		absolutePanel.add(nlnlblBetTypeIs, 43, 320);

		betNumText = new TextBox();
		betNumText.setReadOnly(true);
		absolutePanel.add(betNumText, 136, 249);
		betNumText.setSize("87px", "16px");

		InlineLabel congratsLbl = new InlineLabel("");
		absolutePanel.add(congratsLbl, 629, 162);

		InlineLabel walletAmountLbl = new InlineLabel("Amount in Wallet:");
		absolutePanel.add(walletAmountLbl, 22, 138);

		walletText = new TextBox();
		absolutePanel.add(walletText, 22, 166);
		walletText.setSize("103px", "18px");

		chip = new Image("chip.png");
		absolutePanel.add(chip, 341, 234);
		chip.setSize("28px", "28px");
		
		btnBackToMain = new Button("Back to Main Menu");
		btnBackToMain.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.print("Buttonclicked");
				runMainMenuClick();
			}
		});
		absolutePanel.add(btnBackToMain, 54, 464);
		btnBackToMain.setSize("133px", "47px");


		ok = new Button("OK");
		db.add(ok); 
		
		//click handler for button on dialog box. Hides box when clicked. 
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) { 
				db.hide(); 
			}
		});

	}
	
	public void setCallback(GameViewCallback callback) {
		this.callback = callback;
	}
	
	public void runClick(){
		//If the text is not empty, parse the int
		if(!betAmount.getText().isEmpty()){
			int bet = Integer.parseInt(betAmount.getText());
			int wallet = model.getPlayer().getBalance();
			
			//Check if wallet money does not exceed bet amount
			if(bet > wallet){						
				db.setText("You don't have that much money!");
				db.center();  
			}
			
			//Verify bet is positive
			else if(bet < 0){
				db.setText("Please enter a positive bet!"); 
				db.center(); 
			}

			else{
				controller.placeBetHandler(bet);
				betAmount.setText(""); 
				controller.spinHandler();

				wallet = model.getPlayer().getBalance(); 
				
				//Reset wallet amount to 1000 when funds are depleted. 
				if(wallet == 0){
					db.setText("You are out of money! $1000 has been added to your account");
					db.center(); 
					controller.addMoney(1000); 
				}
			}
		}
		
		else{
			//Display dialog box if the user does not enter a bet 
			db.setText("Please enter a bet!"); 
			db.center(); 
		}
		


	}
	
	//Set the model and add observer
	public void setModel(Roulette model) {
		this.model = model; 
		model.addObserver(this); 
	}
	
	//Set the controller
	public void setController(RouletteController c){
		this.controller = c; 
	}

	//Update the betType, wheel number, bet value, and wallet amount
	public void update(Observable o, Object hint){ 
		wheelNum.setText("" + model.getWheelVal());
		BetType b = model.getBetType(); 

		if(b != null){
			betTypeText.setText(b.toString());
		}

		walletText.setText("" + Integer.toString(model.getPlayer().getBalance())); 

		if(b == BetType.NUM_MATCH){
			betNumText.setText("" + model.getBetVal()) ;
		}
		else{
			betNumText.setText(""); 
		}

	}


	//Get the bet type based off of coordinates clicked on in regards to board.png
	public BetType getBetType(int x, int y){
		BetType b = null; 

		if(x > 68 && x < 120){						
			if( y > 56 && y < 184){				//Coordinates for First 12
				b = BetType.FIRST_TWELVE; 
			}
			if(y > 185 && y < 315){				//Coordinates for Middle 12
				b = BetType.MIDDLE_TWELVE; 
			}
			if(y > 319 && y < 444){				//Coordinates for Last 12
				b = BetType.LAST_TWELVE; 
			}
		}

		if(x > 12 && x < 66){
			if( y > 56 && y < 121){				//Coordinates for First half
				b = BetType.FIRST_HALF;
			}
			if( y > 121 && y < 184){			//Coordinates for Even
				b = BetType.EVEN; 	
			}
			if(y > 184 && y < 250){				//Coordinates for Red
				b = BetType.RED; 
			}
			if(y > 250 && y < 315){				//Coordinates for Black
				b = BetType.BLACK; 
			}
			if(y > 315 && y < 382){				//Coordinates for Odd
				b = BetType.ODD; 
			}
			if(y > 382 && y < 446){				//Coordinates for Last half
				b = BetType.LAST_HALF; 
			}
		}

		if(x > 120 && x < 289){					//Coordinates for number match
			if(y > 20 && y < 445){
				b = BetType.NUM_MATCH; 
			}	
		}
		
		if(y > 444){
			if(x > 120 && x < 178){
				b = BetType.FIRST_COLUMN;
			}
			if(x > 178 && x < 235){
				b = BetType.SECOND_COLUMN; 
			}
			if(x > 235 && x < 287){
				b = BetType.THIRD_COLUMN; 
			}
		}

		return b; 
	}

	//Use the coordinates that were clicked to get a bet value back in respect to board.png. 
	public int getBetVal(int x, int y) {
		int val = 0;

		//1st column
		if(x > 119 && x < 178){
			if(y > 55 && y < 89){				//Coordinates at 1
				val = 1; 
			}
			if(y > 89 && y < 122){				//Coordinates at 4
				val = 4; 
			}
			if(y > 122 && y < 155){				//Coordinates at 7
				val = 7; 
			}
			if(y > 155 && y < 188){				//Coordinates at 10
				val = 10; 						
			}
			if(y > 188 && y < 220){				//Coordinates at 13
				val = 13; 
			}
			if(y > 220 && y < 250){				//Coordinates at 16
				val = 16; 
			}
			if(y > 250 && y < 282){				//Coordinates at 19
				val = 19; 
			}
			if(y > 282 && y < 315){				//Coordinates at 22
				val = 22; 
			}
			if(y > 315 && y < 347){				//Coordinates at 25
				val = 25; 
			}
			if(y > 347 && y < 379){				//Coordinates at 28
				val = 28; 
			}
			if(y > 379 && y < 411){				//Coordinates at 31
				val = 31; 
			}
			if(y > 411 && y < 445){				//Coordinates at 34
				val = 34; 
			}
		}

		//2nd column
		if(x > 178 && x < 235){
			if(y > 55 && y < 89){				//Coordinates at 2
				val = 2; 
			}
			if(y > 89 && y < 122){				//Coordinates at 5
				val = 5; 
			}
			if(y > 122 && y < 155){				//Coordinates at 8
				val = 8; 
			}
			if(y > 155 && y < 188){				//Coordinates at 11
				val = 11; 
			}
			if(y > 188 && y < 220){				//Coordinates at 14
				val = 14; 
			}
			if(y > 220 && y < 250){				//Coordinates at 17
				val = 17; 
			}
			if(y > 250 && y < 282){				//Coordinates at 20
				val = 20; 
			}
			if(y > 282 && y < 315){				//Coordinates at 23
				val = 23; 
			}
			if(y > 315 && y < 347){				//Coordinates at 26
				val = 26; 
			}
			if(y > 347 && y < 379){				//Coordinates at 29
				val = 29; 
			}
			if(y > 379 && y < 411){				//Coordinates at 32
				val = 32; 
			}
			if(y > 411 && y < 445){				//Coordinates at 35
				val = 35; 
			}
		}

		//Third column
		if(x > 235 && x < 287){
			if(y > 55 && y < 89){				//Coordinates at 3
				val = 3; 
			}
			if(y > 89 && y < 122){				//Coordinates at 6
				val = 6; 
			}
			if(y > 122 && y < 155){				//Coordinates at 9
				val = 9; 
			}
			if(y > 155 && y < 188){				//Coordinates at 12
				val = 12; 
			}
			if(y > 188 && y < 220){				//Coordinates at 15
				val = 15; 
			}
			if(y > 220 && y < 250){				//Coordinates at 18
				val = 18; 
			}
			if(y > 250 && y < 282){				//Coordinates at 21
				val = 21; 
			}
			if(y > 282 && y < 315){				//Coordinates at 24
				val = 24; 
			}
			if(y > 315 && y < 347){				//Coordinates at 27
				val = 27; 
			}
			if(y > 347 && y < 379){				//Coordinates at 30
				val = 30; 
			}
			if(y > 379 && y < 411){				//Coordinates at 33
				val = 33; 
			}
			if(y > 411 && y < 445){				//Coordinates at 36
				val = 36; 
			}
		}

		if(x > 150 && x < 265){					//Coordinates at 0
			if(y > 23 && y < 55){
				val = 0; 
			}
		}

		return val; 
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
}


