// 7.1.
package com.edu.di.car.mycar01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

//---------------------------------------------------------------------------------------------------
// JUnit Test
//---------------------------------------------------------------------------------------------------
public class CarTest {
	
	@Test // 테스트 버전 사용시에 사용
	void test() {
		System.out.println("JUnit Test Program 입니다.");
	}

	//------------------------------------------------------------------------------------------------------
	@Test
	void OneToHundred() {
		int result=0;
		for(int i = 1; i <= 100; i++) {
			result += i;
		}
		System.out.println("1부터 100까지 더한 값은 " + result + "입니다.");
	}
	
	//------------------------------------------------------------------------------------------------------
	@Test
	void 자동차에_장착된_타이어브랜드_알아내기() {
		Car car = new Car();
		System.out.println(car.getTireBrand());
		
		assertEquals("AAAA", car.getTireBrand());
	}
	
} // end - public class CarTest
