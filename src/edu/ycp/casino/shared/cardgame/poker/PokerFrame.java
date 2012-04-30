package edu.ycp.casino.shared.cardgame.poker;

import javax.swing.JFrame;

// You won't need to modify this class.
public class PokerFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		PokerFrame frame = new PokerFrame();
		frame.add(new PokerPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Texas Hold Em");
		frame.setVisible(true);
	}
}
