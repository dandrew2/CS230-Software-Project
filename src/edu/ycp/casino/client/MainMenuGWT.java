package edu.ycp.casino.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;

import edu.ycp.casino.shared.Observable;
import edu.ycp.casino.shared.Observer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Image;

public class MainMenuGWT extends Composite implements Observer {
	public interface MainMenuEvents {
		public void slotsChosen();
		public void rouletteChosen();
		public void pokerChosen();
		public void blackjackChosen();
	}
	
	private MainMenuEvents callback;
	
	private PushButton pshbtnSlots;
	private PushButton pshbtnRoulette;
	private PushButton pshbtnPoker;
	private PushButton pshbtnBlackJack;
	
	public MainMenuGWT() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		
		initWidget(layoutPanel);
		layoutPanel.setSize("750px", "750px");
		
		Image imageBackground = new Image("casinoImages/feltEmbed.png");
		layoutPanel.add(imageBackground);
		layoutPanel.setWidgetLeftWidth(imageBackground, 0.0, Unit.PX, 750.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageBackground, 0.0, Unit.PX, 870.0, Unit.PX);	
		
		//Everything involved for the Slots Button
		pshbtnSlots = new PushButton("Slots");
		Image imageDown = new Image("mainMenuImages/mainMenuButtons/pressed/slotsPressed.png");
		pshbtnSlots.getDownFace().setImage(imageDown);
		imageDown.setSize("579px", "126px");
		Image image = new Image("casinoImages/mainMenuButtons/default/slots.png");
		image.setSize("480px", "99px");
		pshbtnSlots.getUpFace().setImage(image);
		pshbtnSlots.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runSlotsClick();
			}
		});
		layoutPanel.add(pshbtnSlots);
		pshbtnSlots.setSize("722", "149");
		layoutPanel.setWidgetLeftWidth(pshbtnSlots, 82.0, Unit.PX, 547.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(pshbtnSlots, 19.0, Unit.PX, 88.0, Unit.PX);
		
		
		//Everything involved for the Roulette Button
		pshbtnRoulette = new PushButton("Roulette");
		Image image_1Down = new Image("mainMenuImages/mainMenuButtons/pressed/roulettePressed.png");
		pshbtnRoulette.getDownFace().setImage(image_1Down);
		image_1Down.setSize("581px", "128px");
		Image image_1 = new Image("mainMenuImages/mainMenuButtons/default/roulette.png");
		image_1.setSize("426px", "54px");
		pshbtnRoulette.getUpFace().setImage(image_1);
		pshbtnRoulette.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runRouletteClick();
			}
		});
		layoutPanel.add(pshbtnRoulette);
		layoutPanel.setWidgetLeftWidth(pshbtnRoulette, 84.0, Unit.PX, 547.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(pshbtnRoulette, 120.0, Unit.PX, 88.0, Unit.PX);
		
		
		//Everything involved for the Poker Button
		pshbtnPoker = new PushButton("Poker");
		Image image_2 = new Image("mainMenuImages/mainMenuButtons/default/poker.png");
		image_2.setSize("578px", "126px");
		pshbtnPoker.getUpFace().setImage(image_2);
		Image image2Down = new Image("mainMenuImages/mainMenuButtons/pressed/pokerPressed.png");
		pshbtnPoker.getDownFace().setImage(image2Down);
		image2Down.setSize("578px", "126px");
		pshbtnPoker.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runPokerClick();
			}
		});
		layoutPanel.add(pshbtnPoker);
		layoutPanel.setWidgetLeftWidth(pshbtnPoker, 82.0, Unit.PX, 547.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(pshbtnPoker, 510.0, Unit.PX, 88.0, Unit.PX);
		
		
		//Everything involved for the Black Jack Button
		pshbtnBlackJack = new PushButton("Black Jack");
		Image image3Down = new Image("mainMenuImages/mainMenuButtons/pressed/blackJackPressed.png");
		pshbtnBlackJack.getDownFace().setImage(image3Down);
		image3Down.setSize("577px", "125px");
		Image image_3 = new Image("mainMenuImages/mainMenuButtons/default/blackJack.png");
		image_3.setSize("577px", "125px");
		pshbtnBlackJack.getUpFace().setImage(image_3);
		pshbtnBlackJack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				runBlackJack();
			}
		});
		layoutPanel.add(pshbtnBlackJack);
		layoutPanel.setWidgetLeftWidth(pshbtnBlackJack, 82.0, Unit.PX, 547.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(pshbtnBlackJack, 604.0, Unit.PX, 88.0, Unit.PX);
	}
	
	public void setCallback(MainMenuEvents callback) {
		this.callback = callback;
	}

	public void runSlotsClick()
	{
		if (callback != null) {
			callback.slotsChosen();
		}
	}
	
	public void runPokerClick()
	{
		if (callback != null) {
			callback.pokerChosen();
		}
	}
	
	public void runRouletteClick()
	{
		if (callback != null) {
			callback.rouletteChosen();
		}
	}
	
	public void runBlackJack()
	{
		if (callback != null) {
			callback.blackjackChosen();
		}
	}
	
	@Override
	public void update(Observable obj, Object hint) {
		// TODO Auto-generated method stub
		
	}
}
