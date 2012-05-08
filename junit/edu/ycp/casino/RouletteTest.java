package edu.ycp.casino;

import static org.junit.Assert.*;

import org.junit.Test;


import edu.ycp.casino.shared.BetType;
import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.Roulette;
import edu.ycp.casino.shared.Roulette.Column;
import edu.ycp.casino.shared.Roulette.SlotColor;

public class RouletteTest {

	private Roulette game1; 
	private Player p; 
	

	@Test
	public void testGetPot() {						
		game1 = new Roulette();
		game1.placeBet(500); 
		assertEquals(500, game1.getPot());
	}
	
	@Test
	public void testClearPot(){
		game1 = new Roulette(); 
		game1.placeBet(500); 
		game1.clearPot(); 
		assertEquals(0, game1.getPot());
	}
	
	@Test
	public void testGetPlayer(){
		game1 = new Roulette(); 
		p = new Player(); 
		game1.setPlayer(p); 
		assertTrue(p.equals(game1.getPlayer())); 
	}
	
	@Test
	public void testSpinWheel(){
		game1 = new Roulette(); 
		game1.spinWheel(); 
		int num1 = game1.getWheelVal(); 
		game1.spinWheel(); 
		int num2 = game1.getWheelVal(); 
	
		assertNotSame(num1, num2); 	 
	}
	
	@Test
	public void testGetBetVal(){
		game1 = new Roulette(); 
		game1.setBetVal(25); 
		assertEquals(25, game1.getBetVal());
	}
	
	@Test
	public void testGetColor(){
		game1 = new Roulette();
		assertEquals(SlotColor.RED, game1.getColor(1));
		assertEquals(SlotColor.BLACK, game1.getColor(2)); 
	}
	
	@Test
	public void testGetColumn(){
		game1 = new Roulette(); 
		assertEquals(Column.FIRST, game1.getColumn(1)); 
		assertEquals(Column.SECOND, game1.getColumn(2)); 
		assertEquals(Column.THIRD, game1.getColumn(3)); 
		assertEquals(Column.NO_COLUMN, game1.getColumn(0)); 
	}
	
	@Test
	public void testGetBetType(){
		game1 = new Roulette(); 
		game1.setBetType(BetType.RED);
		assertEquals(BetType.RED, game1.getBetType());
	}
	
	@Test
	public void testCheckWin(){
		game1 = new Roulette();
		game1.setWheelVal(20); 
		game1.setBetVal(20);
		game1.setBetType(BetType.NUM_MATCH);
		assertTrue(game1.checkWin());
		game1.setWheelVal(21);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.FIRST_HALF);
		game1.setWheelVal(10);
		assertTrue(game1.checkWin());
		game1.setWheelVal(25);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.LAST_HALF);
		game1.setWheelVal(10);
		assertFalse(game1.checkWin());
		game1.setWheelVal(25);
		assertTrue(game1.checkWin());
		
		game1.setBetType(BetType.FIRST_COLUMN);
		game1.setWheelVal(1);
		assertTrue(game1.checkWin());
		game1.setWheelVal(2);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.SECOND_COLUMN);
		game1.setWheelVal(2);
		assertTrue(game1.checkWin());
		game1.setWheelVal(3);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.THIRD_COLUMN);
		game1.setWheelVal(3);
		assertTrue(game1.checkWin());
		game1.setWheelVal(1);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.RED);
		game1.setWheelVal(1);
		assertTrue(game1.checkWin());
		game1.setWheelVal(2);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.BLACK);
		game1.setWheelVal(2);
		assertTrue(game1.checkWin());
		game1.setWheelVal(1);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.ODD);
		game1.setWheelVal(21);
		assertTrue(game1.checkWin());
		game1.setWheelVal(30);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.EVEN);
		game1.setWheelVal(22);
		assertTrue(game1.checkWin());
		game1.setWheelVal(31);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.FIRST_TWELVE);
		game1.setWheelVal(10);
		assertTrue(game1.checkWin());
		game1.setWheelVal(30);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.MIDDLE_TWELVE);
		game1.setWheelVal(20);
		assertTrue(game1.checkWin());
		game1.setWheelVal(30);
		assertFalse(game1.checkWin());
		
		game1.setBetType(BetType.LAST_TWELVE);
		game1.setWheelVal(30);
		assertTrue(game1.checkWin());
		game1.setWheelVal(10);
		assertFalse(game1.checkWin());
	}
	
	@Test
	public void testGetPayout(){
		game1 = new Roulette();
		
		int bet = 10;
		game1.setBetType(BetType.NUM_MATCH);
		assertEquals(370, game1.getPayout(bet));
		
		game1.setBetType(BetType.BLACK);
		assertEquals(20, game1.getPayout(bet));
		
		game1.setBetType(BetType.LAST_HALF);
		assertEquals(20, game1.getPayout(bet));
		
		game1.setBetType(BetType.ODD);
		assertEquals(20, game1.getPayout(bet));
		
		game1.setBetType(BetType.FIRST_TWELVE);
		assertEquals(30, game1.getPayout(bet));
		
		game1.setBetType(BetType.FIRST_COLUMN);
		assertEquals(30, game1.getPayout(bet));

	}

}
