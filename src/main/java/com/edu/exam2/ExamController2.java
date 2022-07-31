//6.30. 
package com.edu.exam2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//--------------------------------------------------------------------------------------------------------
// public class ExamController2
//--------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/exam02")
public class ExamController2 {
	//--------------------------------------------------------------------------------------------------------
	// return 타입이 String인 경우
	// servlet-context.xml에 <context:component-scan base-package="com.edu.exam2" /> 추가해 줘야지 경로 찾아서 결과 화면 보여줌
	// @ModelAttribute("msg")는 주소창에서 msg라는 파라미터의 값을 가져온다.
	// http://localhost:8081/exam02/doC?msg=hello  - > Console 창에 hello가 나옴
	// 이렇게 주소창에 파라미터가 적혀있는 경우에 자동적으로 msg의 값이 hello를 가져오게 된다.
	//     request.getParameter("msg")를 사용하지 않아도 된다.
	//--------------------------------------------------------------------------------------------------------
	@RequestMapping("/doC")
	public String doC(@ModelAttribute("msg") String msg) {
		System.out.println("doC가 실행되었습니다. msg : " + msg);
		
		// return에 문자열이 사용될 경우에는 문자열.jsp파일을 찾아서 실행하게 된다.
		// void타입과는 다르게 return 타입이 String인 경우에는 리턴하는 문자열이 View 이름이 된다.
		return "exam02/doC";
	}
	//--------------------------------------------------------------------------------------------------------
	// 구구단 입력 폼 (7.12)
	// return type이 void이면 value값이 page이름이 된다.
	//--------------------------------------------------------------------------------------------------------
	@RequestMapping(value="gugudanForm.do", method=RequestMethod.GET)
	public void guguForm() {
		System.out.println("구구단 화면을 불렀습니다.");
	}
	
	//--------------------------------------------------------------------------------------------------------
	// 구구단 (7.4)
	// Model : 데이터를 담는 그릇 역할을 한다. map 구조로 저장이 된다.
	// model.addAttribute("변수명", 값);
	//--------------------------------------------------------------------------------------------------------
    /////1//////@RequestMapping("gugu.do") // GET, POST 방식에 상관없다.
	@RequestMapping(value="gugu.do", method=RequestMethod.GET)
	/////1////// public String gugu(int dan, Model model) {
	/////2////// public String gugu(int dan, String good, Model model) {
	/////3////// public String gugu(@RequestParam int dan, String good, Model model) {
	public String gugu(@RequestParam(defaultValue="3") int dan, String good, Model model) {
		
			
		//--------------------------------------------------------------------------------------------------------
		// Spring에서는 get방식에서 아래의 줄과 같은 방식으로 사용하지 않는다.
		// int dan = Integer.parseInt(request.getParameter("dan"));
		//--------------------------------------------------------------------------------------------------------
		// <a href=${path}/gugu.dan?dan=8">구구단</a>
		// public String gugu(HttpServletRequest request, Model model)을 사용하지 않고,
		// public String gugu(int dan, Model model)을 사용한다.
		
		// 값을 넘겨주는 페이지에서 값은 String으로 넘어오지만,
		// spring에서는 정수형으로 잘 받는다. (이런 과정이 스프링 안에 잘 녹아있는 것이다.)
		
		// public String gugu(int dan, String good, Model model)
		// 넘어오는 매개변수명만 맞으면 매개변수의 위치는 상관없이 잘 받는다.
		// ex) http://localhost:8081/exam02/gugu.do?good=monday&dan=2
		
		// public String gugu(int dan, String good, Model model)
		// public String gugu(@RequestParam int dan, String good, Model model)
		// 실제로는 @RequestParam이 숨어 있는 것이기 때문에 기술하지 않아도 된다.
			
		System.out.println("전송 받은 문자열 : " + good);  // public String gugu(int dan, String good, Model model) {
		
		String result ="";
		for(int i = 1; i <= 9; i++) {
			result += dan + " x " + i + " = " + dan*i + "<br/>";
		}
		
		model.addAttribute("result", result);
		return "exam02/gugudan";
	
	} // end - 	public String gugu
 
} // end - public class ExamController2
