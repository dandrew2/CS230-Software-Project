package edu.ycp.casino.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.ycp.casino.client.MainMenuGWT.MainMenuEvents;
import edu.ycp.casino.shared.Slots;
import edu.ycp.casino.shared.SlotsController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Casino implements EntryPoint, MainMenuEvents, GameViewCallback {
	
	private MainMenuGWT mainMenu;
	private Widget currentView;
	private SlotsViewGWT slotsView;
	private RouletteView rouletteView;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		mainMenu = new MainMenuGWT();
		mainMenu.setCallback(this);
		
		initSlotsView();
		//initRouletteView();
		
		selectView(mainMenu);
	}
	
	
	
	
	
	//Methods to set up the 4 various MVC GUI's for games
	private void initSlotsView() {
		Slots model = new Slots();
		SlotsController controller = new SlotsController();
		slotsView = new SlotsViewGWT();
		
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
		
		RootLayoutPanel.get().add(rouletteView);
		
		rouletteView.drawOnCanvas();
	}

	
	
	
	//Method to change view currently being displayed
	private void selectView(Widget view) {
		if (currentView != null) {
			RootLayoutPanel.get().remove(currentView);
		}
		RootLayoutPanel.get().add(view);
		currentView = view;
	}

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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void chooseMainMenu() {
		// TODO Auto-generated method stub
		selectView(mainMenu);
	}
}
