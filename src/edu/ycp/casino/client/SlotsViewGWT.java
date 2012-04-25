package edu.ycp.casino.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.InlineLabel;

import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Observer;
import edu.ycp.casino.shared.Slots;

public class SlotsViewGWT extends Composite implements Observer {
	private Slots model;

	public SlotsViewGWT() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		
		
		initWidget(layoutPanel);
		
		ListBox comboBox = new ListBox();
		layoutPanel.add(comboBox);
		layoutPanel.setWidgetLeftWidth(comboBox, 14.0, Unit.PX, 122.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(comboBox, 101.0, Unit.PX, 26.0, Unit.PX);
		
		ListBox comboBox_1 = new ListBox();
		layoutPanel.add(comboBox_1);
		layoutPanel.setWidgetLeftWidth(comboBox_1, 142.0, Unit.PX, 122.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(comboBox_1, 101.0, Unit.PX, 26.0, Unit.PX);
		
		ListBox comboBox_2 = new ListBox();
		layoutPanel.add(comboBox_2);
		layoutPanel.setWidgetLeftWidth(comboBox_2, 270.0, Unit.PX, 122.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(comboBox_2, 101.0, Unit.PX, 26.0, Unit.PX);
		
		InlineLabel nlnlblSlot = new InlineLabel("Slot 1:");
		layoutPanel.add(nlnlblSlot);
		layoutPanel.setWidgetLeftWidth(nlnlblSlot, 14.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(nlnlblSlot, 77.0, Unit.PX, 18.0, Unit.PX);
	}
	
	@Override
	public void update(Observable obj, Object hint) {
		// TODO Auto-generated method stub
		
	}

	public void setModel(Slots model) {
		this.model = model;
		model.addObserver(this);
	}
}
