package edu.ycp.casino;

public enum SlotsSymbols {
	SEVEN,
	CHERRY,
	BAR,
	PLUM,
	ORANGE,
	BELL,
	GRAPES,
	LIME,
	WATERMELON;
	
	public String toString() {
        if (this == SEVEN) {
                return "7";
        } else if (this == CHERRY) {
                return "C";
        } else if (this == BAR) {
                return "B";
        } else if (this == PLUM) {
                return "P";
        } else if (this == ORANGE) {
                return "O";
        } else if (this == BELL) {
                return "B";
        } else if (this == GRAPES) {
                 return "G";
        } else if (this == LIME) {
                 return "L";
        } else if (this == WATERMELON) {
                 return "W";
          }else {
        	throw new IllegalStateException(); // impossible
        }
	}
}
