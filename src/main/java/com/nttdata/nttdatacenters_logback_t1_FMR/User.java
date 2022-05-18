package com.nttdata.nttdatacenters_logback_t1_FMR;

import java.io.Serializable;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User implements Comparable<User>, Serializable {

	

	private static final long serialVersionUID = 1L;
	
	//Atributos del usuario
	private String name;
	private String surNames;
	private int age = 0;
	private String passwd;
	private String userName;
	private String email;
	
	//Logger de la clase
	private static final Logger USERLOG = LoggerFactory.getLogger(User.class);
	
	/**
	 * Constructor del usuario
	 * 
	 * @param name
	 * @param surNames
	 * @param age
	 * @param passwd
	 * @param userName
	 * @param email
	 */
	public User(String name, String surNames, int age, String passwd, String userName, String email) {
		super();
		
		//Si la edad del usuario es menor a 18 no se crea el usuario y se envia una traza de error
		if(age >= 18) {
			
			this.name = name;
			this.surNames = surNames;
			this.age = age;
			this.passwd = passwd;
			this.userName = userName;
			this.email = email;
			
		}else {
			
			USERLOG.error("La edad introducida es menor a 18 años, no se puede crear una cuenta a un menor");
			
		}
	
	}
	

	/**
	 * Devuelve el nombre del usuario
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * Actualiza el nombre del usuario
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el apellido del usuario
	 * 
	 * @return String
	 */
	public String getSurNames() {
		return surNames;
	}

	/**
	 * Actualiza el apellido del usuario
	 * 
	 * @param surNames
	 */
	public void setSurNames(String surNames) {
		this.surNames = surNames;
	}

	/**
	 * Devuelve la edad del usuario
	 * 
	 * @return Integer
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Actualiza la edad del usuario
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * Devuelve la contraseña del usuario
	 * 
	 * @return String
	 */
	public String getPasswd() {
		
		USERLOG.warn("Se ha obtenido la contraseña del usuario {}",this.userName);
		return passwd;
	}

	/**
	 * Actualiza la contraseña del usuario
	 * 
	 * @param passwd
	 */
	public void setPasswd(String passwd) {
		USERLOG.warn("Se ha cambiado la contraseña del usuario {}",this.userName);
		this.passwd = passwd;
	}

	/**
	 * Devuelve el nombre de usuario del usuario
	 * 
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Actualiza el nombre de usuario del usuario
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		USERLOG.warn("Se ha cambiado el nombre del usuario {}",this.userName);
		this.userName = userName;
	}

	/**
	 * Devuelve el email del usuario
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Actualiza el email del usuario
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Compara a los usuarios mediante su edad
	 * 
	 * @return Integer
	 */
	@Override
	public int compareTo(User o) {
		
		return this.age - o.age;
	}

	/**
	 * Genera el hashCode del objeto
	 * 
	 * @return Integer
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, passwd, userName);
	}

	/**
	 * Devuelve un boolean que indica si dos usuarios son iguales 
	 * segun su email, nombre de usuario y contraseña
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(passwd, other.passwd)
				&& Objects.equals(userName, other.userName);
	}


	/**
	 * Devuelve un String que pinta al usuario
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "Usuario \n" + name + "\n" + surNames + "\n" + age + "\n" + passwd
				+ "\n" + userName + "\n" + email + "\n";
	}
	
	 
	
}
