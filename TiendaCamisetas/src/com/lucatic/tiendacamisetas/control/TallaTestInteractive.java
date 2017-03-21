package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.TallaDAO;
import com.lucatic.tiendacamisetas.dao.TallaDAOFactory;
import com.lucatic.tiendacamisetas.menu.MenuPrincipal;
import com.lucatic.tiendacamisetas.model.Talla;

public class TallaTestInteractive {

	public void IniciarMenuTalla() {
		TallaDAOFactory factory = new TallaDAOFactory();

		boolean timeToQuit = false;
		try (TallaDAO dao = factory.createTallaDAO();
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
			do {
				try {
					timeToQuit = executeMenu(in, dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass().getName());
					System.out.println("Message: " + e.getMessage());
				}
			} while (!timeToQuit);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass().getName() + " , quiting.");
			System.out.println("Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error closing resource " + e.getClass().getName());
			System.out.println("Message: " + e.getMessage());
		}
	}

	// MEN�***************************************************************************
	public static boolean executeMenu(BufferedReader in, TallaDAO dao) throws IOException, DAOException {
		int idtalla;
		Talla tall;
		String action;

		System.out.println("\n\n----Tabla Talla----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]odificar | [B]orrar | [L]istar | [A]tras: \n");
		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {
			new MenuPrincipal().IniciarPrograma();
			return true;
		}

		switch (action.toUpperCase().charAt(0)) {
		// MEN� CREAR TALLA *******************************************************
		case 'C':
			tall = inputTalla(in, null, true);
			dao.add(tall);

			System.out.println("\n\nTalla " + tall.getNombre() + " introducida correctamente:");

			break;

		// MEN� LEER TALLA *********************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			idtalla = new Integer(in.readLine().trim());

			tall = dao.find(idtalla);
			if (tall != null) {
				System.out.println(tall + "\n");
			} else {
				System.out.println("\n\nTalla " + idtalla + " no encontrada");

			}

			break;

		// MEN� MODIFICAR TALLA ***********************************************
		case 'M':

			System.out.println("Por favor, introduce el valor del ID a modificar:");
			idtalla = new Integer(in.readLine().trim());

			tall = null;
			tall = dao.find(idtalla);
			if (tall == null) {
				System.out.println("\n\nTalla " + idtalla + " no encontrado");
			}
			tall = inputTalla(in, tall);
			dao.update(tall);
			System.out.println("Talla modificada a [" + tall.getNombre() + "] correctamente.");
			break;

		// MEN� BORRAR TALLA ***************************************************
		case 'B':
			System.out.println("Introduce el ID que desea borrar: ");
			idtalla = new Integer(in.readLine().trim());

			dao.delete(idtalla);
			System.out.println("ID " + idtalla + " borrada correctamente.");
			break;

		// MEN� LISTAR TALLAS
		case 'L':
			Talla[] allTalla = dao.getAllTablas();
			for (Talla talla : allTalla) {
				System.out.println(talla + "\n");
			}
			break;
		}

		return false;
	}
	// *************************************************************************************
	

	// MODIFICA UNA TALLA*******************************************************************
	public static Talla inputTalla(BufferedReader in, Talla tallDefaults) throws IOException {
		int id = -1;
		String nombre;
		Talla talls;

		try {

			id = tallDefaults.getIdTalla();
			if (id < 0) {
				do {
					System.out.println("Por favor, introduce un valor v�lido para el ID");
					System.out.println("Introduce el valor del ID a modificar: ");
					id = new Integer(in.readLine().trim());
				} while (id < 0);
			}
		} catch (NumberFormatException e) {
			System.out.println("Introduce el valor del ID a modificar: ");
		}

		id = tallDefaults.getIdTalla();

		String prompt = "Introduce el valor que modificar� la talla"
				+ ((tallDefaults == null) ? "" : " [" + tallDefaults.getNombre() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && tallDefaults != null) {
				nombre = tallDefaults.getNombre();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce una talla v�lida");
			}
		} while (nombre.length() < 1);

		talls = new Talla(id, nombre);
		return talls;
	}
	// ***************************************************************************************

	
	// CREA UNA NUEVA TALLA*******************************************************************

	
	public static Talla inputTalla(BufferedReader in, Talla tallDefaults, boolean newTalla) throws IOException {

		String nombre;
		Talla tall;

		String prompt = "Introduce una talla" + ((tallDefaults == null) ? "" : " [" + tallDefaults.getNombre() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && tallDefaults != null) {
				nombre = tallDefaults.getNombre();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce una talla v�lida");
			}
		} while (nombre.length() < 1);

		tall = new Talla(nombre);
		return tall;
	}
}
