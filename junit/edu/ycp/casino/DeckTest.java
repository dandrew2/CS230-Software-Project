package edu.ycp.casino;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.casino.shared.cardgame.Card;
import edu.ycp.casino.shared.cardgame.Deck;
import edu.ycp.casino.shared.cardgame.Rank;
import edu.ycp.casino.shared.cardgame.Suit;

public class DeckTest {

	private Deck deck1;
	private Deck deck2;
	private Card firstCard;
	private Card addCard;
	
	
	
	@Before
	public void setUp() {
		deck1 = new Deck();
		deck2 = new Deck();
		firstCard = new Card(Suit.CLUBS, Rank.ACE);
		addCard = new Card(Suit.SPADES,Rank.EIGHT);
		
	}
	
	
	
	@Test
	public void testGetNumOfCards()
	{
		assertEquals(deck1.getNumCards(),52);
		deck2.drawCard();
		assertEquals(deck2.getNumCards(),51);
		
	}
	
	@Test
	public void testGetCard()
	{
		assertEquals(deck1.getCard(0).getRank(),Rank.ACE);
		assertEquals(deck1.getCard(0).getSuit(),Suit.CLUBS);
		assertEquals(deck1.getCard(1).getSuit(),Suit.DIAMONDS);
		assertEquals(deck1.getCard(4).getRank(),Rank.TWO);
	}
	
	@Test
	public void testAddCard()
	{
		deck1.addCard(addCard);
		assertEquals(deck1.getCard(52).getRank(),Rank.EIGHT);
		assertEquals(deck1.getCard(52).getSuit(),Suit.SPADES);
	}
	
	@Test
	public void testDrawCard()
	{
		Card cardToTest;
		
		cardToTest = deck1.drawCard();
	}
	
	
}
