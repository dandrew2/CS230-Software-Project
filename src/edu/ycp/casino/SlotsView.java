package edu.ycp.casino;

import java.awt.Dimension;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;

import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Observer;
import edu.ycp.casino.shared.Slots;
import edu.ycp.casino.shared.SlotsController;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class SlotsView extends JPanel implements Observer {
	
	private Slots model;
	private SlotsController controller;
	
	private JButton btnSpin;
	
	private JLabel lblSlot0;
	private JLabel lblSlot1;
	private JLabel lblSlot2;
	private JLabel lblMoneyDisplay;
	private JTextField textFieldCurrentBet;
	
	public SlotsView() {

		setPreferredSize(new Dimension(500, 320));
		setLayout(null);
		
		btnSpin = new JButton("Spin");
		btnSpin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleSpinBottonClicked();
			}
		});
		btnSpin.setBounds(243, 286, 89, 23);
		add(btnSpin);
		
		JLabel lblCurrentMoney = new JLabel("Current Money:");
		lblCurrentMoney.setBounds(10, 288, 95, 19);
		add(lblCurrentMoney);
		
		lblSlot0 = new JLabel();
		lblSlot0.setBounds(96, 77, 73, 14);
		add(lblSlot0);
		
		lblSlot1 = new JLabel();
		lblSlot1.setBounds(189, 77, 73, 14);
		add(lblSlot1);
		
		
		lblSlot2 = new JLabel();
		lblSlot2.setBounds(299, 77, 73, 14);
		add(lblSlot2);
		
		JLabel lblCurrentBet = new JLabel("Current Bet:");
		lblCurrentBet.setBounds(10, 263, 76, 14);
		add(lblCurrentBet);
		
		lblMoneyDisplay = new JLabel("");
		lblMoneyDisplay.setBounds(96, 290, 46, 14);
		add(lblMoneyDisplay);
		
		textFieldCurrentBet = new JTextField();
		textFieldCurrentBet.setText("25");
		textFieldCurrentBet.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});
		textFieldCurrentBet.setBounds(96, 260, 86, 20);
		add(textFieldCurrentBet);
		textFieldCurrentBet.setColumns(10);
	}
	
	public void propertyChange(PropertyChangeEvent evt)
	{
		controller.assignPot(Integer.parseInt(textFieldCurrentBet.getText()));
		System.out.println("Passed 1");
	}
	
	
	public void setModel(Slots model)
	{
		this.model = model;
		model.addObserver(this);
	}
	
	public void setController(SlotsController controller)
	{
		this.controller = controller;
	}
	
	protected void handleSpinBottonClicked()
	{
		if(textFieldCurrentBet.getText() != "")
		{
			controller.assignPot(Integer.parseInt(textFieldCurrentBet.getText()));
		}else
		{
			controller.assignPot(Integer.parseInt("0"));
		}
		controller.spinHandler();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//System.out.println("updating...");
		
		lblSlot0.setText(model.getSlot()[0].toString());
		lblSlot1.setText(model.getSlot()[1].toString());
		lblSlot2.setText(model.getSlot()[2].toString());
		
		lblMoneyDisplay.setText(Integer.toString(model.getMoney()));		
	}
}
