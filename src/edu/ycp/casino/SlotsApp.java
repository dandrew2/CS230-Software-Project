package edu.ycp.casino;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.ycp.casino.shared.Slots;
import edu.ycp.casino.shared.SlotsController;

public class SlotsApp {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
		
			public void run()
			{
				Slots model = new Slots();
				SlotsController controller = new SlotsController();
				
				SlotsView view = new SlotsView();
				view.setModel(model);
				controller.setModel(model);
				view.setController(controller);
				
				
				view.update(null);
				
				JFrame frame = new JFrame();
				frame.setTitle("Slots App");
				frame.add(view);
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
   }
}
