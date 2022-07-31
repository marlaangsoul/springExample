package com.edu.coffeeshop.coffee6;

//--------------------------------------------------------------------------------------------------------------
// setter 메서드를 통한 주입
//--------------------------------------------------------------------------------------------------------------
public class Coffee {
   private Americano americano;
   
   public void setCoffee(Americano ame) {
	   americano = ame;
   }
   public void coffeeType() {
	   System.out.println(americano.getName());
   }
}
