package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EstadoSesion;

/**
 * Servlet implementation class Iniciar
 */
@WebServlet("/iniciar")
public class Iniciar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Iniciar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (Home.getEstadoSesion(request) == EstadoSesion.NO_LOGIN) {
        	request.getRequestDispatcher("WEB-INF/home/login.jsp").forward(request, response);
    	} else {
    		//System.out.println(request.getSession().getAttribute("usr_sesion"));
    		request.getRequestDispatcher("/home").forward(request, response);
    	}
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
