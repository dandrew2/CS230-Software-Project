package edu.ycp.casino;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.casino.shared.Game;
import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.Wallet;
import edu.ycp.casino.shared.cardgame.Hand;

public class PlayerTest {
	private Player p1;
	private Player p2;
	private int anti;
	private Hand hand;
	private Wallet w;
	private Game game;
	@Before
	public void setUp(){
		//hand
		hand = new Hand();
		//player
		p1 = new Player(1000,1);//funds,seat
		p2 = new Player(1010,hand,2);//fund,hand,seat
		w = new Wallet();
		//anti
		anti = 10;
		
	}
	@Test//test seat of player
	public void testSeat() throws Exception{
		game = new Game();
		assertEquals(1,p1.getSeatNum());
		assertEquals(2,p2.getSeatNum());
	}
	@Test//test balance of player
	public void TestBalance() throws Exception{
		game = new Game();
		assertEquals(1000,p1.getBalance());
		assertEquals(1010,p2.getBalance());
	}
	@Test//test hand of player
	public void TestHand() throws Exception{
		game = new Game();
		assertEquals(hand, p2.getHand());
	}
	@Test//test anti
	public void TestAnti() throws Exception{
		game = new Game();
		assertEquals(10,p1.getAnti(anti));
	}
	@Test//test bet
	public void TestBet() throws Exception{
		game = new Game();
		assertEquals(5,p1.getBet());
	}

}
