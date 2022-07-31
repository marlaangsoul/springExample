package com.edu.di.car.mycar02;

public class Driver {

	public static void main(String[] args) {
		
		// KoreaTire kt = new KoreaTire();
		// AmericaTire at = new AmericaTire();
		
		Tire tire = new AmericaTire();
		//Tire tire = new KoreaTire();
		
		Car car = new Car(tire);
		System.out.println(car.getTireBrand());

	} // end - public static void main(String[] args)

} // end - public class Driver
