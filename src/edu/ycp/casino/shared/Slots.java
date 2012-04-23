package edu.ycp.casino.shared;

import java.util.Random; 
import java.util.Scanner;


public class Slots extends Game {
	
	private SlotsSymbols[] slot = new SlotsSymbols[3];
	private int money;
	public int bet;
	
	public Slots()
	{
		this.slot = new SlotsSymbols[3];
		
		this.money = 1000;
	}
	
	
	//Getter method
	public SlotsSymbols[] getSlot(){
		
		return this.slot;
	}
	
	
	//Setter method
	public void setSlot(SlotsSymbols[] slot){
	
		this.slot = slot;
		setChanged();
		notifyObservers();
	}
	
	
	//Check if every slot has same value.
	public boolean checkWin(){
		
		if(slot[0].equals(slot[1]))
		{
			if(slot[1].equals(slot[2]))
			{
				return true;
			}
			return false;
		}
		else 
		{
			return false;
		}
	}
	
	public int getMoney()
	{
		return this.money;
	}
	
	public void setMoney(int m)
	{
		this.money = m;
		setChanged();
		notifyObservers();
	}
	
	
	//Method to assign random values to slot.
	public void spin(){
		
		Random rand = new Random(); 
		int newNumber;
		SlotsSymbols[] newSlot = new SlotsSymbols[3];

	
		for(int i = 0; i < 3; i++)
		{
			newNumber = rand.nextInt(9);
			
			if(newNumber == 0)
			{
				newSlot[i] = SlotsSymbols.BAR;
			}
			
			if(newNumber == 1)
			{
				newSlot[i] = SlotsSymbols.BELL;
			}
			
			if(newNumber == 2)
			{
				newSlot[i] = SlotsSymbols.CHERRY;
			}
			
			if(newNumber == 3)
			{
				newSlot[i] = SlotsSymbols.GRAPES;
			}
			
			if(newNumber == 4)
			{
				newSlot[i] = SlotsSymbols.LIME;
			}
			
			if(newNumber == 5)
			{
				newSlot[i] = SlotsSymbols.ORANGE;
			}
			
			if(newNumber == 6)
			{
				newSlot[i] = SlotsSymbols.PLUM;
			}
			
			if(newNumber == 7)
			{
				newSlot[i] = SlotsSymbols.SEVEN;
			}	
			
			if(newNumber == 8)
			{
				newSlot[i] = SlotsSymbols.WATERMELON;
			}	
						
		}
		
		this.setSlot(newSlot);	
	}
	
}
	
