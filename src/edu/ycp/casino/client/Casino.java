package edu.ycp.casino.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Casino implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		SlotsViewGWT slotsView = new SlotsViewGWT();
		
		RootLayoutPanel.get().add(slotsView);
	}
}
