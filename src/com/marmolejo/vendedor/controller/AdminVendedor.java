
package com.marmolejo.vendedor.controller;
import com.marmolejo.vendedor.dao.VendedorDAO;
import com.marmolejo.vendedor.model.Vendedor;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.sql.SQLException;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Yessica
 */
@WebServlet("/adminVendedor")
public class AdminVendedor extends HttpServlet {

   VendedorDAO VendedorDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        try {
            VendedorDAO = new VendedorDAO(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException e) {

        }
    }

    public AdminVendedor() {
        super();
    }
     protected void  doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String action = request.getParameter("action"); 
        
        try{
        switch (action){
            case "index":
                index(request, response);
                break; 
            case "nuevo":
                nuevo(request, response);
                break;
            case "register":
               registrar(request, response);
                break;
            case "mostrar":
		mostrar(request, response);
		break;
	    case "showedit":
		showEditar(request, response);
		break;	
	    case "editar":
		editar(request, response);
		break;
	    case "eliminar":
		eliminar(request, response);
		break;
		default:
			break;
				
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
    
}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }
    
    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
        dispatcher.forward(request, response); 
    }
    
   private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Vendedor Vendedor = new Vendedor(0, request.getParameter("codigo"), request.getParameter("nombre"), request.getParameter("telefono"), request.getParameter("cantidad"), Double.parseDouble(request.getParameter("salario")));
		VendedorDAO.insertar(Vendedor);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
    }
private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		List<Vendedor> listaVendedor= VendedorDAO.listaVendedor();
		request.setAttribute("lista", listaVendedor);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Vendedor Vendedor = VendedorDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("Vendedor", Vendedor);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Vendedor Vendedor = new Vendedor(Integer.parseInt(request.getParameter("id")), request.getParameter("codigo"), request.getParameter("nombre"), request.getParameter("telefono"),request.getParameter("email"), Double.parseDouble(request.getParameter("salario")));
		VendedorDAO.actualizar(Vendedor);
		index(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Vendedor Vendedor = VendedorDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		VendedorDAO.eliminar(Vendedor);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}
}
