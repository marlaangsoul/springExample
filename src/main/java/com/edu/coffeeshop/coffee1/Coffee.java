package com.edu.coffeeshop.coffee1;

public class Coffee {

	private HotAmericano ame;
	private IceAmericano ime;
	
	public Coffee() {
		//ame = new HotAmericano();
		ime = new IceAmericano();	
	}
	public void coffeeType() {
		//System.out.println(ame.getName());
		System.out.println(ime.getName());
	}
}
