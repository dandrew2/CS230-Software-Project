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
public class Casino implements EntryPoint, MainMenuEvents {
	
	private static boolean SHOW_SLOTS = true;
	
	private MainMenuGWT mainMenu;
	private Widget currentView;
	private SlotsViewGWT slotsView;
	private RouletteView rouletteView;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		/*
		if (SHOW_SLOTS) {
			Slots model = new Slots();
			SlotsController controller = new SlotsController();
			SlotsViewGWT slotsView = new SlotsViewGWT();
			
			slotsView.setModel(model);
			controller.setModel(model);
			controller.setView(slotsView);
			slotsView.setController(controller);
			model.spin();
			
			slotsView.update(model, null);
			
			RootLayoutPanel.get().add(slotsView);
		} else {
			RouletteView rouletteView = new RouletteView();
			
			RootLayoutPanel.get().add(rouletteView);
			
			rouletteView.drawOnCanvas();
		}
		*/
		mainMenu = new MainMenuGWT();
		mainMenu.setCallback(this);
		
		initSlotsView();
		//initRouletteView();
		
		selectView(mainMenu);
	}
	
	private void initSlotsView() {
		Slots model = new Slots();
		SlotsController controller = new SlotsController();
		slotsView = new SlotsViewGWT();
		
		slotsView.setModel(model);
		controller.setModel(model);
		controller.setView(slotsView);
		slotsView.setController(controller);
		model.spin();
		
		slotsView.update(model, null);
	}
	
	private void initRouletteView() {
		rouletteView = new RouletteView();
		
		RootLayoutPanel.get().add(rouletteView);
		
		rouletteView.drawOnCanvas();
	}

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
}
