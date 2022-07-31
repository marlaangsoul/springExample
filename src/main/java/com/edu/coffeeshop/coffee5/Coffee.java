package com.edu.coffeeshop.coffee5;

//--------------------------------------------------------------------------------------------------------------
// 생성자를 통해서 주입을 받는다. (인터페이스를 사용해서)
//--------------------------------------------------------------------------------------------------------------
public class Coffee {

	private Americano americano; 
	
	public Coffee(Americano ame) {
		americano = ame;
	}
	public void coffeeType() {
		System.out.println(americano.getName());
		
	}
}
