package edu.ycp.casino.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import edu.ycp.casino.shared.Slots;
import edu.ycp.casino.shared.SlotsController;
import edu.ycp.casino.shared.blackjack.Blackjack;
import edu.ycp.casino.shared.blackjack.BlackjackController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CasinoBackup implements EntryPoint {
	
	private static boolean SHOW_SLOTS = false;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
			/*Slots model = new Slots();
			SlotsController controller = new SlotsController();
			SlotsViewGWT slotsView = new SlotsViewGWT();
			slotsView.setModel(model);
			controller.setModel(model);
			controller.setView(slotsView);
			slotsView.setController(controller);
			model.spin();
			slotsView.update(model, null);
			RootLayoutPanel.get().add(slotsView);
			*/
			Blackjack model = new Blackjack();
			BlackjackViewGWT view = new BlackjackViewGWT();
			BlackjackController controller = new BlackjackController();
			view.setModel(model);
			controller.setModel(model);
			controller.setView(view);
			view.setController(controller);
			//model.play();
			view.update(model, null);
			RootLayoutPanel.get().add(view);

	}
}
