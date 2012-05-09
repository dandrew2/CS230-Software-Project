package edu.ycp.casino.shared.cardgame.poker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.cardgame.Card;

public class PokerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int hCards = 3;
	private static final int wCards = 5;
	private static final int cardW = 71;
	private static final int cardH = 96;
	private static final int padding = 10;
	private static final int bettingW = 20;
	private static final int betButtonH = 20;
	private static final int playerHandSize = 2;
	private static final int betTokinW = 10;
	private static final int betTokinH = 10;
	private static final int allInH = 15;
	private static final int minBetH = 15;
	
	private boolean allIn;
	private boolean gameOverShown;
	private boolean showNewGameAlert;
	private Table table;
	private int betTokinY;
	private final int WIDTH = (bettingW)+(cardW*wCards)+(padding*(wCards+1));
	private final int HEIGHT = (cardH*hCards)+(padding*(hCards+1));
	private String message;
	
	// TODO: add fields to store state
	
	// constructor
	public PokerPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		newPokerGame();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleMouseClick(e);
			}
		});
	}
	
	private void newPokerGame(){
		allIn=false;
		gameOverShown=false;
		message="";
		table=new Table();
		resetBetTokin();
		showNewGameAlert=true;
	}
	
	private void handleMouseClick(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		if(showNewGameAlert)
			showNewGameAlert=false;
		if(gameOverShown){
			newPokerGame();
			repaint();
		}
		else if(x<bettingW){
			//Inside the bet bar
			if(y<HEIGHT-betButtonH){
				//not "fold" button
				if(y<HEIGHT-(betButtonH*2)){
					//not "bet" button
					if(y<HEIGHT-(betButtonH*3)){
						//not "check" button
						if(y<minBetH){
							//use min bet
							allIn=false;
							int bet=table.getPot().getMinBet();
							table.getCurrentPlayer().setHoldingBet(bet,table.getPot().getMinBet());
							betTokinY=y;
						}
						else if(y<HEIGHT-(betButtonH*3)-allInH-padding){
							//set percentage in the bet bar
							allIn=false;
							double percent=((y-minBetH)/(HEIGHT-(betButtonH*2.0)-allInH-padding-minBetH));
							table.getCurrentPlayer().setHoldingBetPercent(percent,table.getPot().getMinBet());
							betTokinY=y;
						}
						else if (y<HEIGHT-(betButtonH*3)-padding){
							//go all in
							allIn=true;
							int bet=table.getCurrentPlayer().getBalance();
							table.getCurrentPlayer().setHoldingBet(bet,table.getPot().getMinBet());
							betTokinY=y;
						}
					}
					else{
					//is check button
						if (table.getPot().getMinBet()==0){
							table.getPot().add(0);
							table.getCurrentPlayer().setHoldingBet(0,0);
							message="Player "+table.getCurrentPlayerNum()+" checks";
							table.iterateCurrentPlayer();
							resetBetTokin();
						}
						else
							message="Invalid bet.";
					}
				}
				else{
					//is bet button
					int bet=table.getCurrentPlayer().getHoldingBet();
					if(table.getCurrentPlayer().takeHoldingBet()){
						if(allIn)
							table.getPot().setMaxBet(bet);
						if (table.getPot().add(bet)){
							message="Player "+table.getCurrentPlayerNum()+" added $"+bet+" to the pot";
							table.iterateCurrentPlayer();
						}
						else
							message="Invalid bet.";
						allIn=false;
					}
					else
						message="Not enough funds.";
					resetBetTokin();
				}
			}
			else{
				//is fold button
				table.getCurrentPlayer().fold();
				message="Player "+table.getCurrentPlayerNum()+" folds.";
				if(table.nonFoldedPlayers()>0)
					table.iterateCurrentPlayer();
				else
					newPokerGame();
					
			}
		repaint();
		}
	}
	
	private void resetBetTokin(){
		allIn=false;
		int bet=table.getPot().getMinBet();
		table.getCurrentPlayer().setHoldingBet(bet,table.getPot().getMinBet());
		betTokinY=minBetH/2;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); // paint background
		
		//Drawing
		Font f=new Font(Font.SANS_SERIF,Font.BOLD,12);
		g.setFont(f);
		g.setColor(Color.WHITE);
		//Get space dimensions.
		//Betting bar
			//Betting bar line
			g.drawLine(bettingW, 0, bettingW, HEIGHT);
			//Betting bar buttons
			g.fillOval(0, (HEIGHT-(3*betButtonH)), bettingW, betButtonH);
			g.fillOval(0, (HEIGHT-(2*betButtonH)), bettingW, betButtonH);
			g.fillOval(0, (HEIGHT-betButtonH), bettingW, betButtonH);
			g.drawString("Check",bettingW+3,HEIGHT-((betButtonH*2)+3));
			g.drawString("Bet",bettingW+3,HEIGHT-(betButtonH+3));
			g.drawString("Fold",bettingW+3,(HEIGHT-3));
			//make minBet button
			g.setColor(Color.YELLOW);
			g.fillRect(0, 0, bettingW, minBetH);
			g.setColor(Color.WHITE);
			//Betting bar bet meter
			int[] triangleX={0,(bettingW/2),bettingW};
			int[] triangleY={(HEIGHT-((3*betButtonH)+allInH+padding)),minBetH,(HEIGHT-((3*betButtonH)+allInH+padding))};
			g.fillPolygon(triangleX, triangleY, 3);
			//make All In button
			g.setColor(Color.RED);
			g.fillRect(0, (HEIGHT-((3*betButtonH)+allInH+padding)), bettingW, allInH);
			g.setColor(Color.WHITE);
		//Hand section with bars inbetween cards
			g.drawLine(bettingW+(padding/2), HEIGHT-(cardH+(padding)), WIDTH-(padding/2), HEIGHT-(cardH+(padding)));
			for(int w=bettingW+(padding/2); w<WIDTH; w+=(cardW+padding))
				g.drawLine(w, HEIGHT-(2*(cardH+(padding/2))), w, HEIGHT-(cardH+padding));	
			//display current player's hand
			int x=WIDTH-(playerHandSize*(cardW+padding));
			int y=HEIGHT-(cardH+(padding/2));
			for(Card card : table.getCurrentPlayer().getHand().getCards()){
				paintCard(g,card,x,y);
				x+=(cardW+padding);
			}
		//display community cards
			x=bettingW+padding;
			y=HEIGHT-((2*cardH)+padding);
			for(Card card : table.getCommunity().getCards()){
				paintCard(g,card,x,y);
				x+=(cardW+padding);
			}
		//draw messages
			g.setColor(Color.RED);
			g.drawString(message,bettingW+padding,HEIGHT-(cardH-5));
			g.drawString("Player "+table.getCurrentPlayerNum()+"'s turn.",bettingW+padding+15,15);
			g.setColor(Color.GREEN);
			g.drawString("Wallet:  $"+table.getCurrentPlayer().getWallet().getBalance(),bettingW+padding+150,15);
			g.setColor(Color.RED);
			g.drawString("Pot: $"+table.getPot().getAmount(),WIDTH-100,15);
			g.drawString("Minimum Bet: $"+table.getPot().getMinBet(),WIDTH-125,30);
			g.drawString("Maximum Bet: $"+table.getPot().getMaxBet(),WIDTH-125,45);
		//make bet tokin
			g.setColor(Color.GREEN);
			g.fillOval(((bettingW-betTokinW)/2),(betTokinY-(betTokinH/2)),betTokinW,betTokinH);
			if(betTokinY>0){
				if(allIn){
					//draw "ALL IN!"
					g.drawString("ALL IN!",(bettingW+2),(betTokinY+3));
				}
				else{
					//draw current bet amount
					g.drawString("$"+table.getCurrentPlayer().getHoldingBet(),(bettingW+2),(betTokinY+3));
				}
			}
		//Check if new game
		if(showNewGameAlert){
			g.drawString("New Game!",bettingW+padding+160,100);
		}
		//Check if game is over.
		if(table.gameIsOver()){
			gameOverShown=true;
			//Draw "game over" text
			Player winner=table.getWinner();
			g.drawString("Game over.  Player "+winner.getHand().getOwner()+" wins $"+table.getPot().getAmount()+" with a "+winner.getHandType(),bettingW+padding+50,100);
			g.drawString("Click anywhere to start a new game.",bettingW+padding+75,115);
			winner.getWallet().addFunds(table.getPot().takeAll());
		}
	}
	private void paintCard(Graphics g,Card card,int x,int y){
		BufferedImage image;
		try {
			image = ImageIO.read(new File("war/cards/"+card.toImageFileName()+".png"));
			g.drawImage(image,x,y,null);
		}
	    catch (IOException ex) {
	    	g.setColor(Color.BLUE);
	    	g.fillRect(x, y, cardW, cardH);
	    	message=ex.toString();
	    }
	}
}
