package edu.ycp.casino.shared;

import edu.ycp.casino.client.SlotsViewGWT;


public class SlotsController {
	
		private Slots model;
		private SlotsViewGWT view;
		
		public SlotsController()
		{
		}
	
		public void spinHandler()
		{
			int bet = Integer.parseInt(view.getBettextBox().getText());
			
			model.spin();
			
			if(model.checkWin() == true)
			{
				model.getPlayer().getWallet().setBalance(model.getPlayer().getWallet().getBalance() + bet);
			}
			
			if(model.checkWin() == false)
			{
				model.getPlayer().getWallet().setBalance(model.getPlayer().getWallet().getBalance() - bet);
			}
			
			model.setChanged();
			model.notifyObservers();
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
