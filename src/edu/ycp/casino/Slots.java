package edu.ycp.casino;
import java.util.Random; 

public class Slots extends Game {
	
	private SlotsSymbols[] slot = new SlotsSymbols[3];
	
	public Slots(SlotsSymbols[] slot)
	{
		this.slot = slot;
	}
	
	public SlotsSymbols[] getSlot(){
		
		return this.slot;
	}

	public void setSlot(SlotsSymbols[] slot){
	
	this.slot = slot;
	}
	
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
	
	
	public void spin(){
		
		Random rand = new Random(); 
		
		for(int i = 0;i < 2;i++)
		{
			int newNumber = rand.nextInt();
			
			if(newNumber == 0)
			{
				slot[i] = SlotsSymbols.BAR;
			}
			
			if(newNumber == 1)
			{
				slot[i] = SlotsSymbols.BELL;
			}
			
			if(newNumber == 2)
			{
				slot[i] = SlotsSymbols.CHERRY;
			}
			
			if(newNumber == 3)
			{
				slot[i] = SlotsSymbols.GRAPES;
			}
			
			if(newNumber == 4)
			{
				slot[i] = SlotsSymbols.LIME;
			}
			
			if(newNumber == 5)
			{
				slot[i] = SlotsSymbols.ORANGE;
			}
			
			if(newNumber == 6)
			{
				slot[i] = SlotsSymbols.PLUM;
			}
			
			if(newNumber == 7)
			{
				slot[i] = SlotsSymbols.SEVEN;
			}	
			
			if(newNumber == 8)
			{
				slot[i] = SlotsSymbols.WATERMELON;
			}	
						
		}
		
	}

}
