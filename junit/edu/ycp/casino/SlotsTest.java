package edu.ycp.casino;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.casino.shared.SlotsSymbols;

public class SlotsTest {
	
	
	private SlotsSymbols[] slot1;
	private SlotsSymbols[] slot2;
	private SlotsSymbols[] slot3;
	private SlotsSymbols[] slot4;
	
	private Slots game1;

	@Before
	public void setUp() {
		slot1 = new SlotsSymbols[3];
		
		slot1[0] = SlotsSymbols.BAR;
		slot1[1] = SlotsSymbols.BELL;
		slot1[2] = SlotsSymbols.CHERRY;
		
		slot2 = new SlotsSymbols[3];
		
		slot2[0] = SlotsSymbols.BAR;
		slot2[1] = SlotsSymbols.BELL;
		slot2[2] = SlotsSymbols.CHERRY;
		
		slot3 = new SlotsSymbols[3];
		
		slot3[0] = SlotsSymbols.BELL;
		slot3[1] = SlotsSymbols.BELL;
		slot3[2] = SlotsSymbols.BELL;
		
		slot4 = new SlotsSymbols[3];
		
		slot4[0] = SlotsSymbols.BELL;
		slot4[1] = SlotsSymbols.BELL;
		slot4[2] = SlotsSymbols.CHERRY;
	}
	
	@Test
	public void getSlot() throws Exception {
		game1 = new Slots();
		game1.setSlot(slot2);
		
		assertSame(slot2,game1.getSlot());
		assertNotSame(slot3,game1.getSlot());
		
		
	}
	
	@Test
	public void setSlot() throws Exception {
		game1 = new Slots();
		game1.setSlot(slot2);
		
		assertSame(slot2,game1.getSlot());
		assertNotSame(slot3,game1.getSlot());
	}
	

	@Test
	public void testSpin() throws Exception {
		game1 = new Slots();
		game1.setSlot(slot1);
		game1.spin();
		
	}

	@Test
	public void testCheckWin() throws Exception {
		game1 = new Slots();
		
		game1.setSlot(slot3);
		assertEquals(true,game1.checkWin());
		game1.setSlot(slot4);
		assertEquals(false,game1.checkWin());
	}
	

}
