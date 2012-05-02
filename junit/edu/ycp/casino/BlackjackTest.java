package edu.ycp.casino;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.blackjack.Blackjack;
import edu.ycp.casino.shared.cardgame.Card;

import edu.ycp.casino.shared.cardgame.Rank;
import edu.ycp.casino.shared.cardgame.Suit;

public class BlackjackTest{
	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;
	private Player p5;
	private Player dealer;//test dealer moves
	private int gold;
	private Card ace;
	private Card ten;
	private Card jack;
	private Card five;
	private Blackjack game;
	@Before
	public void setUp(){  
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		p4 = new Player();
		p5 = new Player();
		dealer = new Player();
		gold = 100;
		//Cards
		ace = new Card(Suit.CLUBS,Rank.ACE);
		jack = new Card(Suit.CLUBS,Rank.JACK);
		five = new Card(Suit.DIAMONDS,Rank.FIVE);
		ten = new Card (Suit.HEARTS, Rank.TEN);

		//hand value 21 is blackjack
		p1.getHand().addCard(ace);
		p1.getHand().addCard(jack);
		
		//hand value of 30
		p2.getHand().addCard(jack);
		p2.getHand().addCard(jack);
		p2.getHand().addCard(jack);
		
		//hand value of 20
		p3.getHand().addCard(jack);
		p3.getHand().addCard(jack);
		
		//hand value of 20
		p4.getHand().addCard(jack);
		p4.getHand().addCard(five);
		p4.getHand().addCard(five);
		
		//hand value of 21 but not black jack
		p5.getHand().addCard(ace);
		p5.getHand().addCard(five);
		p5.getHand().addCard(five);
		
		//hand value of 16
		dealer.getHand().addCard(ten);
		dealer.getHand().addCard(five);
		dealer.getHand().addCard(ace);
		
	}


	@Test
	public void testCheckBJ() throws Exception{
		game = new Blackjack();
		assertEquals(true,game.checkBJ(p1));
		assertEquals(false,game.checkBJ(p2));
		assertEquals(false,game.checkBJ(p3));
		assertEquals(false,game.checkBJ(p5));
	}
	@Test
	public void testCheckWin() throws Exception{
		game = new Blackjack();
		assertEquals(false,game.checkWin(p2, p1));
		assertEquals(true,game.checkWin(p3,p4));
	}
	@Test
	public void testCheckTie() throws Exception{
		game = new Blackjack();
		assertEquals(false,game.checkTie(p1, p5));
		assertEquals(true,game.checkTie(p3, p3));
		assertEquals(false,game.checkTie(p2, p2));//because it is a bust
		assertEquals(false,game.checkTie(p3, p4));
	}
	@Test
	public void testCheckBust() throws Exception{
		game = new Blackjack();
		assertEquals(false,game.checkBust(p1));
		assertEquals(true,game.checkBust(p2));
	}
	@Test
	public void testCheckOut() throws Exception{
		game = new Blackjack();
		assertEquals(-100,game.checkOut(p2, p1, gold));//busted
		assertEquals(200,game.checkOut(p1, p2, gold));//blackjack
		assertEquals(0,game.checkOut(p1, p1, gold));//tie
		assertEquals(-100,game.checkOut(p3, p2, gold));//lose
		assertEquals(100,game.checkOut(p3, p4, gold));//win
		
	}
	@Test
	//test dealer moves
	public void testdealerTurn(){
		game = new Blackjack();
		game.dealerTurn(dealer);//dealer should dealer one more card to reach 17 or higher
		assertEquals(4,dealer.getHand().getNumCards());
		game.dealerTurn(p1);
		assertEquals(2,p1.getHand().getNumCards());//p1 is already at 21 so dealer should not add more cards
	}
	
}

