package com.surefor.utils.logging;

/**
 * Annotation @Log
 * 
 * @Log annotation selectively enables to log method name, parameter(s), before/after time stamp and execution time.  
 * 
 * example:
 * 
 * com.surefor.utils.logging.Log ;
 * 
 * @Log(params = {"1", "2"}, timestamp=true, execTime=true)
 * public void getAccountBalance(String accountNumber, DateRange range) {
 * ...
 * }
 * 
 * 1. @Log 
 *  : com.surefor.utils.logging.Log
 *  : Selectively enable/disable logging feature(s)
 * 2. params = {"1", "2"}
 *  : Log first and second parameter value(s) 
 * 3. timestamp=true
 *  : Enable time stamp feature. It puts 2 time stamps, before/after method 
 * 4. execTime=true
 *  : Enable to log method execution time.
 * 
 * @author Ethan Cha
 */
public @interface Log {
	String[] params() default "" ;
	boolean timestamp() default false ;
	boolean execTime() default false ;
}
