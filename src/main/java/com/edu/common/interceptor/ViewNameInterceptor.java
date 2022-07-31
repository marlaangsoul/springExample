package com.edu.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//------------------------------------------------------------------------------------------------------------
// public class ViewNameInterceptor
// 스프링에서 제공하는 org.springframework.web.servlet.HandlerInterceptor 인터페이스를 구현하거나,
// org.springframework.web.servlet.handler.HandlerInterceptorAdapter 추상클래스를 오버라이딩해서 작성한다.
// HandelrInterdceptorAdapter 추상 클래스의 경우에는 HandlerInterceptor 인터페이스를 상속받아 구현되었다.
//------------------------------------------------------------------------------------------------------------
public class ViewNameInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ViewNameInterceptor.class);

	//------------------------------------------------------------------------------------------------------------
	// preHandle() : 컨트롤러가 호출되기 전에 실행된다.
	// 컨트롤러가 실행되기 이전에 처리해야 할 작업이 있는 경우이거나, 요청정보를 가공하거나 추가하는 경우에 사용.
	//------------------------------------------------------------------------------------------------------------
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println();
		logger.info("---------------------------------------------------------------------------------");
		System.out.println("컨트롤러에 가기 전에 인터셉터가 가로챘습니다.");
		System.out.println("[preHandle][" + request + "][" + request.getMethod() + "][" + request.getRequestURI() +"]");
		System.out.println("[handler][" + handler.toString() + "]");
		logger.info("---------------------------------------------------------------------------------");
		System.out.println();
		return true;
	}
	
	//------------------------------------------------------------------------------------------------------------
	// postHandle() : 핸들러가 실행은 완료되었으나 아직 view가 생성되기 이전에 호출이 된다.
	// ModelAndView 타입의 정보가 매개변수값으로 받는다. 따라서 Controller에서 view 정보를 전달하기 위해 
	// 작업한 Model객체의 정보를 참조하거나, 조작할 수 있다.
	// 비동기적 요청처리 시에는 처리되지 않는다.
	//------------------------------------------------------------------------------------------------------------
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println();
		logger.info("---------------------------------------------------------------------------------");
		System.out.println("컨트롤러가 일을 끝마쳤으므로 인터셉터가 가로챘습니다.");
		System.out.println("[ModelAndView][Model 일 : " + modelAndView.getViewName() + "][Model 내용 : " + modelAndView.getModel() + "]");
		logger.info("---------------------------------------------------------------------------------");
		System.out.println();

		super.postHandle(request, response, handler, modelAndView);
	}
	
	//------------------------------------------------------------------------------------------------------------
	//afterCompletion() : 모든 view에서 최종 결과를 생성하는 일을 포함한 모든 작업이 완료된 후에 실행된다.
	// 요청 처리 중에 사용한 리소스를 반환해주기에 적당한 메서드이다.
	// 비동기적 요청처리 시에는 처리되지 않는다.
	//------------------------------------------------------------------------------------------------------------
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	

	
}// end - public class ViewNameInterceptor
