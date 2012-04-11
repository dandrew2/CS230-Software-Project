package edu.ycp.casino;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.*;
public class BlackjackTest extends TestCase{
	private Hand hOne;
	private Hand hTwo;
	private Card ace;
	private Card jack;
	
	@Before
	protected void setUp() throws Exception{
		hOne = new Hand();
		ace = new Card(CardSuits.CLUB,CardValues.ACE);
		jack = new Card(CardSuits.CLUB,CardValues.JACK);
		hOne.addToHand(ace);
		hOne.addToHand(jack);
	}
	@Test
	public void testBJValue() throws Exception{
		//test ace value
		assertEquals(11,hOne.getBJValue(ace));
	}
	@Test
	public void testBJHandValue() throws Exception{
		//test hand value
		assertEquals(21,hOne.getBJHandValue());
	}
}
