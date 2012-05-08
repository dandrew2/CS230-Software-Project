package edu.ycp.casino.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;


import edu.ycp.casino.client.MainMenuGWT.MainMenuEvents;
import edu.ycp.casino.shared.Player;
import edu.ycp.casino.shared.Roulette;
import edu.ycp.casino.shared.Slots;
import edu.ycp.casino.shared.SlotsController;
import edu.ycp.casino.shared.blackjack.Blackjack;
import edu.ycp.casino.shared.blackjack.BlackjackController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Casino implements EntryPoint, MainMenuEvents, GameViewCallback {
	

	private MainMenuGWT mainMenu;
	private Widget currentView;
	
	private SlotsViewGWT slotsView;
	private RouletteView rouletteView;
	private BlackjackViewGWT blackJackView;

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		mainMenu = new MainMenuGWT();
		mainMenu.setCallback(this);
		Player player = new Player();
		player.getWallet().setBalance(1500);
		
		initSlotsView(player);
		initBlackJackView();
		initRouletteView();
		
		selectView(mainMenu);
	}
	
	
	//Methods to set up the 4 various MVC GUI's for games
	private void initSlotsView(Player p) {
		Slots model = new Slots();
		SlotsController controller = new SlotsController();
		slotsView = new SlotsViewGWT();
		
		model.setPlayer(p);
		slotsView.setModel(model);
		controller.setModel(model);
		controller.setView(slotsView);
		slotsView.setController(controller);
		slotsView.setCallback(this);
		model.spin();
		
		slotsView.update(model, null);
	}
	
	private void initRouletteView() {
		rouletteView = new RouletteView();
		Roulette model = new Roulette();			
		RouletteController controller = new RouletteController();	
		
		rouletteView.setModel(model); 			
		controller.setModel(model);			
		controller.setView(rouletteView); 			
		rouletteView.setController(controller);
	}
	
	private void initBlackJackView()
	{
		Blackjack model = new Blackjack();
		blackJackView = new BlackjackViewGWT();
		BlackjackController controller = new BlackjackController();
		
		blackJackView.setModel(model);
		controller.setModel(model);
		controller.setView(blackJackView);
		blackJackView.setController(controller);
		blackJackView.update(model, null);
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
	}

	@Override
	public void rouletteChosen() {
		selectView(rouletteView);
	}

	@Override
	public void pokerChosen() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void blackjackChosen() {
		selectView(blackJackView);
	}

	@Override
	public void chooseMainMenu() {
		// TODO Auto-generated method stub
		selectView(mainMenu);
	}
}
