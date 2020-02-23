package com.sarthak.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// set up logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// set up pointcut declaration
	@Pointcut("execution(* com.sarthak.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	// do the same for servuce package and dao package
	@Pointcut("execution(* com.sarthak.springdemo.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* com.sarthak.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {

	}

	// add @Before advice

	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {

		// display method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("======> in @Before: calling method: " + method);

		// display the arguments to the method
		

	}

	// add @AfterReturning advice

}
