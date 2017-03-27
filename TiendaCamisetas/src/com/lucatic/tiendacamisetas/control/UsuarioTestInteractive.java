package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lucatic.tiendacamisetas.beans.Usuario;
import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.UsuarioDAO;
import com.lucatic.tiendacamisetas.dao.UsuarioDAOFactory;
import com.lucatic.tiendacamisetas.model.Rol;

public class UsuarioTestInteractive {

	public void IniciarMenuUsuario() {
		UsuarioDAOFactory factory = new UsuarioDAOFactory();
		boolean timeToQuitUsuario = false;
		try (UsuarioDAO dao = factory.createUsuarioDAO()) {
			do {
				try {
					timeToQuitUsuario = executeMenuUsuario(dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass().getName());
					System.out.println("Message: " + e.getMessage());
				}
			} while (!timeToQuitUsuario);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass().getName() + " , quiting.");
			System.out.println("Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error closing resource " + e.getClass().getName());
			System.out.println("Message: " + e.getMessage());
		}
	}

	// MENÚ***************************************************************************
	public static boolean executeMenuUsuario(UsuarioDAO dao) throws IOException, DAOException {
		int idUsuario;
		Usuario us;
		String action;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n\n----Tabla Usuario----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]odificar | [B]orrar | [L]istar | [A]tras: \n");
		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {
            
			return true;
		}

		switch (action.toUpperCase().charAt(0)) {
		
		// MENÚ CREAR USUARIO **************************************************
		case 'C':
			us = inputUsuario(in, null, true);
			dao.addItem(us);

			System.out.println("\n\nUsuario " + us.getNombreUsuario() + " introducido correctamente:");

			break;

		// MENÚ LEER USUARIO ****************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			idUsuario = new Integer(in.readLine().trim());

			us = (Usuario)dao.findById(idUsuario);
			if (us != null) {
				System.out.println(us + "\n");
			} else {
				System.out.println("\n\nUsuario " + idUsuario + " no encontrado");
				}

			break;

		// MENÚ MODIFICAR USUARIO ***********************************************
		case 'M':

			System.out.println("Por favor, introduce el valor del ID a modificar:");
			idUsuario = new Integer(in.readLine().trim());

			us = null;
			us = (Usuario)dao.findById(idUsuario);
			if (us == null) {
				System.out.println("\n\nUsuario " + idUsuario + " no encontrado");
			}
			us = inputUsuario(in, us);
			dao.updateItem(us);
			System.out.println("Usuario modificado a [" + us.getNombreUsuario() + "] correctamente.");
			break;

		// MENÚ BORRAR USUARIO ***************************************************
		case 'B':
			System.out.println("Introduce el ID que desea borrar: ");
			idUsuario = new Integer(in.readLine().trim());

			dao.removeItem(idUsuario);
			System.out.println("ID " + idUsuario + " borrado correctamente.");
			break;

		// MENÚ LISTAR COLORES
		/*case 'L':
			Categoria[] allCategoria = dao.getAllTablas();
			for (Categoria categoria : allCategoria) {
				System.out.println(categoria + "\n");
			}
			break;
			*/
		}

		return false;
	}
	
	// *************************************************************************************
	

	// MODIFICA UN USUARIO ********************************************************************
	public static Usuario inputUsuario(BufferedReader in, Usuario usDefaults) throws IOException {
		int id = -1;
		String nombre;
		Usuario usuarios;
		String password;
		Rol roll= new Rol(1,"Administrador");
				
		try {
			id = usDefaults.getIdUsuario();
			if (id < 0) {
				do {
					System.out.println("Por favor, introduce un valor válido para el ID");
					System.out.println("Introduce el valor del ID a modificar: ");
					id = new Integer(in.readLine().trim());
				} while (id < 0);
			}
		} catch (NumberFormatException e) {
			System.out.println("Introduce el valor del ID a modificar: ");
		}
		id = usDefaults.getIdUsuario();
		do {
			System.out.println("Introduce un nombre de usuario:");
			nombre = in.readLine().trim();
			if (nombre.equals("") && usDefaults != null) {
				nombre = usDefaults.getNombreUsuario();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un usuario válido");
			}
		} while (nombre.length() < 1);
		
		do {
			System.out.println("Introduce un password:");
			password = in.readLine().trim();
			if (password.equals("") && usDefaults != null) {
				password = usDefaults.getPassword();
			}
			if (password.length() < 1) {
				System.out.println("Por favor, introduce un password válido");
			}
		} while (password.length() < 1);	
		
		usuarios = new Usuario(id,nombre,password,roll);
		return usuarios;
	}
	// ***************************************************************************************
	

	// CREA UN NUEVO USUARIO ********************************************************************

	
	public static Usuario inputUsuario(BufferedReader in, Usuario usDefaults, boolean newUsuario) throws IOException {

		String nombre;
		Usuario us;
		String password;
		Rol roll= new Rol(2,"Usuario");
				
		do {
			System.out.println("Introduce un nombre de usuario:");
			nombre = in.readLine().trim();
			if (nombre.equals("") && usDefaults != null) {
				nombre = usDefaults.getNombreUsuario();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un usuario válido");
			}
		} while (nombre.length() < 1);
		
		do {
			System.out.println("Introduce un password:");
			password = in.readLine().trim();
			if (password.equals("") && usDefaults != null) {
				password = usDefaults.getPassword();
			}
			if (password.length() < 1) {
				System.out.println("Por favor, introduce un password válido");
			}
		} while (password.length() < 1);
		
	
		us = new Usuario(nombre,password,roll);
		
		return us;
	}
}