package edu.ycp.casino.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;


import edu.ycp.casino.client.MainMenuGWT.MainMenuEvents;
import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.Roulette;
import edu.ycp.casino.shared.Slots;
import edu.ycp.casino.shared.blackjack.Blackjack;
import edu.ycp.casino.shared.blackjack.BlackjackController;
import edu.ycp.casino.shared.cardgame.poker.Table;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Casino implements EntryPoint, MainMenuEvents, GameViewCallback {
	

	private MainMenuGWT mainMenu;
	private Widget currentView;
	
	private SlotsViewGWT slotsView;
	private Slots slotsModel;
	private SlotsController slotsController;
	
	private RouletteView rouletteView;
	private Roulette rouletteModel;
	private RouletteController rouletteController;
	
	private BlackjackViewGWT blackJackview;
	private Blackjack blackJackModel;
	private BlackjackController blackJackController;
	
	private PokerViewGWT pokerView;
	private Table pokerTable;
	private PokerController pokerController;

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		mainMenu = new MainMenuGWT();
		mainMenu.setCallback(this);
		Player player = new Player();
		player.getWallet().setBalance(1500);
		
		initSlotsView(player);
		initRouletteView(player);
		initBlackJackView(player);
		initPokerView(player);
		
		selectView(mainMenu);
	}
	
	
	//Methods to set up the 4 various MVC GUI's for games
	private void initSlotsView(Player p) {
		slotsModel = new Slots();
		slotsController = new SlotsController();
		slotsView = new SlotsViewGWT();
		
		slotsModel.setPlayer(p);
		slotsView.setModel(slotsModel);
		slotsController.setModel(slotsModel);
		slotsController.setView(slotsView);
		slotsView.setController(slotsController);
		slotsView.setCallback(this);
		slotsModel.spin();
		
		slotsView.update(slotsModel, null);
	}
	
	private void initRouletteView(Player p) {
		rouletteView = new RouletteView();
		rouletteModel = new Roulette();			
		rouletteController = new RouletteController();	
		
		rouletteModel.setPlayer(p);
		rouletteView.setModel(rouletteModel); 			
		rouletteController.setModel(rouletteModel);			
		rouletteController.setView(rouletteView); 			
		rouletteView.setController(rouletteController);
		rouletteView.setCallback(this);
		
	}
	
	private void initBlackJackView(Player p)
	{
		blackJackModel = new Blackjack();
		blackJackview = new BlackjackViewGWT();
		blackJackController = new BlackjackController();
		
		blackJackview.setModel(blackJackModel);
		blackJackController.setModel(blackJackModel);
		blackJackController.setView(blackJackview);
		blackJackview.setController(blackJackController);
		blackJackview.setCallback(this);
	}
	
	private void initPokerView(Player p)
	{
		
		ArrayList<Player> players=new ArrayList<Player>();
		players.add(p);
		pokerTable = new Table(players);
		pokerView= new PokerViewGWT(pokerTable);
		pokerController = new PokerController(pokerTable,pokerView);
		
		
		pokerView.setController(pokerController);
		pokerView.setCallback(this);
		
		pokerView.update(pokerTable, null);
	}

	
	//Method to change view currently being displayed
	private void selectView(Widget view) {
		if (currentView != null) {
			RootLayoutPanel.get().remove(currentView);
		}
		RootLayoutPanel.get().add(view);
		currentView = view;
	}
	
	
	//Methods that call changeView() for their particular view
	@Override
	public void slotsChosen() {
		selectView(slotsView);
		slotsView.update(slotsModel, null);
	}

	@Override
	public void rouletteChosen() {
		selectView(rouletteView);
		rouletteView.update(rouletteModel, null);
	}

	@Override
	public void pokerChosen() {
		selectView(pokerView);
		pokerView.update(pokerTable, null);
		pokerView.reDraw();
	}

	@Override
	public void blackjackChosen() {
		selectView(blackJackview);
		blackJackview.update(blackJackModel, null);
	}

	@Override
	public void chooseMainMenu() {
		// TODO Auto-generated method stub
		selectView(mainMenu);
		System.out.print("Change view");
	}
}
