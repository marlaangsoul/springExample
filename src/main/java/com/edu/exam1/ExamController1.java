// 6.29.-30.
package com.edu.exam1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//--------------------------------------------------------------------------------------------------------
// public class ExamController1
// @Controller : ExamController1 클래스를 컨트롤러로 설정하는 애노테이션
// @RequestMapping("/exam01") : /exam01로 접근하는 URL을 처리하겠다는 것을 기술한 것이다.
//--------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/exam01")
public class ExamController1 {

	//--------------------------------------------------------------------------------------------------------
	// public void doA() : void타입 : return 타입이 없는 경우
	// localhost:8081/exam01/doA
	// void 타입의 경우 리턴 값이 없는 대신에 @RequestMapping에서 지정한 URL에 해당하는 VIEW의 이름을 전달한다.
	// 최종적으로 VIEW의 이름은 /exam01/doA
	//--------------------------------------------------------------------------------------------------------
	@RequestMapping("/doA")
	public void doA() {
		System.out.println("doA가 실행되었습니다.....");
	} // END - public void doA()
	
	//--------------------------------------------------------------------------------------------------------
	// public void doB() : void타입 : return 타입이 없는 경우
	//--------------------------------------------------------------------------------------------------------
	@RequestMapping("/doB")
	public void doBBBBB() {
	    System.out.println("doB가 실행되었습니다.....");
	} // END - public void doB()
	

} // end - public class ExamController1
