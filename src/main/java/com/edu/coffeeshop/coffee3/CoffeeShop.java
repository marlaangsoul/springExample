package com.edu.coffeeshop.coffee3;

public class CoffeeShop {

	public static void main(String[] args) {
		IceAmericano ice = new IceAmericano();
		HotAmericano hot = new HotAmericano();
		
		Coffee coffee1 = new Coffee();
		coffee1.setCoffee(ice);
        coffee1.coffeeType();
        
        Coffee coffee2 = new Coffee();
		coffee1.setCoffee(hot);
        coffee1.coffeeType();
	}

}
