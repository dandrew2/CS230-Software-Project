package edu.ycp.casino;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.casino.shared.cardgame.Card;
import edu.ycp.casino.shared.cardgame.Deck;

public class DeckTest {

	private Deck deck1;
	private Deck deck2;
	private Card firstCard;
	
	
	
	@Before
	public void setUp() {
		deck1 = new Deck();
		deck2 = new Deck();
		firstCard = new Card(null, null);
	}
	
	
	
	@Test
	public void testGetNumOfCards()
	{
		assertEquals(deck1.getNumCards(),52);
		
	}
	
	@Test
	public void testGetCard()
	{
		
	}
	
	
}
