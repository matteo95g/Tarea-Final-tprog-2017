package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EstadoSesion;

/**
 * Servlet implementation class CerrarSesion
 */
@WebServlet("/cerrarSesion")
public class CerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerrarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (Home.getEstadoSesion(request) == EstadoSesion.LOGIN_CORRECTO) {
    		request.getSession().setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
            request.getSession().setAttribute("usr_sesion", null);
    		Cookie[] cookies = request.getCookies();
    		if (cookies != null) {
        		for (int i = 0; i < cookies.length; i++) {
        			if (cookies[i].getName().equals("cookieSesion")) {
        				response.setContentType("text/html");
        				cookies[i].setValue("");
        				cookies[i].setPath("/");
        				cookies[i].setMaxAge(0);
        				response.addCookie(cookies[i]);
        			}
        		}
    		}
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
            dispatcher.forward(request, response);
    	} else {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
            dispatcher.forward(request, response);
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