package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AltaUsuario
 */
@WebServlet("/altaUsuario")
public class AltaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getAttribute("EstadoRegistro") == null) {
    		request.setAttribute("EstadoRegistro", "noRegistro");
    	}
    	if (request.getAttribute("EstadoRegistro").equals("noRegistro")) {
    		request.getRequestDispatcher("/WEB-INF/usuarios/altaUsuario.jsp").forward(request, response);
    	} else if (request.getAttribute("EstadoRegistro").equals("errorRegistroNick")) {
    		request.getRequestDispatcher("/WEB-INF/usuarios/errorAltaNickRep.jsp").forward(request, response);
    	} else if (request.getAttribute("EstadoRegistro").equals("errorRegistroCorreo")) {
    		request.getRequestDispatcher("/WEB-INF/usuarios/errorAltaCorreoRep.jsp").forward(request, response);
    	} else if (request.getAttribute("EstadoRegistro").equals("okRegistro")) {
    		request.setAttribute("EstadoRegistro", null);
    		request.getRequestDispatcher("/iniciar").forward(request, response);
    	}
    }
    
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
