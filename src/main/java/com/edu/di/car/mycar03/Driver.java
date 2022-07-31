package com.edu.di.car.mycar03;

public class Driver {

	public static void main(String[] args) {
		
		// Tire tire = new CanadaTire();
		Tire tire = new EnglandTire();
		Car car = new Car();
		// 속성을 통해서 의존성을 주입한다.
		car.setTire(tire);

		System.out.println(car.getTireBrand());
	} // end - public static void main(String[] args)

} // end - public class Driver
