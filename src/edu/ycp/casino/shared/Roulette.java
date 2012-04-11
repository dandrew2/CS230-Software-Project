package edu.ycp.casino.shared;

import java.util.Random;


public class Roulette {
	private static int [] wheel; 
	private static Random generator;
	
	
	
		public Roulette(){
			for(int i = 0; i < 37; i++){
				wheel[i] =  i; 
			}
		}
		
	public int spinWheel(){
		int val = generator.nextInt(37);
		return wheel[val]; 
	}
	
	public String getColor(int val){
		if(val%2 == 0){
			return "Black"; 
		}
		else{
			return "Red"; 
		}
	}
	
	//public void placeBet
	
	public Boolean checkWin(BetType b, int val){
		Boolean win = false;
		String color = getColor(val); 
		
		if(b == b.BLACK && color == "Black"){
			win = true; 
		}
		
		if(b == b.RED && color == "Red"){
			win = true; 
		}
		
		if(b == b.FIRST_TWELVE && val < 13){
			win = true; 
		}
		
		else{
			if(b == b.MIDDLE_TWELVE && val < 25){
				win = true; 
			}
			else {
				if(b == b.LAST_TWELVE && val < 37){
					win = true; 
				}
			}
		}
		
		if(b == b.ZERO && val == 0){
			win = true; 
		}
		
		return win; 
	}
	
	
		
}
