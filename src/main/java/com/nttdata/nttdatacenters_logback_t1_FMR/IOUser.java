package com.nttdata.nttdatacenters_logback_t1_FMR;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class IOUser extends ObjectOutputStream{

	/**
	 * Constructor del padre 
	 * 
	 * @throws IOException
	 * @throws SecurityException
	 */
	protected IOUser() throws IOException, SecurityException {
		super();
		
	}

	/**
	 * Constructor del padre sobrecargado
	 * 
	 * @param out
	 * @throws IOException
	 */
	public IOUser(OutputStream out) throws IOException {
		super(out);
		
	}

	/**
	 * Sobrescribe el metodo que escribe la cabecera para que no haga nada 
	 * 
	 */
	@Override
	  protected void writeStreamHeader() throws IOException {
       
    }
}
