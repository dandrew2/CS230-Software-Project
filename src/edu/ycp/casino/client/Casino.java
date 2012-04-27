package edu.ycp.casino.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import edu.ycp.casino.shared.Slots;
import edu.ycp.casino.shared.SlotsController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Casino implements EntryPoint {
	
	private static boolean SHOW_SLOTS = false;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
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
	}
}
