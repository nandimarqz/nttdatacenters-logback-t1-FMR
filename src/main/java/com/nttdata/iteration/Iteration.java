package com.nttdata.iteration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Iteration {
	
	//Logger para la clase
	private static final Logger LOGGINGIT = LoggerFactory.getLogger(Iteration.class);
	
	/**
	 * Pinta 3000 loggers 
	 */
	public void iteration()
	{
		
		for(int i = 1; i <= 3000; i++) {
			
			LOGGINGIT.info("Iteracion nº {}", i);
			
		}
		
	}
}
