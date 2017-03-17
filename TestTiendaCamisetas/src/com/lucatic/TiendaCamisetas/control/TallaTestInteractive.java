package com.lucatic.TiendaCamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lucatic.TiendaCamisetas.dao.DAOException;
import com.lucatic.TiendaCamisetas.dao.TallaDAO;
import com.lucatic.TiendaCamisetas.dao.TallaDAOFactory;
import com.lucatic.TiendaCamisetas.model.Talla;


public class TallaTestInteractive {

    public static void main(String[] args) {
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

    public static boolean executeMenu(BufferedReader in, TallaDAO dao) throws IOException, DAOException {
    	int idtalla;
    	Talla tall;
        String action;
        

        System.out.println("\n\n[C]reate | [R]ead | [U]pdate | [D]elete | [L]ist | [Q]uit: ");
        action = in.readLine();
        if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
            return true;
        }

        switch (action.toUpperCase().charAt(0)) {
            // MENÚ CREAR TALLA **************************************************
            case 'C':
                tall = inputTalla(in);
                dao.add(tall);
                
                System.out.println("\n\nTalla " + tall.getNombre()+" introducida correctamente:");
               
                break;

            // MENÚ LEER TALLA ****************************************************
            case 'R':
                System.out.println("Por favor, introduce el valor del ID a localizar:");
                idtalla = new Integer(in.readLine().trim());

                
                tall = dao.findByIdTalla(idtalla);
                if (tall != null) {
                    System.out.println(tall + "\n");
                } else {
                    System.out.println("\n\nTalla " + idtalla + " no encontrada");
                    break;
                }

                break;

            // MENÚ MODIFICAR TALLA ***********************************************   
            case 'U':
            	
                System.out.println("Por favor, introduce el valor del ID a modificar:");
                idtalla = new Integer(in.readLine().trim());
            
                
                tall = null;
                tall = dao.findByIdTalla(idtalla);
                if (tall == null) {
                    System.out.println("\n\nTalla " + idtalla + " no encontrado");
                }
                tall = inputTalla(in, tall);
                dao.update(tall); 
                System.out.println("Talla " + tall.getNombre()+" modificada correctamente.");
                break;

            // MENÚ BORRAR TALLA ***************************************************
            case 'D':
                System.out.println("Introduce el ID que desea borrar: ");
                idtalla = new Integer(in.readLine().trim());
                               
                dao.deleteIdTalla(idtalla);
                System.out.println("ID " + idtalla+" borrada.");
                break;

            //MENÚ LISTAR TALLAS
            case 'L':
                Talla[] allTalla = dao.getAllTablasTalla();
                for (Talla talla : allTalla) {
                    System.out.println(talla + "\n");
                }
                break;
        }

        return false;
    }

    public static Talla inputTalla(BufferedReader in) throws IOException {
        return inputTalla(in, null, true);
    }
    
    //MODIFICA UNA TALLA****************************************************************
    public static Talla inputTalla(BufferedReader in, Talla tallDefaults) throws IOException {
    	int id=-1;
    	String nombre;
    	Talla talls;
    	    	   	
           
            try {
            	
                id = tallDefaults.getIdTalla();
                if (id < 0) {
                	do{
                    System.out.println("Por favor, introduce un valor válido para el ID");
                    System.out.println("Introduce el valor del ID a modificar: ");
                    id=new Integer(in.readLine().trim());
                }while (id < 0);}
                }catch (NumberFormatException e) {
                System.out.println("Introduce el valor del ID a modificar: ");
                }
         
          
        id = tallDefaults.getIdTalla();
                
        String prompt = "Introduce el valor que modificará la talla" + ((tallDefaults == null) ? "" : " [" + tallDefaults.getNombre() + "]");

        do {
            System.out.println(prompt + " : ");
            nombre = in.readLine().trim();
            if (nombre.equals("") && tallDefaults != null) {
                nombre = tallDefaults.getNombre();
            }
            if (nombre.length() < 1) {
                System.out.println("Por favor, introduce una talla válida");
            }
        } while (nombre.length() < 1);
        
        talls = new Talla(id, nombre);
        return talls;
    }

    
    //CREA UNA NUEVA TALLA********************************************************
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
                System.out.println("Por favor, introduce una talla válida");
            }
        } while (nombre.length() < 1);
            
        tall = new Talla(nombre);
        return tall;
    }
}