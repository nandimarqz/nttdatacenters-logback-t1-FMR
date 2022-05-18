package com.nttdata.nttdatacenters_logback_t1_FMR;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loggin {

	// Logger para la clase
	private static final Logger LOGGINGLOG = LoggerFactory.getLogger(Loggin.class);

	// Atributos clase
	private static final File FICH = new File("bbddUsers.dat");
	private ObjectOutputStream usersFile = null;

	/**
	 * Este metodo crea un usuario y lo escribe en un fichero binario
	 * 
	 * @param name
	 * @param surNames
	 * @param age
	 * @param passwd
	 * @param userName
	 * @param email
	 * @param confirmPasswd
	 */
	public void createUser(String name, String surNames, int age, String passwd, String userName, String email,
			String confirmPasswd) {

		LOGGINGLOG.debug("Entrada en el metodo");

		// Si el metodo checkUser da false es que esta todo correcto y se puede crear el
		// usuario
		if (!this.checkUser(passwd, userName, email, confirmPasswd)) {

			// Si el fichero de usuarios no existe usersFile pasa a ser un
			// ObjectOutoputStream
			// Si existe usersFile pasa a ser de la clase IOUser que es hija de
			// ObjectOutoputStream
			if (!FICH.exists()) {

				try {
					usersFile = new ObjectOutputStream(new FileOutputStream(FICH));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {

				try {
					usersFile = new IOUser(new FileOutputStream(FICH, true));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			try {

				// Se crea el usuario
				User user = new User(name, surNames, age, confirmPasswd, userName, email);

				// Si la edad del usuario es igual a 0 es que la edad era menor a 18 a la hora
				// de crearse
				// Si es diferente a 0 es que la edad era mayor o igual a 18 y se escribe el
				// usuario en el fichero y muestra el saludo
				if (user.getAge() == 0) {
					LOGGINGLOG.error("La edad es menor a 18 años no se puede crear la cuenta");
				} else {
					usersFile.writeObject(user);
					LOGGINGLOG.info("El usuario se ha creado y guardado correctamente");
				}

				usersFile.flush();
				usersFile.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				LOGGINGLOG.error("No se ha encontrado el fichero");
			} catch (IOException e) {
				e.printStackTrace();
				LOGGINGLOG.error(e.getMessage());
				LOGGINGLOG.error(e.getLocalizedMessage());
			}

		}

		LOGGINGLOG.debug("Fin del metodo");
	}

	/**
	 * Este metodo comprueba el usuario para validar si puede ser creado o no
	 * 
	 * @param passwd
	 * @param userName
	 * @param email
	 * @param confirmPasswd
	 * @return boolean
	 */
	public boolean checkUser(String passwd, String userName, String email, String confirmPasswd) {

		// booleano para indicar si se puede crear o no
		boolean error = false;

		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FICH))) {

			// Si la contraseña es igual que la contraseña de confirmacion entra en la
			// condicion si no,
			// el boolean pasa a true indicando que hay un error
			if (passwd.equals(confirmPasswd)) {

				// Mientras que el error se mantenga a falso se ejecuta el bucle, cuando llegue
				// al final del fichero
				// saltra una exepcion EOFExceptio
				while (!error) {

					// Se lee un objeto de tipo usuario
					User user = (User) reader.readObject();

					// Si el nombre del usuario leido del fichero es igual al pasado por parametro
					// ocurre un error: el nombre de usuario esta siendo utulizado. Igual para el email.
					if (user.getUserName().equals(userName)) {

						error = true;
						LOGGINGLOG.error("El nombre de usuario introducido esta siendo utilizado en otra cuenta");

					} else if (user.getEmail().equals(email)) {

						error = true;
						LOGGINGLOG.error("El email introducido esta siendo utilizado en otra cuenta");

					}

				}

			} else {
				error = true;
				LOGGINGLOG.error("Las contraseñas introducidas no son iguales");
			}

		} catch (NullPointerException e) {
			LOGGINGLOG.info(e.getMessage());
		} catch (EOFException e) {
			LOGGINGLOG.info("Se ha terminado el fichero");
		} catch (FileNotFoundException e) {
			LOGGINGLOG.error("No se ha encontrado el fichero");
		} catch (IOException e) {
			LOGGINGLOG.error(e.getMessage());
			LOGGINGLOG.error(e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			LOGGINGLOG.error(e.getMessage());
			e.printStackTrace();
		}

		return error;
	}

	/**
	 * Devuelve todos los usuarios guardados en el fichero
	 * 
	 * @return String
	 */
	public String showUsers() {

		String users = "";

		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FICH))) {

			// Mientras que sea true esto es para generar un bucle infinito
			// ya que la salida la controla la exepcion EOFExeption que indica
			// que el fichero a terminado
			while (true) {

				// Lee un objeto de tipo user
				User user = (User) reader.readObject();

				// Pinta ese objeto en el String
				users += user + "\n";
			}

		} catch (StreamCorruptedException e) {

		} catch (NullPointerException e) {
			LOGGINGLOG.info(e.getMessage());
		} catch (EOFException e) {
			LOGGINGLOG.info("Se ha terminado el fichero");
		} catch (FileNotFoundException e) {
			LOGGINGLOG.error("No se ha encontrado el fichero");
		} catch (IOException e) {
			LOGGINGLOG.error(e.getMessage());
			LOGGINGLOG.error(e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			LOGGINGLOG.error(e.getMessage());
			e.printStackTrace();
		}

		return users;
	}

}
