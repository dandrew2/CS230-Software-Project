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
	
	private boolean gameOverShown=false;
	private Table table;
	private int betTokinY=0;
	private final int WIDTH = (bettingW)+(cardW*wCards)+(padding*(wCards+1));
	private final int HEIGHT = (cardH*hCards)+(padding*(hCards+1));
	private String message=" ";
	
	// TODO: add fields to store state
	
	// constructor
	public PokerPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		table=new Table();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleMouseClick(e);
			}
		});
	}
	
	
	private void handleMouseClick(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		if(gameOverShown){
			table.newGame();
		}
		if(x<bettingW){
			//Inside the bet bar
			if(y<HEIGHT-betButtonH){
				//not "bet" button
				if(y<HEIGHT-(betButtonH*2)){
					//not "check" button
					//set percentage in the bet bar
					double percent=(y/(HEIGHT-(betButtonH*2.0)));
					table.getCurrentPlayer().setHoldingBetPercent(percent,table.getPot().getMinBet());
					betTokinY=y;
				}
				else if (table.getPot().getMinBet()==0){
				//is check button
					table.getCurrentPlayer().setHoldingBet(0,table.getPot().getMinBet());
					table.getPot().add(table.getCurrentPlayer().takeHoldingBet(table.getPot().getMinBet()));
					table.iterateCurrentPlayer();
					betTokinY=0;
					message="Player "+table.getCurrentPlayerNum()+" checks";
				}
			}
			else{
				//is bet button
				int bet=table.getCurrentPlayer().takeHoldingBet(table.getPot().getMinBet());
				table.getPot().add(bet);
				table.iterateCurrentPlayer();
				betTokinY=0;
				message="Player "+table.getCurrentPlayerNum()+" added $"+bet+" to the pot";
				bet=0;
			}
		repaint();
		}
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
			g.fillOval(0, (HEIGHT-(2*betButtonH)), bettingW, betButtonH);
			g.fillOval(0, (HEIGHT-betButtonH), bettingW, betButtonH);
			g.drawString("Check",bettingW+3,HEIGHT-(betButtonH+3));
			g.drawString("Bet",bettingW+3,(HEIGHT-3));
			//Betting bar bet meter
			int[] triangleX={0,(bettingW/2),bettingW};
			int[] triangleY={(HEIGHT-((2*betButtonH)+padding)),0,(HEIGHT-((2*betButtonH)+padding))};
			g.fillPolygon(triangleX, triangleY, 3);
			//make bet tokin
			g.setColor(Color.GREEN);
			g.fillOval(((bettingW-betTokinW)/2),(betTokinY-(betTokinH/2)),betTokinW,betTokinH);
			if(betTokinY>0){
				//draw current bet amount
				g.drawString("$"+table.getCurrentPlayer().getHoldingBet(),(bettingW+2),(betTokinY+3));
			}
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
			g.drawString("Player "+table.getCurrentPlayerNum()+"'s turn.",bettingW+padding,30);
			g.drawString("Pot: $"+table.getPot().getAmount(),WIDTH-100,15);
			g.drawString("Minimum Bet: $"+table.getPot().getMinBet(),WIDTH-125,30);
		//Check if game is over.
		if(table.gameIsOver()){
			gameOverShown=true;
			//Draw "game over" text
			Player winner=table.getWinner();
			g.drawString("Game over.  Player "+winner.getHand().getOwner()+" wins $"+table.getPot().getAmount()+" with a "+winner.getHandType(),bettingW+padding,15);
			winner.getWallet().addFunds(table.getPot().takeAll());
		}
	}
	private void paintCard(Graphics g,Card card,int x,int y){
		BufferedImage image;
		try {
			image = ImageIO.read(new File("H:/cards/"+card.toImageFileName()+".png"));
			g.drawImage(image,x,y,null);
		}
	    catch (IOException ex) {
	    	g.setColor(Color.BLUE);
	    	g.fillRect(x, y, cardW, cardH);
	    	message=ex.toString();
	    }
	}
}
