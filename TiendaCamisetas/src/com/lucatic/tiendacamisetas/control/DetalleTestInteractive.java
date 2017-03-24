package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lucatic.tiendacamisetas.beans.Detalle;
import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.DetallesDAO;
import com.lucatic.tiendacamisetas.dao.DetallesDAOFactory;
import com.lucatic.tiendacamisetas.dao.DetallesDAOJDBCImp;
import com.lucatic.tiendacamisetas.dao.GestorDAO;
import com.lucatic.tiendacamisetas.model.Categoria;

public class DetalleTestInteractive {
	static DetallesDAOJDBCImp jdbc = new DetallesDAOJDBCImp();
	public void iniciarMenuDetalle() {
		DetallesDAOFactory factory = new DetallesDAOFactory();
		boolean timeToQuitDetalle = false;
		try (DetallesDAO dao = factory.createDetalleDAO()) {
			do {
				try {
					timeToQuitDetalle = executeMenuDetalle(dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass());
					System.out.println("Mensaje " + e.getMessage());
				}
			} while (!timeToQuitDetalle);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass());
			System.out.println("Mensaje " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error " + e.getClass());
			System.out.println("Mensaje " + e.getMessage());
		}

	}

	public static boolean executeMenuDetalle(GestorDAO dao) throws IOException, DAOException {

		int idDetalle;
		Detalle det;
		String action;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n\n----Tabla Detalles----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]ostrar Todo | [B]orrar | [L]istar | [A]tras: \n");
		action = in.readLine();

		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {

			return true;
		}else{
			
		}
		switch (action.toUpperCase().charAt(0)) {
		
		case 'C':
			
			/*//det =
			//dao.add(det);

			System.out.println("\n\nCategoría " + det.getCantidad() + " introducida correctamente:");

			break;*/

		// MENÚ LEER CATEGORIA ****************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			int id= new Integer(in.readLine().trim());
			int id1=1;
			Detalle mostrar=jdbc.find(id);
			if (mostrar != null) {
				System.out.println(mostrar.toString() + "\n");
			} else {
				System.out.println("\n\n Id  " + id1+ " no encontrado");
				}

			break;

		// MENÚ MODIFICAR CATEGORIA ***********************************************
		case 'M':
			Detalle[] jdbc2 = (Detalle[]) new DetallesDAOJDBCImp().getAllTablas();
			for(Detalle d:jdbc2){
				d.toString();
			}
			/*System.out.println("Por favor, introduce el valor del ID a modificar:");
			idDetalle = new Integer(in.readLine().trim());

			det = null;
			det = dao.find(idDetalle);
			if (det == null) {
				System.out.println("\n\nCategoría " + idDetalle + " no encontrada");
			}
			det = inputCategoria(in, det);
			dao.update(det);
			System.out.println("Cantidad modificada a [" + det.getCantidad()() + "] correctamente.");
			break;
*/
		// MENÚ BORRAR CATEGORIA ***************************************************
		case 'B':
			/*System.out.println("Introduce el ID que desea borrar: ");
			idCategoria = new Integer(in.readLine().trim());

			dao.delete(idCategoria);
			System.out.println("ID " + idCategoria + " borrada correctamente.");
			break;
*/
		// MENÚ LISTAR COLORES
		case 'L':
			/*Categoria[] allCategoria = det.getAllTablas();
			for (Categoria categoria : allCategoria) {
				System.out.println(categoria + "\n");
			}
			break;
		}
*/
		
		}
		return false;
	}
}
