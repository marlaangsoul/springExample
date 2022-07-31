// 6.30.
package com.edu.exam3.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.exam3.vo.MemberVO;

//---------------------------------------------------------------------------------------------------------------
// public class ExamController3
// servlet-context에 <context:component-scan base-package="com.edu.exam3" /> 등록해주고,
// Retrun Type이 String인 경우 - 데이터를 전달하는 경우
//---------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/exam03")
public class ExamController3 {

	//---------------------------------------------------------------------------------------------------------------
	// public String doD(Model model)
	//---------------------------------------------------------------------------------------------------------------
	@RequestMapping("/doD")
	public String doD(Model model) {
		
		System.out.println("doD가 시작되었습니다......");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid("userID-GOOD");
		memberVO.setUserpw("userPW-1111");
		
		model.addAttribute("member", memberVO);	
		
		return "/exam03/memberData";
		
	}// end - public String doD(Model model)
	
	    //---------------------------------------------------------------------------------------------------------------
		// public String doE
		//---------------------------------------------------------------------------------------------------------------
		@RequestMapping("/doE")
		public String doE(Model model) {
			System.out.println("doE가 실행되었습니다.");
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", "userID-BAD");
			map.put("userpw", "userPW-2222");
			
			model.addAttribute("map", map);
			
			return "/exam03/memberData";
		} // end - public String doE(Model model)
		
} // end - public class ExamController3
