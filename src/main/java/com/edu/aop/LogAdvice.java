package com.edu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//-----------------------------------------------------------------------------------------------------------
// public class LogAdvic
// @Aspect : 해당 클래스 객체가 Aspect를 구현한 것임을 나타내기 위해서 사용한다.
// @Component : AOP와 관계는 없지만 스프링에서 Bean으로 인식하기 위해서 사용한다.
//-----------------------------------------------------------------------------------------------------------
@Aspect
@Component
public class LogAdvice {

	private static Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	/*
	 @Before : 타겟 메서드가 실행하기 이전 어드바이스 기능을 수행한다.
	 @After : 타겟 메서드의 결과에 상관없이 실행 후 어드바이스 기능을 수행한다.
	 @AfterReturning : 타겟 메서드가 정상적으로 결과를 반환한 후에 어드바이스 기능을 수행한다.
	 @AfterThrowing : 타겟 메서드가 수행 중 예외를 발생하면 어드바이스 기능을 수행한다.
	 @Around : 어드바이스가 타겟 메서드를 감싸서 타겟 메서드의 호출 전, 후 어드바이스 기능을 수행한다.
	*/
	/* 
	 execution(int minus(int, int)) : int 타입의 리턴값, minus메서드, 두개의 int 파라미터를 가지는 메서드
	 execution(* minus(int, int)) : 모든 타입의 리턴값, minus메서드, 두개의 int 파라미터를 가지는 메서드
	 execution(* minus(..)) : 리턴 타입, 파라미터의 종류, 개수에 상관없이 minus라는 메서드 이름을 가진 모든 메서드
	 execution(* minus()) : 리턴 타입은 상관없이 파라미터가 존재하지 않는 minus 메서드
	 execution(* *(..)) : 리턴 타입, 메서드 이름, 파라미터 이름에 상관없이 모든 조건을 허용하는 포인트 컷 표현식
	 execution(* com.edu.aop.Target.*(..)) : com.edu.aop 패키지의 Target클래스에 존재하는 메서드
	 execution(* com.edu.aop.*.*(..)) : com.edu.aop 패키지에 존재하는 모든 클래스
	*/
	/*
	 @Around(execution(* com.edu.aop.service.AopService*.*(..))")
	 execution.. : AspectJ의 표현식
	 execution   : 접근 제한자와 특정 클래스의 메서드를 지정할 수 있다.
	 맨 앞의 * 	 : 리턴타입을 의미한다.
	 맨 뒤의 *	 : 클래스의 이름과 매서드의 이름을 의미한다.
	*/
	//-----------------------------------------------------------------------------------------------------------
	// @Before
	//-----------------------------------------------------------------------------------------------------------
	@Before("execution(* com.edu.board.service.*.*(..))")
	public void logBefore() throws Exception {
		
		logger.info("========================================================================");
		logger.info("AOP Before 입니다.");
		logger.info("========================================================================");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// @After
	//-----------------------------------------------------------------------------------------------------------
	@After("execution(* com.edu.board.service.*.*(..))")
	public void logAfter() throws Exception {
			
		logger.info("========================================================================");
		logger.info("AOP After 입니다.");
		logger.info("========================================================================");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 모든 패키지 내의 service, dao package에 존재하는 클래스
	//-----------------------------------------------------------------------------------------------------------
	@Around("execution(* *..service.*.*(..)) or execution(* *..dao.*.*(..))")
	public Object calculateExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		
		logger.info("========================================================================");
		logger.info("AOP Around 입니다.");
		logger.info("========================================================================");
		
		// 해당 클래스의 처리 전 시간
		StopWatch sw = new StopWatch();
		sw.start();
	
		// 해당 클래스의 메서드를 실행한다.  ==> 핵심 업무
		Object result = pjp.proceed(); // 본 업무
		
		// 해당 클래스의 처리 후 시간
		sw.stop();
		long executionTime = sw.getTotalTimeMillis();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String task = className + "." + methodName;
		
		logger.info("========================================================================");
		logger.info("[업무처리소요시간]" + task + "==>" + executionTime + "(ms)");
		logger.info("========================================================================");
		
		return result;
	}
	
	
} // end - public class LogAdvic
