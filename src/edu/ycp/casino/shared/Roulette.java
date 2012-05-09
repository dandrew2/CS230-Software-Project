package edu.ycp.casino.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import edu.ycp.casino.shared.cardgame.poker.Pot;


public class Roulette extends Game {
	private static int [] wheel; 
	private static Random generator;
	private int wheelVal;
	private int betNumber; 
	private Pot p;
	private BetType betType; 
	private Player player;

	private static ArrayList<Integer> red =
			new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36})); 

	//Slot color enumeration
	public enum SlotColor {
		RED, BLACK;
	}

	//Column number enumeration
	public enum Column {
		FIRST, SECOND, THIRD, NO_COLUMN; 
	}

	//Roulette constructor
	public Roulette(Player player){
		wheel = new int[38]; 			//Construct the wheel
		p = new Pot(); 
		generator = new Random(); 		//Create the random generator
		for(int i = 0; i < 37; i++){	//Fill the wheel with values
			wheel[i] =  i;
		}

		this.player = player;			//Create the player
	}

	//Return amount in pot
	public int getPot(){	
		return p.getAmount();  
	}

	//Set pot value to zero
	public void clearPot(){
		p.takeAll(); 
	}

	//Return the player
	public Player getPlayer(){
		return player; 
	}

	//Set the player
	public void setPlayer(Player p){
		player = p; 
	}

	//Generate a random value from the wheel
	public void spinWheel(){
		wheelVal = generator.nextInt(37);
	}

	//Return the wheel value
	public int getWheelVal(){
		return wheelVal; 
	}

	public void setWheelVal(int val){
		wheelVal = val; 
	}

	//Return the number bet on
	public int getBetVal(){
		return betNumber; 
	}

	//Set the number to be bet on
	public void setBetVal(int val){
		betNumber = val;  
	}

	//Return the color based on the value the wheel lands on. 
	public SlotColor getColor(int val){
		if(red.contains(val)){
			return SlotColor.RED; 
		}
		else{
			return SlotColor.BLACK; 
		}
	}

	//Return the column based on the value the wheel lands on
	public Column getColumn(int val){
		Column c = null;

		if(val < 3){
			if(val == 1){c = Column.FIRST;}
			if(val == 2){c =  Column.SECOND;}
			if(val == 0){c =  Column.NO_COLUMN;}
		}
		else{
			if(val % 3 == 0){
				c =  Column.THIRD; 
			}
			else if(val % 3 == 1){
				c =  Column.FIRST; 
			}
			else{
				c =  Column.SECOND; 
			}
		}

		return c; 
	}

	//Return the bet type
	public BetType getBetType(){
		return betType; 
	}

	//Set the bet type
	public void setBetType(BetType b){
		betType = b; 
	}

	//Place a bet
	public void placeBet(int amt){
		p.add(amt);							//Add the bet to the pot
		player.getWallet().takeBet(amt); 	//Take the bet amount from the users wallet

	}

	//Check if the user won based off the wheel value, bet type, and bet number
	public Boolean checkWin(){
		Boolean win = false;
		SlotColor color = getColor(wheelVal);
		Column column = getColumn(wheelVal); 

		if(betType == BetType.NUM_MATCH && betNumber == wheelVal){
			win = true; 
		}

		if(betType == BetType.FIRST_HALF && wheelVal < 19){
			win = true; 
		}

		if(betType == BetType.LAST_HALF && wheelVal > 18){
			win = true; 
		}

		if(betType == BetType.FIRST_COLUMN && column == Column.FIRST){
			win = true; 
		}

		if(betType == BetType.SECOND_COLUMN && column == Column.SECOND){
			win = true; 
		}

		if(betType == BetType.THIRD_COLUMN && column == Column.THIRD){
			win = true; 
		}

		if(betType == BetType.BLACK && color == SlotColor.BLACK){
			win = true; 
		}

		if(betType == BetType.RED && color == SlotColor.RED){
			win = true; 
		}

		if(betType== BetType.FIRST_TWELVE && wheelVal < 13){
			win = true; 
		}
		
		if(betType == BetType.MIDDLE_TWELVE && wheelVal < 25 && wheelVal > 12){
			win = true; 
		}
		
		if(betType == BetType.LAST_TWELVE && wheelVal > 24){
			win = true; 
		}

		if(betType == BetType.ODD && wheelVal % 2 == 1){
			win = true; 
		}

		if(betType == BetType.EVEN && wheelVal % 2 == 0){
			win = true; 
		}

		return win; 
	}

	//Return the payout based off the amount bet. Each bet type has different odds that pay differently
	public int getPayout(int bet){
		int payout = 0; 

		if(betType == BetType.NUM_MATCH){
			payout = bet*37; 
		}

		if(betType == BetType.BLACK || betType == BetType.RED){
			payout = bet*2; 
		}

		if(betType == BetType.ODD || betType == BetType.EVEN){
			payout = bet*2; 
		}

		if(betType == BetType.BLACK || betType == BetType.RED){
			payout = bet*2; 
		}

		if(betType == BetType.FIRST_HALF || betType == BetType.LAST_HALF){
			payout = bet*2; 
		}

		if(betType == BetType.FIRST_TWELVE || betType == BetType.MIDDLE_TWELVE || betType == BetType.LAST_TWELVE){

			payout = bet*3; 
		}

		if(betType == BetType.FIRST_COLUMN || betType == BetType.SECOND_COLUMN || betType == BetType.THIRD_COLUMN){

			payout = bet*3; 
		}
		
		return payout; 
	}	
}
