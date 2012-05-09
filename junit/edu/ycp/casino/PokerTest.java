package edu.ycp.casino;

import java.util.ArrayList;

import org.junit.Test;

import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.cardgame.Card;
import edu.ycp.casino.shared.cardgame.Hand;
import edu.ycp.casino.shared.cardgame.HandType;
import edu.ycp.casino.shared.cardgame.Rank;
import edu.ycp.casino.shared.cardgame.Suit;
import edu.ycp.casino.shared.cardgame.poker.HandComperator;
import edu.ycp.casino.shared.cardgame.poker.Table;
import static org.junit.Assert.*;

public class PokerTest {
	static final int PLAYERSNUM=5;
	Table table;
	HandComperator comperator;
	ArrayList<Player> players;
	Player player;
	
	public void setup(){
		players=new ArrayList<Player>();
		for(int x=0; x<PLAYERSNUM; x++)
			players.add(new Player());
		player=new Player();
		comperator=new HandComperator();
	}
	
	@Test
	public void testPlayers() throws Exception{
		setup();
		this.table=new Table(players);
		assertEquals(PLAYERSNUM,table.getPlayers().size());
	}
	@Test
	public void testFillSeats() throws Exception{
		setup();
		ArrayList<Player> gameOfOne=new ArrayList<Player>();
		gameOfOne.add(player);
		this.table=new Table(gameOfOne);
		assertEquals(1,table.getPlayers().size());
		table.fillEmptySeats();
		assertEquals(5,table.getPlayers().size());
	}
	@Test
	public void testComperator() throws Exception{
		setup();
		Hand com=new Hand();
		ArrayList<Player> comparePlayers = new ArrayList<Player>();
		Player p1=new Player();
		Player p2=new Player();
		Player p3=new Player();
		Player p4=new Player();
		Player p5=new Player();
		
		com.addCard(new Card(Suit.SPADES,Rank.ACE));
		com.addCard(new Card(Suit.DIAMONDS,Rank.ACE));
		com.addCard(new Card(Suit.SPADES,Rank.TEN));
		com.addCard(new Card(Suit.CLUBS,Rank.TWO));
		com.addCard(new Card(Suit.SPADES,Rank.FIVE));
		
		p1.getHand().addCard(new Card(Suit.CLUBS,Rank.ACE));
		p1.getHand().addCard(new Card(Suit.HEARTS,Rank.ACE));
		comparePlayers.add(p1);
		
		p2.getHand().addCard(new Card(Suit.HEARTS,Rank.FIVE));
		p2.getHand().addCard(new Card(Suit.SPADES,Rank.TEN));
		comparePlayers.add(p2);
		
		p3.getHand().addCard(new Card(Suit.HEARTS,Rank.TWO));
		p3.getHand().addCard(new Card(Suit.SPADES,Rank.JACK));
		comparePlayers.add(p3);
		
		p4.getHand().addCard(new Card(Suit.HEARTS,Rank.TEN));
		p4.getHand().addCard(new Card(Suit.SPADES,Rank.JACK));
		comparePlayers.add(p4);
		
		p5.getHand().addCard(new Card(Suit.HEARTS,Rank.THREE));
		p5.getHand().addCard(new Card(Suit.SPADES,Rank.JACK));
		comparePlayers.add(p5);
		
		assertEquals(p1,comperator.getWinner(com, comparePlayers));
	}
	@Test
	public void testHandParser() throws Exception{
		Hand testHand=new Hand();
		testHand.addCard(new Card(Suit.SPADES,Rank.ACE));
		testHand.addCard(new Card(Suit.HEARTS,Rank.ACE));
		testHand.addCard(new Card(Suit.CLUBS,Rank.ACE));
		testHand.addCard(new Card(Suit.DIAMONDS,Rank.ACE));
		testHand.addCard(new Card(Suit.SPADES,Rank.TWO));
		
		assertEquals(HandType.FOUROFAKIND,testHand.parseHandType());
	}
}