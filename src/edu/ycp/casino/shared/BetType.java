package edu.ycp.casino.shared;

public enum BetType {
	RED,
	BLACK,
	FIRST_TWELVE,
	MIDDLE_TWELVE,
	LAST_TWELVE,
	EVEN,
	ODD,
	FIRST_COLUMN,
	SECOND_COLUMN,
	THIRD_COLUMN,
	FIRST_HALF,
	LAST_HALF,
	NUM_MATCH;

	public String toString(){
		String s = null;
		
		if(this == RED){
			s = "Red"; 
		}
		else if(this == BLACK){
			s =  "Black"; 
		}
		else if(this == FIRST_TWELVE){
			s =  "First 12"; 
		}
		else if(this == MIDDLE_TWELVE){
			s =  "Second 12"; 
		}
		else if(this == LAST_TWELVE){
			s =  "Last 12"; 
		}
		else if(this == NUM_MATCH){
			s =  "Match Number"; 
		}
		else if(this == FIRST_COLUMN){
			s =  "First Column"; 
		}
		else if(this == SECOND_COLUMN){
			s =  "Second Column"; 
		}
		else if(this == THIRD_COLUMN){
			s =  "Third Column"; 
		}
		else if(this == ODD){
			s =  "Odd"; 
		}
		else if(this == EVEN){
			s =  "Even"; 
		}
		else if(this == FIRST_HALF){
			s =  "1 to 18"; 
		}
		else if(this == LAST_HALF){
			s =  "19 to 36"; 
		}

		else{
			throw new IllegalStateException(); 
		}
		
		return s; 
	}
}
