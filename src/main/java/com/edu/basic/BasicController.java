package com.edu.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//-----------------------------------------------------------------------------------------------------------
// public class BasicController
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/basic")
public class BasicController {

	// 로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(BasicController.class);
	//-----------------------------------------------------------------------------------------------------------
	// 체크 박스
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping("/checkBox01")
	public String checkBox01() {
		
		logger.info("BasicController checkBox01()");
		return "/basic/checkBox01";
		
	} // end - public String checkBox01()
	
} // end - public class BasicController
