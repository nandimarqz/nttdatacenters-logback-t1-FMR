package com.nttdata.nttdatacenters_logback_t1_FMR;

import com.nttdata.iteration.Iteration;

/**
 * Proyecto SL4J+ Logback
 * @author nandi
 *
 */
public class App {

	/**
	 * Clase principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		//Creacion de objetos y llamadas a metodos
		
		Loggin log = new Loggin();

		System.out.println(log.showUsers());
		
		log.createUser("Fernando", "Marquez", 20, "hola", "nandimere", "nandimarquez31@gmail.com", "hola");
		log.createUser("Fernando", "Marquez", 16, "hola", "nandimarqzz", "nandimarquez@gmail.com", "hola");
		log.createUser("Fernando", "Marquez", 25, "hola", "nandimarqzz", "nandimarquez@gmail.com", "hola");
		log.createUser("Pedro", "Marquez", 25, "hola", "pedromarqzz", "pedromarquez@gmail.com", "holaa");
		log.createUser("Pedro", "Marquez", 25, "hola", "pedromarqzz", "pedromarquez@gmail.com", "hola");
		log.createUser("Pedro", "Marquez", 25, "hola", "pedromarqzz", "pedromarquez@gmail.com", "hola");
		log.createUser("Raul", "Marquez", 25, "hola", "raulmarqzz", "raulmarquez@gmail.com", "hola");
		log.createUser("Raul", "Marquez", 25, "hola", "raulmar", "raulmarquez@gmail.com", "hola");
		log.createUser("Raul", "Marquez", 25, "hola", "pedromarqzz", "raulmarquez52@gmail.com", "hola");
		
		System.out.println(log.showUsers());
		
		Iteration iter = new Iteration();
		
		iter.iteration();
	}
}
