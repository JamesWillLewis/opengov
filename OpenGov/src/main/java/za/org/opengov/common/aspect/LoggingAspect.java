/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.org.opengov.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Performs logging (log4j) using AOP techniques. 
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 * @since 1.0
 */
@Aspect
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	

	/**
	 * Any method annotated with @Logged will be intercepted, and
	 * this method will be called prior to the intercepted method's execution. 
	 * 
	 */
	@Before("execution(@za.org.opengov.common.aspect.Logged * *(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info(joinPoint.getSignature().toShortString() + " called.");
	}
	


}
