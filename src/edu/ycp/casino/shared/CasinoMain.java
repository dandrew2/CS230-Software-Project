package edu.ycp.casino.shared;





public class CasinoMain{

	public static void main(String[] args)
	{
		Player player = new Player();
		
		Roulette r = new Roulette(); 
		r.play(player); 
	}


}