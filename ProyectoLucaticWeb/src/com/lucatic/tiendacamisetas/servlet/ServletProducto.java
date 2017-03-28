package com.lucatic.tiendacamisetas.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lucatic.tiendacamisetas.beans.Camiseta;
import com.lucatic.tiendacamisetas.beans.Detalle;
import com.lucatic.tiendacamisetas.beans.Producto;
import com.lucatic.tiendacamisetas.dao.DetallesDAOJDBCImp;
import com.lucatic.tiendacamisetas.dao.ProductoDAOImp;

@WebServlet( 
        name = "ServletProducto", 
        urlPatterns = {"/Servlet.do"}, 
        asyncSupported = false 
)
public class ServletProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	try{
    		/*ProductoDAOImp producto = new ProductoDAOImp();
    		ArrayList<Producto> listado = new ArrayList<Producto>();*/
    		List <Producto> p = new ProductoDAOImp().AllCamiseta();
    		
    		HttpSession sesion = request.getSession();
    		
    		request.setAttribute("styles", p); 
            RequestDispatcher view = request.getRequestDispatcher("mostrarMenu.jsp"); 
            view.forward(request, response);
    		
    		
    	}catch (Exception e) {
            System.out.println("--------------------  FALLO  -----------------------------");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("----------------------------------------------------------");
        }
    
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {
        return "Short description";
    }
    
}
