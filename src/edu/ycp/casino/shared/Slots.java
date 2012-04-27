package edu.ycp.casino.shared;
import java.util.Random; 


public class Slots extends Game {
	
	private SlotsSymbols[] slot = new SlotsSymbols[3];
	
	public Slots()
	{
		this.slot = new SlotsSymbols[3];
		
		for(int i = 0; i < 3; i++) //Initialize slot
		{
			slot[i] = SlotsSymbols.WATERMELON;
		}
	}
	
	
	//Getter method
	public SlotsSymbols[] getSlot(){
		
		return this.slot;
	}
	
	
	//Setter method
	public void setSlot(SlotsSymbols[] slot){
	
	this.slot = slot;
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
	
	
	//Method to assign random values to slot.
	public void spin(){
		
		Random rand = new Random(); 
		int newNumber;

	
		for(int i = 0; i < 3; i++)
		{
			newNumber = rand.nextInt(9);
			
			if(newNumber == 0)
			{
				this.slot[i] = SlotsSymbols.BAR;
			}
			
			if(newNumber == 1)
			{
				this.slot[i] = SlotsSymbols.BELL;
			}
			
			if(newNumber == 2)
			{
				this.slot[i] = SlotsSymbols.CHERRY;
			}
			
			if(newNumber == 3)
			{
				this.slot[i] = SlotsSymbols.GRAPES;
			}
			
			if(newNumber == 4)
			{
				this.slot[i] = SlotsSymbols.LIME;
			}
			
			if(newNumber == 5)
			{
				this.slot[i] = SlotsSymbols.ORANGE;
			}
			
			if(newNumber == 6)
			{
				this.slot[i] = SlotsSymbols.PLUM;
			}
			
			if(newNumber == 7)
			{
				this.slot[i] = SlotsSymbols.SEVEN;
			}	
			
			if(newNumber == 8)
			{
				this.slot[i] = SlotsSymbols.WATERMELON;
			}	
						
		}
		
	}
	
	
	public void play(Player p)
	{
		int bet;
		boolean stillPlaying = true;
		Scanner keyboard = new Scanner(System.in);
		
		while(stillPlaying == true)
		{
			System.out.println("How much would you like to bet?");
		
			bet = keyboard.nextInt();
		
			this.spin();
			
			this.printSlot();
		
			if(this.checkWin() == true)
			{
				p.addBalance(bet);
				System.out.println("Congrats you won!");
			}else
			{
				p.addBalance(bet * -1);
				
				System.out.println("You lost better luck next time.");
			}
			
			System.out.println("You now have: " + p.getBalance());
			
			if(p.getBalance() <= 0)	//check player still has money.
			{
				System.out.println("You've gone broke.");
				break;
			}
			
			System.out.println("Would you like to keep playing (Press 1 to exit)?");
			
			if(keyboard.nextInt() == 1)
			{
				System.out.println("Thanks for playing");
				stillPlaying = false;
			}
		
		}
	}
	
	//Method to print out slot to console.
	public void printSlot()
	{
		System.out.println("Slot: " + this.slot[0].toString() + " " + this.slot[1].toString() + " " + this.slot[2].toString());
	}

}
