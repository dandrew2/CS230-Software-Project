package edu.ycp.casino;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.casino.shared.cardgame.Card;

import edu.ycp.casino.shared.cardgame.Hand;
import edu.ycp.casino.shared.cardgame.Rank;
import edu.ycp.casino.shared.cardgame.Suit;

public class HandTest {
	private Hand hand;
	private Hand hand1;
	private Hand hand2;
	private Hand hand3;
	private Card five;
	private Card king;
	private Card jack;
	private Card four;
	private Card ace;
	@Before
	public void setUp(){
		hand = new Hand();
		hand1 = new Hand();
		hand2 = new Hand();
		hand3 = new Hand();
		
		//Cards
		five = new Card(Suit.CLUBS,Rank.FIVE);
		jack = new Card(Suit.CLUBS,Rank.JACK);
		four = new Card(Suit.DIAMONDS,Rank.FOUR);
		king = new Card (Suit.HEARTS, Rank.KING);
		ace = new Card (Suit.DIAMONDS, Rank.ACE);
		//value 14
		hand.addCard(four);
		hand.addCard(jack);
		
		//value 15
		hand1.addCard(ace);//1
		hand1.addCard(four);
		hand1.addCard(king);
		
		//value 14
		hand2.addCard(five);
		hand2.addCard(four);
		hand2.addCard(five);
		
		//value 21
		hand3.addCard(ace);//11
		hand3.addCard(jack);
		
		
	}
	@Test
	public void testBJCardValue(){
		assertEquals(4,hand.getBJCardValue(four));
		assertEquals(10,hand.getBJCardValue(jack));
		assertEquals(3,hand.getNumCards()+1);
	}
	@Test
	public void testBJHandValue(){
		assertEquals(14,hand.getBJHandValue());
		assertEquals(15,hand1.getBJHandValue());//ace == 1
		assertEquals(14,hand2.getBJHandValue());
		assertEquals(21,hand3.getBJHandValue());//ace == 11
	}
	@Test
	public void testCompareBJ(){
		assertEquals(-1,hand.compareBJ(hand1));
		assertEquals(0,hand.compareBJ(hand));
		assertEquals(-1,hand2.compareBJ(hand));
	}
}
