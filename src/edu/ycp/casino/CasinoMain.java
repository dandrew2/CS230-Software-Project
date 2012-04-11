package edu.ycp.casino;
import java.util.Scanner;

import edu.ycp.casino.shared.Player;



public class CasinoMain{

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		int gameChoice;
		
		Player player = new Player(1000);
		
		System.out.println("Which Game would you like to play?");
		
		gameChoice = keyboard.nextInt();
		
		if(gameChoice == 1) //User selected Slots
		{
			Slots game1 = new Slots();
			game1.play(player);	
		}
		
		if(gameChoice == 2) // User selected Poker
		{
			
		}
		
		if(gameChoice == 3) // User selected Roulette
		{
			
		}
		
		if(gameChoice == 4) // User selected Black Jack
		{
			
		}
	}


}