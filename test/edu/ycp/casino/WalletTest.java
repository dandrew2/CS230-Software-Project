package edu.ycp.casino;

import edu.ycp.casino.shared.Wallet;
import junit.framework.TestCase;

public class WalletTest extends TestCase {

	private Wallet fiveHundred;
	private Wallet thousand; 
	
	@Override
	protected void setUp() throws Exception{
		fiveHundred = new Wallet(); 
		thousand = new Wallet(); 
	}
	
	
	public void testAddFunds() throws Exception{
		fiveHundred.addFunds(500); 
		assertEquals(500, fiveHundred.getBalance());
	}
	
	public void testTakeBet() throws Exception{
		thousand.addFunds(1000); 
		thousand.takeBet(100);
		assertEquals(900, thousand.getBalance());
	}

}
