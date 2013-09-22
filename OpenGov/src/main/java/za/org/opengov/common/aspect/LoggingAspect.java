package za.org.opengov.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
	
	@Before("execution(* za.org.opengov.stockout.service.StockoutReportService.submitStockoutReport(..))")
	public void logBefore(JoinPoint joinPoint) {
 
		System.out.println("--- Called submitStockoutReport service method");
	}

}
