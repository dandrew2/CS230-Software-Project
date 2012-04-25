package edu.ycp.casino.shared;

import java.util.ArrayList;
import java.util.List;

public class Observable {
	private List<Observer> observerList;
	private boolean changed;
	
	public Observable() {
		this.observerList = new ArrayList<Observer>();
		this.changed = false;
	}
	
	public void addObserver(Observer observer) {
		observerList.add(observer);
	}
	
	public void setChanged() {
		this.changed = true;
	}
	
	public void notifyObservers() {
		if (changed) {
			for (Observer observer : observerList) {
				observer.update(this, null);
			}
			changed = false;
		}
	}
}
