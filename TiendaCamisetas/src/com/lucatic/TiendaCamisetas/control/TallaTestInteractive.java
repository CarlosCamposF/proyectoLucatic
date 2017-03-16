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
        Talla tall;
        String action;
        int idtalla;

        System.out.println("\n\n[C]reate | [R]ead | [U]pdate | [D]elete | [L]ist | [Q]uit: ");
        action = in.readLine();
        if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
            return true;
        }

        switch (action.toUpperCase().charAt(0)) {
            // Create a new talla record
            case 'C':
                tall = inputTalla(in);
                dao.add(tall);
                System.out.println("Successfully added Talla Record: " + tall.getIdTalla());
                System.out.println("\n\nCreated " + tall);
                break;

            // Display an talla record
            case 'R':
                System.out.println("Enter int value for talla id: ");
                idtalla = new Integer(in.readLine().trim());

                // Find this Talla record
                tall = dao.findById(idtalla);
                if (tall != null) {
                    System.out.println(tall + "\n");
                } else {
                    System.out.println("\n\nTalla " + idtalla + " not found");
                    break;
                }

                break;

            // Update an existing talla record    
            case 'U':
                System.out.println("Enter int value for talla id: ");
                idtalla = new Integer(in.readLine().trim());
                // Find this Talla record
                tall = null;
                tall = dao.findById(idtalla);
                if (tall == null) {
                    System.out.println("\n\nTalla " + idtalla + " not found");
                    break;
                }
                // Go through the record to allow changes

                tall = inputTalla(in, tall);
                dao.update(tall);
                System.out.println("Successfully updated Talla: " + tall.getIdTalla());
                break;

            // Delete an talla record
            case 'D':
                System.out.println("Enter int value for talla id: ");
                idtalla = new Integer(in.readLine().trim());

                // Find this Talla record                 
                dao.delete(idtalla);
                System.out.println("Deleted Talla " + idtalla);
                break;

            // Display a list (Read the records) of Tallas
            case 'L':
                Talla[] allTalla = dao.getAllTallas();
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

    public static Talla inputTalla(BufferedReader in, Talla tallDefaults) throws IOException {
        return inputTalla(in, tallDefaults, false);
    }

    public static Talla inputTalla(BufferedReader in, Talla tallDefaults, boolean newTalla) throws IOException {
       
        
        int id = -1;
        String nombre;
        Talla tall;
        
        if (newTalla) {
            do {
                System.out.println("Enter int value for talla id: ");
                try {
                    id = new Integer(in.readLine().trim());
                    if (id < 0) {
                        System.out.println("Please retry with a valid positive integer id");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please retry with a valid positive integer id");
                }
            } while (id < 0);
        } else {
            id = tallDefaults.getIdTalla();
            System.out.println("Modify the fields of Talla record: " + id
                    + ". Press return to accept current value.");
        }

        String prompt = "Enter value for talla nombre" + ((tallDefaults == null) ? "" : " [" + tallDefaults.getNombre() + "]");

        do {
            System.out.println(prompt + " : ");
            nombre = in.readLine().trim();
            if (nombre.equals("") && tallDefaults != null) {
                nombre = tallDefaults.getNombre();
            }
            if (nombre.length() < 1) {
                System.out.println("Please retry with a valid nombre");
            }
        } while (nombre.length() < 1);


       

       


        

        // Create an Talla object
        tall = new Talla(id, nombre);
        return tall;
    }
}