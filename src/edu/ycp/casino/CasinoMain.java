package edu.ycp.casino;
import java.util.Scanner;



public class CasinoMain{

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		int gameChoice;
		int cash = 1000;
		int bet;
		boolean stillPlaying = true;
		
		System.out.println("Which Game would you like to play?");
		
		gameChoice = keyboard.nextInt();
		
		if(gameChoice == 1)
		{
			while(stillPlaying == true)
			{
				Slots game1 = new Slots();
			
				System.out.println("How much would you like to bet?");
			
				bet = keyboard.nextInt();
			
				game1.spin();
				
				game1.printSlot();
			
				if(game1.checkWin() == true)
				{
					cash = cash + bet; 
					System.out.println("Congrats you won!");
				}else
				{
					cash = cash - bet;
					System.out.println("You lost better luck next time.");
					
				}
				
				System.out.println("You now have: " + cash);
				
				System.out.println("Would you like to keep playing (Press 1 to exit)?");
				
				if(keyboard.nextInt() == 1)
				{
					System.out.println("Thanks for playing");
					stillPlaying = false;
				}
			
			}
			
		}
	}


}