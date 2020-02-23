package com.sarthak.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
		// get the arguments
		Object[] args = theJoinPoint.getArgs();

		// loop through and display the arguments
		for (Object tempArg : args) {
			myLogger.info("Argument: " + tempArg);
		}
	}

	// add @AfterReturning advice

	@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

		// display method that we are returning from
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("======> in @AfterReturning: from method: " + method);

		// display the data returned
		myLogger.info("======> Result: " + theResult);

	}

}
