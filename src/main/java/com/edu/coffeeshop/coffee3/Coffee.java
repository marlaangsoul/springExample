package com.edu.coffeeshop.coffee3;

public class Coffee {

	private Americano americano;
	
	public void setCoffee(Americano americano) {  // set메서드
		this.americano = americano;
	}
	public void coffeeType() {
		System.out.println(americano.getName());
	}
}
