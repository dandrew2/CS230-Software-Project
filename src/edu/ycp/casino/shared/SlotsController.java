package edu.ycp.casino.shared;

import edu.ycp.casino.client.SlotsViewGWT;


public class SlotsController {
	
		Slots model;
		SlotsViewGWT view;
		
		public SlotsController()
		{
		}
		
		public void assignPot(int m)
		{
			model.setBet(m);
		}
	
	
		public void spinHandler()
		{
			model.spin();
		
			if(model.checkWin() == true)
			{
				model.setMoney(model.getMoney() + model.getBet());
			}
			
			if(model.checkWin() == false)
			{
				model.setMoney(model.getMoney() - model.getBet());
			}
			
			System.out.println("Test1");
		}
		
		public void setModel(Slots model)
		{
			this.model = model;
		}
		
		public void setView(SlotsViewGWT slotsView)
		{
			this.view = slotsView;
		}
		
}
