package com.dma.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// where the code define in this class will run is defined by the pointcut
	// ..* = the class, the method and everything else
	@Pointcut("within(com.dma.pma.controllers..*)")
	public void definePackagePointcuts() {
		// empty method just to name the location specified in the pointcut
	}
	
	// "after" the pointcut "definePackagePointcuts" the log.debug will run
	/*
	@After("definePackagePointcuts()")
	public void log(JoinPoint joinPoint) {
		log.debug("----------------------------------------------------");
		log.debug("********** After Execution **********\n {}.{}() with arguments[s] = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		log.debug("---------------------------------------------------- \n");
	}
	*/
	
	@Around("definePackagePointcuts()")
	public Object logAround(ProceedingJoinPoint proceedingJoinPoint) {
		log.debug("----------------------------------------------------");
		log.debug("********** Before Execution **********\n {}.{}() with arguments[s] = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
		log.debug("---------------------------------------------------- \n");
		
		Object object = null;
		try {
			object = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		log.debug("----------------------------------------------------");
		log.debug("********** After Execution **********\n {}.{}() with arguments[s] = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));
		log.debug("---------------------------------------------------- \n");

		return object;
	}
}
