package edu.ycp.casino.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner; 

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import edu.ycp.casino.shared.cardgame.poker.Pot;


public class Roulette extends Game {
	private static int [] wheel; 
	private static Random generator;
	private int wheelVal; 
	private Pot p;
	private BetType betType; 
	private Player player;

	private static ArrayList<Integer> red = (ArrayList<Integer>) Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36); 




	private enum SlotColor {
		RED, BLACK;
	}

	private enum Column {
		FIRST, SECOND, THIRD, NO_COLUMN; 
	}


	public Roulette(){
		wheel = new int[38]; 
		p = new Pot(); 
		generator = new Random(); 
		for(int i = 0; i < 37; i++){
			wheel[i] =  i;
		}	
	}


	public Player getPlayer(){
		return player; 
	}

	public void spinWheel(){
		wheelVal = generator.nextInt(37);
	}

	public int getWheelVal(){
		return wheelVal; 
	}


	public SlotColor getColor(int val){
		if(red.contains(val)){
			return SlotColor.RED; 
		}
		else{
			return SlotColor.BLACK; 
		}
	}

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


	public BetType getBetType(){
		return betType; 
	}

	public void setBetType(BetType b){
		betType = b; 
	}

	public void placeBet(int amt){
		p.add(amt); 
	}



	public Boolean checkWin(int betNumber, int wheelNumber){
		Boolean win = false;
		SlotColor color = getColor(wheelNumber); 

		if(betType == BetType.NUM_MATCH && betNumber == wheelNumber){
			win = true; 
		}
		
		if(betType == BetType.FIRST_HALF && betNumber < 19 && betNumber != 0){
			win = true; 
		}
		
		if(betType == BetType.LAST_HALF && betNumber > 18){
			win = true; 
		}

		if(betType == BetType.BLACK && color == SlotColor.BLACK){
			win = true; 
		}

		if(betType == BetType.RED && color == SlotColor.RED){
			win = true; 
		}

		if(betType== BetType.FIRST_TWELVE && wheelNumber < 13){
			win = true; 
		}

		else{
			if(betType == BetType.MIDDLE_TWELVE && wheelNumber < 25){
				win = true; 
			}
			else {
				if(betType == BetType.LAST_TWELVE && wheelNumber < 37){
					win = true; 
				}
			}
		}

		if(betType == BetType.ZERO && wheelNumber == 0){
			win = true; 
		}

		return win; 
	}

	public int getPayout(int bet){
		int payout = 0; 

		if(betType == BetType.NUM_MATCH){
			payout = bet*37; 
		}

		if(betType == BetType.BLACK || betType == BetType.RED){
			payout = bet*2; 
		}

		if(betType == BetType.FIRST_TWELVE || betType == BetType.MIDDLE_TWELVE || betType == BetType.LAST_TWELVE){
			payout = bet*3; 
		}

		return payout; 
	}


	public void play(Player p){
		/*int betAmount;
		int type; 
		int numToBet=0;
		int wheelNum; 
		int winnings;
		BetType b = null;
		Boolean gameLoop = true; 

		while(gameLoop == true){
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Please select a bet amount: ");
			betAmount = keyboard.nextInt();
			p.getWallet().takeBet(betAmount); 

			System.out.println("Please select a bet type:");
			System.out.println("1. Red \n2. Black \n3. First 12 \n4. Mid 12 \n5. Last 12 \n6. Number"); 
			type = keyboard.nextInt(); 

			if(type == 6){
				System.out.println("Please enter a num to bet"); 
				numToBet = keyboard.nextInt();
				b = BetType.NUM_MATCH; 
			}

			if(type == 1){
				b = BetType.RED; 
			}
			if(type == 2){
				b = BetType.BLACK; 
			}
			if(type == 3){
				b = BetType.FIRST_TWELVE; 
			}
			if(type == 4){
				b = BetType.MIDDLE_TWELVE; 
			}
			if(type == 5){
				b = BetType.LAST_TWELVE; 
			}

			placeBet(betAmount, b); 

			spinWheel();

			System.out.printf("The wheel landed on %d", wheelVal);

			if(checkWin(b, numToBet, wheelVal) == true){
				winnings = getPayout(betAmount, b); 
				p.addBalance(winnings);

				System.out.printf("Congrats, you won %d dollars!", winnings); 
			}
			else{
				winnings = 0; 
				System.out.printf("Sorry, you won %d dollars", winnings); 
			}

			System.out.print("Play again?: ");
			System.out.print("1. Yes\n2. No"); 

			if(keyboard.nextInt() == 2){
				gameLoop = false; 
			}
		}*/
	}
}
