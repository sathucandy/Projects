package com.sarthak.springdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// set up logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// set up pointcut declaration

	// add @Before advice

	// add @AfterReturning advice

}
