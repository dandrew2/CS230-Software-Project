package edu.ycp.casino;

import java.awt.Color;
import java.util.Random;


public class Roulette {
	private static int [] wheel; 
	private static Random generator;
	
	
		public Roulette(){
			for(int i = 0; i < 38; i++){
				wheel[i] =  i + 1; 
			}
		}
		
	public int spinWheel(){
		int val = generator.nextInt(37);
		return wheel[val]; 
	}
	
	
		
}
