package com.edu.bmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//----------------------------------------------------------------------------------------------------------
// public class BMIMain
//----------------------------------------------------------------------------------------------------------
public class BMIMain {

	//----------------------------------------------------------------------------------------------------------
	// public static void main(String[] args)
	//----------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// classpath => src/main/resources를 가리킨다.
		String conf = "classpath:ApplicationContext.xml";
		
		// 스프링 컨테이너가 형성된다.
		ApplicationContext ctx = new GenericXmlApplicationContext(conf);
		
		// 스프링 컨테이너에서 컴포넌트를 가져온다.
		MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
		myInfo.getInfo();
		
		// 스프링 컨테이너를 닫아준다.
		// ctx.close;

	}// end - public static void main(String[] args)

}// end - public class BMIMain
